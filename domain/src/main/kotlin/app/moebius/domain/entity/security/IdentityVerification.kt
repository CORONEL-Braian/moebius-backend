package app.moebius.domain.entity.security

import app.moebius.domain.entity.Country
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "identity_verification")
data class IdentityVerification(
        @Id val identityVerificationUUID: UUID,
        val documentationVerification: DocumentationVerification,
        val liveness: Liveness
)

@Entity
@Table(name = "documentation_verification")
data class DocumentationVerification(
        @Id val documentationVerificationUUID: UUID,
        val statusDocumentationVerification: StatusDocumentationVerification = StatusDocumentationVerification.UNSOLICITED,
        val dni: DNI? = null,
)

@Entity
@Table(name = "dni")
data class DNI(
        @Id val dniUUID: UUID,
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

@Entity
@Table(name = "liveness")
data class Liveness(
        @Id val livenessUUID: UUID,
        val statusLiveness: StatusLiveness = StatusLiveness.UNSOLICITED
)

enum class StatusLiveness {
    UNSOLICITED, ALIVE, MISSING, DECEASED
}