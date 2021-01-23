package app.mobius

import org.springframework.context.annotation.Import

@Import(value = [
    SignUpFeatureModuleConfiguration::class,
    CredentialManagmentFeatureModuleConfiguration::class
])
open class MobiusBusinessModuleConfiguration