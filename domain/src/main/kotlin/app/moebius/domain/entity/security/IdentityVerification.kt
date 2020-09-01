package app.moebius.domain.entity.security

import app.moebius.domain.entity.location.Country
import java.util.*

data class IdentityVerification(
        val identityVerificationUUID: UUID,
        val documentationVerification: DocumentationVerification,
        val liveness: Liveness
)

data class DocumentationVerification(
        val id: Int,
        val state: StatusDocumentationVerification = StatusDocumentationVerification.UNSOLICITED,
        val dni: DNI,
)

data class DNI(
        val dniUUID: UUID,
        val surname: String,
        val name: String,
        val sex: String,
        val nationality: Country,
        val ejemplar: String,
        val birthdate: Date,
        val dateIssue: Date,
        val dateExpiry: Date,
        val identificationNumber: Int
)

enum class StatusDocumentationVerification {
    UNSOLICITED, PENDING, VERIFIED, BLOCKED
}

data class Liveness(
        val livenessUUID: UUID,
        val statusLiveness: StatusLiveness = StatusLiveness.UNSOLICITED
)

enum class StatusLiveness {
    UNSOLICITED, ALIVE, MISSING, DECEASED
}