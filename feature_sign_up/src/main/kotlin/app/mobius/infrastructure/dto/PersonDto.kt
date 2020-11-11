package app.mobius.infrastructure.dto

import app.mobius.domain.entity.Profile
import java.util.*
import javax.persistence.*

data class PersonDto(
        @Id @GeneratedValue @Column(name = "person_uuid") val personUUID: UUID? = null,

        val username: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "profile_uuid", referencedColumnName = "profile_uuid")
        val profile: Profile,
) {
    constructor() : this(username = "", profile = Profile())
}