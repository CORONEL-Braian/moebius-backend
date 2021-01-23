package app.mobius

import app.mobius.credentialManagment.CredentialManagmentFeatureModuleConfiguration
import org.springframework.context.annotation.Import

@Import(value = [
    SignUpFeatureModuleConfiguration::class,
    CredentialManagmentFeatureModuleConfiguration::class
])
open class MobiusBusinessModuleConfiguration