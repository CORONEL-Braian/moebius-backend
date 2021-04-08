package app.mobius.credentialManagment

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * https://stackoverflow.com/a/43558513/5279996
 */
@Configuration
@ComponentScan(basePackages = [
    "app.mobius",
    "app.mobius.credentialManagment"
])
open class CredentialManagmentFeatureModuleConfiguration