package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "ceremony")
data class Ceremony(
        val ceremonyUUID: UUID,
        val ceremonyType: CeremonyType
)

@Entity
@Table(name = "ceremony_type")
data class CeremonyType(
        val typeUUID: UUID,
        val name: String
)