package app.mobius.domain.entity.setting.security

import app.mobius.domain.entity.LivenessStatus
import app.mobius.domain.model.Country
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "identity_verification")
data class IdentityVerification(
        @Id @GeneratedValue @Column(name = "identity_verificationUUID") val identityVerificationUUID: UUID? = null,
        val documentationVerification: DocumentationVerification,
        val liveness: Liveness
)

@Entity
@Table(name = "documentation_verification")
data class DocumentationVerification(
        @Id @GeneratedValue @Column(name = "documentation_verificationUUID") val documentationVerificationUUID: UUID? = null,
        val documentationVerificationStatus: DocumentationVerificationStatus = DocumentationVerificationStatus.UNSOLICITED,
        val dni: DNI? = null,
)

@Entity
@Table(name = "dni")
data class DNI(
        @Id @GeneratedValue @Column(name = "dniUUID") val dniUUID: UUID? = null,
        val surname: String,
        val name: String,
        val sex: String,
        val nationality: Country,
        val ejemplar: String,
        val birthdate: Date,
        val dateIssue: Date,
        val dateExpiry: Date,
        val identificationNumber: Int
) {
    constructor() : this()
}

enum class DocumentationVerificationStatus {
    UNSOLICITED, PENDING, VERIFIED, BLOCKED
}

@Entity
@Table(name = "liveness")
data class Liveness(
        @Id @GeneratedValue @Column(name = "livenessUUID") val livenessUUID: UUID? = null,
        val livenessStatus: LivenessStatus = LivenessStatus.UNSOLICITED
) {
    constructor() : this()
}