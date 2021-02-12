package app.mobius.security.authentication.configuration

import app.mobius.security.authentication.CustomAuthenticationProvider
import app.mobius.security.authentication.SecurityCoreEndpoints
import app.mobius.web.filter.XHeaderAuthenticationFilter
import app.mobius.security.authentication.www.CustomBasicAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * Load this module with Auto Configuration
 *
 * SecurityBasicAuthTechnicalModuleConfiguration will only be loaded if the class AppAuthorizationService is present
 *
 *  . Basic vs Digest Auth: https://stackoverflow.com/a/34098797/5279996
 * https://reflectoring.io/spring-boot-modules/
 *  . Demo: https://github.com/spring-projects/spring-data-examples/tree/master/rest/security
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = [
    "app.mobius.security"
])
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class SecurityBasicAuthTechnicalModuleConfiguration: WebSecurityConfigurerAdapter() {


    @Autowired
    private lateinit var basicAuthenticationEntryPoint: CustomBasicAuthenticationEntryPoint

    @Autowired
    private lateinit var customAuthenticationProvider: CustomAuthenticationProvider

    /**
     * Use JDBC, UserDetailsService or AuthenticationProvider
     *
     * AuthenticationManagerBuilder is used to create an {@link AuthenticationManager}. Allows for
     * easily building:
     *  . adding {@link AuthenticationProvider}'s.
     *  . others
     */
    override fun configure(auth: AuthenticationManagerBuilder) {
        /**
         * Impl notes:
         * AuthenticationProvider is used because we have our own service that performs the check in the db
         */
        auth.authenticationProvider(customAuthenticationProvider)
    }

    /**
     * This section defines the security policy for the app.
     * - BASIC authentication is supported
     * - /employees is secured using URL security shown below
     * - CSRF headers are disabled since we are only testing the REST interface,
     *   not a web one.
     */
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        /**
         * The most restrictive rules should be at the top.
         */
        http.authorizeRequests()
                .antMatchers(SecurityCoreEndpoints.Keys.HOME).permitAll()
                .antMatchers(HttpMethod.POST).permitAll()

        /**
         * Allow POST, PUT or DELETE request
         *
         * NOTE: csrf filter is enabled by default and it actually blocks any POST, PUT or DELETE requests
         * which do not include de csrf token.
         *
         * Source: https://stackoverflow.com/a/66149133/5279996
         */
        http.csrf().disable()

        http.authorizeRequests()
                .anyRequest().authenticated().and().httpBasic()
                .authenticationEntryPoint(basicAuthenticationEntryPoint)

//        Verifying subsequent requests
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(XHeaderAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}