package app.mobius.data.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile


@Configuration(proxyBeanMethods = false)
@Profile("staging")
open class StagingConfiguration {

}