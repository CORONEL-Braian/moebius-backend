package app.mobius.domain.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ceremony")
data class Ceremony(
        @Id @GeneratedValue @Column(name = "ceremony_uuid") val ceremonyUUID: UUID? = null,
        val ceremonyType: CeremonyType
)

@Entity
@Table(name = "ceremony_type")
data class CeremonyType(
        @Id @GeneratedValue @Column(name = "ceremony_type_uuid") val typeUUID: UUID? = null,
        val name: String
)