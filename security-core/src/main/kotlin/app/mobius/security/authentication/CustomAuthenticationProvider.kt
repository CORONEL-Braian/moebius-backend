package app.mobius.security.authentication

import app.mobius.credentialManagment.service.AppAuthorizationService
import app.mobius.domain.security.authorization.AppAuthorizationToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

/**
 * Access the full Authentication request to be able to perform the authentication process.
 */
@Component
@ComponentScan(basePackages = [
    "app.mobius.credentialManagment"
])
class CustomAuthenticationProvider : AuthenticationProvider {

    @Autowired
    private lateinit var appAuthorizationService: AppAuthorizationService

    /**
     * OBS: @param authentication contains username and password but this is received from SecurityContextHolder
     */
    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication? {
//        A custom token is obtained which includes the headers
        val appAuthToken: AppAuthorizationToken? = SecurityContextHolder.getContext().authentication as AppAuthorizationToken?

        appAuthToken?.let {

            val isValidAppAuth = appAuthorizationService.isValidAppAuthorization(
                    developerName = appAuthToken.principal,
                    privateKey = appAuthToken.credentials,
                    platform = appAuthToken.platform,
            )

            return if(isValidAppAuth) {
//              The type of authentication belonging to AuthenticationProvider is returned
                UsernamePasswordAuthenticationToken("","", listOf())
            } else {
                null
            }
        }

        return null
    }

    /**
     * Source:
     *  . Use isAssignableFrom instead of equals: https://stackoverflow.com/a/44650177/5279996
     */
    override fun supports(authentication: Class<*>): Boolean {
        /**
         * The authentication type of AuthenticationProvider is always UsernamePasswordAuthenticationToken,
         * so that data type is checked and not AppAuthorizationToken that belongs to SecurityContextHolder
         */
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

}