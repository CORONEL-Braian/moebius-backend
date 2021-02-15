package app.mobius.credentialManagment

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = [
    "app.mobius",
    "app.mobius.credentialManagment"
])
open class CredentialManagmentFeatureModuleConfiguration