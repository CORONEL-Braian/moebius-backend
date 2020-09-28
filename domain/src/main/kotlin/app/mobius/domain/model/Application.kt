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
        @Id @GeneratedValue @Column(name = "application_uuid") val applicationUUID: UUID,
        val environment: Environment,
        val consumer: Consumer,
        val publicKey: String
)

enum class Environment {
    DEV, INTEGRATION, TESTING, STAGING, PRODUCTION
}


sealed class Consumer {
    @Entity
    @Table(name = "consumer_identities")
    data class Identities(
            @Id @GeneratedValue @Column(name = "consumer_identities_uuid") val usersUUID: UUID,
            val platform: Platform)

    /**
     * A partner consumes a particular feature
     */
    @Entity
    @Table(name = "consumer_partner")
    data class Partner(
            @Id @GeneratedValue @Column(name = "consumer_partner_uuid") val partnerUUID: UUID,
            val name: String,
            val platform: Platform,
            val feature: String)

    /**
     * A team consumes a particular feature
     */
    @Entity
    @Table(name = "consumer_team")
    data class Team(
            @Id @GeneratedValue @Column(name = "consumer_team_uuid") val teamUUID: UUID,
            val name: String,
            val platform: Platform,
            val feature: String)
}

@Entity
@Table(name = "platform")
data class Platform(
        @Id @GeneratedValue @Column(name = "platform_uuid") val platformUUID: UUID,
        val name: String,
        val ecosystem: String
)