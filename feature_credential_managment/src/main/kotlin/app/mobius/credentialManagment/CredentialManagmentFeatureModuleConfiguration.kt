package app.mobius.credentialManagment

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * https://stackoverflow.com/a/43558513/5279996
 */
@Configuration
@ComponentScan(basePackages = [
    "app.mobius",
    "app.mobius.credentialManagment"
])
//@EntityScan(basePackages = ["app.mobius"])
//@EnableJpaRepositories(basePackages = ["app.mobius.credentialManagment.data.repository"])
open class CredentialManagmentFeatureModuleConfiguration