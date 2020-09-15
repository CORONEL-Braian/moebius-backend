package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "application")
data class Application(
        val applicationUUID: UUID,
        val environment: Environment,
        val consumer: Consumer,
        val hashPassword: String
)

enum class Environment {
    DEV, INTEGRATION, TESTING, STAGING, PRODUCTION
}

sealed class Consumer {
    data class Users(val usersUUID: UUID)

    /**
     * A partner with a certain goal
     */
    data class Partner(val partnerUUID: UUID, val name: String, val goal: String)

    /**
     * A team consumes a particular feature
     */
    data class Team(val teamUUID: UUID, val name: String, val feature: String)
}