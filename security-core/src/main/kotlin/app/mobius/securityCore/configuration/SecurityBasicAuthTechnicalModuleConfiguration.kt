package app.mobius.securityCore.configuration

import app.mobius.credentialManagment.service.AppAuthorizationService
import app.mobius.securityCore.SecurityCoreEndpoints
import app.mobius.securityCore.security.CustomBasicAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


/**
 * Load this module with Auto Configuration
 *
 * SecurityBasicAuthTechnicalModuleConfiguration will only be loaded if the class AppAuthorizationService is present
 *
 *  . Basic vs Digest Auth: https://stackoverflow.com/a/34098797/5279996
 * https://reflectoring.io/spring-boot-modules/
 * https://www.baeldung.com/spring-boot-custom-auto-configuration
 */
@Configuration
@ConditionalOnClass(AppAuthorizationService::class)
@EnableWebSecurity
@ComponentScan(basePackages = [
    "app.mobius.securityCore",
    "app.mobius.credentialManagment"
])
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
open class SecurityBasicAuthTechnicalModuleConfiguration: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var appAuthorizationService: AppAuthorizationService

    @Autowired
    private lateinit var basicAuthenticationEntryPoint: CustomBasicAuthenticationEntryPoint

    @Autowired
    @Throws(Exception::class)
    open fun configureGlobal(auth: AuthenticationManagerBuilder) {
//        TODO: Change login data
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(passwordEncoder().encode("user1Pass"))
                .authorities("ROLE_USER")
    }

    @Autowired
    open fun checkAppAuthorization(appAuthorizationService: AppAuthorizationService) {
//        TODO: Get basic auth and headers
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers(SecurityCoreEndpoints.Keys.HOME).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(basicAuthenticationEntryPoint)

        http.addFilterAfter(CustomFilter(), BasicAuthenticationFilter::class.java)
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}