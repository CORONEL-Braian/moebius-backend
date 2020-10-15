package app.mobius.domain.model

import java.util.*
import javax.persistence.*

/**
 * Map with:
 *  . AppConsumerUsers
 *  . AppConsumerPartner
 *  . AppConsumerOrganization
 */
@Entity
@Table(name = "application")
data class Application(
        @Id @GeneratedValue @Column(name = "application_uuid") val applicationUUID: UUID? = null,
        val environment: Environment,
        val consumer: Consumer,
        val publicKey: String
)

enum class Environment {
    DEV, INTEGRATION, TESTING, STAGING, PRODUCTION
}


sealed class Consumer {
    @Entity
    @Table(name = "consumerPeople")
    data class Identities(
            @Id @GeneratedValue @Column(name = "consumerIdentities_uuid") val usersUUID: UUID? = null,
            val platform: Platform)

    /**
     * A partner consumes a particular feature
     */
    @Entity
    @Table(name = "consumerPartner")
    data class Partner(
            @Id @GeneratedValue @Column(name = "consumerPartner_uuid") val partnerUUID: UUID? = null,
            val name: String,
            val platform: Platform,
            val feature: String)

    /**
     * A team consumes a particular feature
     */
    @Entity
    @Table(name = "consumerTeam")
    data class Team(
            @Id @GeneratedValue @Column(name = "consumerTeam_uuid") val teamUUID: UUID? = null,
            val name: String,
            val platform: Platform,
            val feature: String)
}

@Entity
@Table(name = "platform")
data class Platform(
        @Id @GeneratedValue @Column(name = "platform_uuid") val platformUUID: UUID? = null,
        val name: String,
        val ecosystem: String
)