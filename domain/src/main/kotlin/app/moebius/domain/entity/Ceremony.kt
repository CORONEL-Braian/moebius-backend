package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ceremony")
data class Ceremony(
        @Id val ceremonyUUID: UUID,
        val ceremonyType: CeremonyType
)

@Entity
@Table(name = "ceremony_type")
data class CeremonyType(
        @Id val typeUUID: UUID,
        val name: String
)