package app.mobius.securityCore.configuration

import app.mobius.credentialManagment.service.AppAuthorizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
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

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication? {
        val name = authentication.name
        val password = authentication.credentials.toString()

        return if(true) {
            UsernamePasswordAuthenticationToken(name, password, arrayListOf())
        } else {
            null
        }
    }

    /**
     *
     * Source:
         *  . Use isAssignableFrom instead of equals: https://stackoverflow.com/a/44650177/5279996
     */
    override fun supports(authentication: Class<*>): Boolean {
//        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
        return true
    }

}