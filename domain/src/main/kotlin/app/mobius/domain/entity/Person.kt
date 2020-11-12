package app.mobius.domain.entity

import app.mobius.domain.entity.role.Role
import app.mobius.domain.entity.setting.Setting
import io.crnk.core.resource.annotations.JsonApiId
import io.crnk.core.resource.annotations.JsonApiResource
import java.util.*
import javax.persistence.*

/**
 * Be endowed with reason, self-aware and possessed of their own person
 */
@JsonApiResource(type = "people")
@Entity
@Table(name = "person")
data class Person(
        @JsonApiId @Id @GeneratedValue @Column(name = "person_uuid") val personUUID: UUID? = null,

        val username: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "profile_uuid", referencedColumnName = "profile_uuid")
        val profile: Profile,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "setting_uuid", referencedColumnName = "setting_uuid")
        val setting: Setting,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "role_uuid", referencedColumnName = "role_uuid")
        val role: Role,
) {
        constructor() : this(username = "", profile = Profile(), setting = Setting(), role = Role())
}

