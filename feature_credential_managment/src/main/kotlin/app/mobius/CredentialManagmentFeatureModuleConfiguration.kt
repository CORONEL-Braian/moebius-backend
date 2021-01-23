package app.mobius

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * https://reflectoring.io/spring-boot-test/
 */
@Configuration
@ComponentScan(basePackages = ["app.mobius.credentialManagment"])
open class CredentialManagmentFeatureModuleConfiguration