package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "third_profile")
data class ThirdProfile(
        val thirdProfileUUID: UUID
)