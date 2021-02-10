package app.mobius.signUp

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = [
    "app.mobius.signUp",
    "app.mobius.security"
])
open class SignUpFeatureModuleConfiguration {

}