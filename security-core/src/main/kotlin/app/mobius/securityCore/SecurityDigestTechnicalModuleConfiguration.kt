package app.mobius.securityCore

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import kotlin.jvm.Throws

/**
 * Load this module with Auto Configuration
 *
 * https://reflectoring.io/spring-boot-modules/
 * https://www.baeldung.com/spring-boot-custom-auto-configuration
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = ["app.mobius.securityCore"])
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
open class SecurityDigestTechnicalModuleConfiguration: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var digestAuthenticationEntryPoint: CustomDigestAuthenticationEntryPoint

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/securityNone").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(digestAuthenticationEntryPoint)
        http.addFilterAfter(CustomFilter(),
                BasicAuthenticationFilter::class.java)
    }

}