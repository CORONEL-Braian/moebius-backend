package app.mobius.securityCore.authentication

import app.mobius.credentialManagment.domain.entity.security.Platform
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
 * Review: https://shout.setfive.com/2015/11/02/spring-boot-authentication-with-custom-http-header/
 */
@Component
@ComponentScan(basePackages = [
    "app.mobius.credentialManagment"
])
class CustomAuthenticationProvider : AuthenticationProvider {

    @Autowired
    private lateinit var appAuthorizationService: AppAuthorizationService

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication? {
//        val name = authentication.name
//        val password = authentication.credentials.toString()

//        A custom token is obtained which includes the headers
        val appAuthToken = SecurityContextHolder.getContext().authentication as AppAuthorizationToken

        val isValidAppAuth = appAuthorizationService.isValidAppAuthorization(
                developerName = appAuthToken.principal,
                privateKey = appAuthToken.credentials,
                platform = appAuthToken.platform,
        )

        return if(isValidAppAuth) {
//              Se devuelve el tipo de autenticacion perteneciente a AuthenticationProvider
            UsernamePasswordAuthenticationToken("","", listOf())
        } else {
            null
        }
    }

    /**
     * Source:
     *  . Use isAssignableFrom instead of equals: https://stackoverflow.com/a/44650177/5279996
     */
    override fun supports(authentication: Class<*>): Boolean {
        /**
         * El tipo de autenticacion de AuthenticationProvider siempre es UsernamePasswordAuthenticationToken, por eso
         * checkeo ese tipo de dato y no AppAuthorizationToken que pertenece a SecurityContextHolder
         */
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

}