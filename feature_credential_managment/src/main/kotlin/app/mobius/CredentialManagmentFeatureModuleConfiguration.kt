package app.mobius

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * https://reflectoring.io/spring-boot-test/
 */

@Configuration
@EnableScheduling
@ConditionalOnProperty(
        name = ["app.mobius.CredentialManagment.enabled"],
        havingValue = "true",
        matchIfMissing = true
)
open class CredentialManagmentFeatureModuleConfiguration