package app.mobius.securityCore.configuration

import app.mobius.credentialManagment.service.AppAuthorizationService
import app.mobius.securityCore.security.CustomDigestAuthenticationEntryPoint
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
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter

/**
 * Load this module with Auto Configuration
 *
 * SecurityDigestTechnicalModuleConfiguration will only be loaded if the class AppAuthorizationService is present
 *
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
open class SecurityDigestTechnicalModuleConfiguration: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var appAuthorizationService: AppAuthorizationService

    @Autowired
    private lateinit var digestAuthenticationEntryPoint: CustomDigestAuthenticationEntryPoint

    @Autowired
    @Throws(Exception::class)
    open fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(passwordEncoder().encode("user1Pass"))
                .authorities("ROLE_USER")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/securityNone").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(digestAuthenticationEntryPoint)
        http.addFilterAfter(CustomFilter(),
                DigestAuthenticationFilter::class.java)
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}