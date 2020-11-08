package app.mobius.domain.entity.setting.security

import app.mobius.domain.entity.LivenessStatus
import app.mobius.domain.entity.Country
import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "identity_verification")
data class IdentityVerification(
        @Id @GeneratedValue @Column(name = "identity_verification_uuid") val identityVerificationUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "documentation_verification_uuid", referencedColumnName = "documentation_verification_uuid")
        val documentationVerification: DocumentationVerification,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "liveness_uuid", referencedColumnName = "liveness_uuid")
        val liveness: Liveness
) {
    constructor() : this(documentationVerification = DocumentationVerification(), liveness = Liveness())
}

@Entity
@Table(
        name = "documentation_verification"
)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class DocumentationVerification(
        @Id @GeneratedValue @Column(name = "documentation_verification_uuid") val documentationVerificationUUID: UUID? = null,

        @Enumerated(EnumType.STRING) @Type(type = "pgsql_enum")
        val documentationVerificationStatus: DocumentationVerificationStatus = DocumentationVerificationStatus.UNSOLICITED,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "dni_uuid", referencedColumnName = "dni_uuid")
        val dni: DNI? = null,
)

@Entity
@Table(name = "dni")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class DNI(
        @Id @GeneratedValue @Column(name = "dni_uuid") val dniUUID: UUID? = null,
        @Column(name = "surname") val surname: String,
        @Column(name = "name") val name: String,

        @Enumerated(EnumType.STRING) @Type(type = "pgsql_enum")
        val sex: Sex,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "nationality_uuid", referencedColumnName = "country_uuid")
        val nationality: Country,

        @Column(name = "ejemplar") val ejemplar: String,
        @Column(name = "birthdate") val birthdate: Date,
        @Column(name = "date_issue") val dateIssue: Date,
        @Column(name = "date_expiry") val dateExpiry: Date,
        @Column(name = "identification_number", unique = true) val identificationNumber: Int,
        @Column(name = "number", unique = true) val number: Int,


        ) {
    constructor() : this(
            surname = "",
            name = "",
            sex = Sex.F,
            nationality = Country(),
            ejemplar = "",
            birthdate = Date(),
            dateIssue = Date(),
            dateExpiry = Date(),
            identificationNumber = -1,
            number = -1
    )
}

enum class Sex {
    F, M
}

enum class DocumentationVerificationStatus {
    UNSOLICITED, PENDING, VERIFIED, BLOCKED
}

@Entity
@Table(name = "liveness")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class Liveness(
        @Id @GeneratedValue @Column(name = "liveness_uuid") val livenessUUID: UUID? = null,

        @Enumerated(EnumType.STRING) @Type(type = "pgsql_enum")
        @Column(name = "liveness_status") val livenessStatus: LivenessStatus = LivenessStatus.UNSOLICITED
)