package app.moebius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "third_profile")
data class ThirdProfile(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val thirdProfileUUID: UUID
)