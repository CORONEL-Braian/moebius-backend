package app.mobius.domain.model.security

import app.mobius.domain.model.Country
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "identity_verification")
data class IdentityVerification(
        @Id @GeneratedValue @Column(name = "identity_verification_uuid") val identityVerificationUUID: UUID? = null,
        val documentationVerification: DocumentationVerification,
        val liveness: Liveness
)

@Entity
@Table(name = "documentation_verification")
data class DocumentationVerification(
        @Id @GeneratedValue @Column(name = "documentation_verification_uuid") val documentationVerificationUUID: UUID? = null,
        val statusDocumentationVerification: StatusDocumentationVerification = StatusDocumentationVerification.UNSOLICITED,
        val dni: DNI? = null,
)

@Entity
@Table(name = "dni")
data class DNI(
        @Id @GeneratedValue @Column(name = "dni_uuid") val dniUUID: UUID? = null,
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
        @Id @GeneratedValue @Column(name = "liveness_uuid") val livenessUUID: UUID? = null,
        val statusLiveness: StatusLiveness = StatusLiveness.UNSOLICITED
)

enum class StatusLiveness {
    UNSOLICITED, ALIVE, MISSING, DECEASED
}