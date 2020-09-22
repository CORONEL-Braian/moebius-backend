package app.moebius.domain.entity

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
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val applicationUUID: UUID,
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
            @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val usersUUID: UUID,
            val platform: Platform)

    /**
     * A partner consumes a particular feature
     */
    @Entity
    @Table(name = "consumer_partner")
    data class Partner(
            @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val partnerUUID: UUID,
            val name: String,
            val platform: Platform,
            val feature: String)

    /**
     * A team consumes a particular feature
     */
    @Entity
    @Table(name = "consumer_team")
    data class Team(
            @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val teamUUID: UUID,
            val name: String,
            val platform: Platform,
            val feature: String)
}

@Entity
@Table(name = "platform")
data class Platform(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val platformUUID: UUID,
        val name: String,
        val ecosystem: String
)