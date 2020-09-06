package app.moebius.domain.entity

import app.moebius.domain.entity.role.Role
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(
        val userUUID: UUID,
        val username: String,
        val accessToken: AccessToken,
        val account: Account,
        val profile: Profile,
        val role: Role,
)

/**
 * @param accessToken: Access token or API key to identify the user
 */
data class AccessToken(
        val tokenUUID: UUID,
        val accessToken: String,
        val created: Date,
        val expiry: Date,
)