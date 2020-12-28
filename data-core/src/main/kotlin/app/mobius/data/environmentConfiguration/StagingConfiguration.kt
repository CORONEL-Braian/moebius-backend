package app.mobius.data.environmentConfiguration

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile


@Configuration(proxyBeanMethods = false)
@Profile("staging")
open class StagingConfiguration {

}