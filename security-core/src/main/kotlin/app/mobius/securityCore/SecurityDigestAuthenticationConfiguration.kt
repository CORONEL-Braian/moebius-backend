package app.mobius.securityCore

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * TODO: Load this module with Auto Configuration
 * https://reflectoring.io/spring-boot-modules/
 */
@Configuration
@EnableWebSecurity
open class SecurityDigestAuthenticationConfiguration: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        super.configure(http)
    }

}