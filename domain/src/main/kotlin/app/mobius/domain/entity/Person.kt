package app.mobius.domain.entity

import app.mobius.domain.entity.profile.Profile
import app.mobius.domain.entity.role.Role
import app.mobius.domain.entity.setting.Setting
import java.util.*
import javax.persistence.*

/**
 * Be endowed with reason, self-aware and possessed of their own person
 */
@Entity
@Table(name = "person")
data class Person(
        @Id @GeneratedValue @Column(name = "person_uuid") val personUUID: UUID? = null,

        @Column(unique = true)
        val username: String,

//              TODO: Add Metadata

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "profile_uuid", referencedColumnName = "profile_uuid")
        val profile: Profile,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "setting_uuid", referencedColumnName = "setting_uuid")
        val setting: Setting,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "role_uuid", referencedColumnName = "role_uuid")
        val role: Role,

//        val identities: List<Identity>  //TODO | Next version
) {
        constructor() : this(username = "", profile = Profile(), setting = Setting(), role = Role())

        constructor(
                username: String,
                profile: Profile,
                setting: Setting
        ) : this(
                username = username,
                profile = Profile(),
                setting = Setting(),
                role = Role()
        )
}

