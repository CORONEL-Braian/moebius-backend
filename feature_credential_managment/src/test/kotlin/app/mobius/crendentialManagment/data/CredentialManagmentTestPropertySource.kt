package app.mobius.crendentialManagment.data

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

//TODO: Migrate to Secret Manager
@Component
data class CredentialManagmentTestPropertySource(
        @Value("\${app.mobius.credentialManagment.testPropertySource.developerName}") val developerName: String? = null,
        @Value("\${app.mobius.credentialManagment.testPropertySource.privateKey}") val privateKey: String? = null
)
