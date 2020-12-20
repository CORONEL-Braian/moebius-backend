package app.mobius.domain.entity

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
        val publicKey: String,
        val version: Double,
)

enum class Environment {
    DEV, INTEGRATION, TESTING, STAGING, PRODUCTION
}


sealed class Consumer {

    @Entity
    @Table(name = "consumerPeople")
    data class ConsumerPeople(
            @Id @GeneratedValue @Column(name = "consumerIdentities_uuid") val usersUUID: UUID? = null,
            val platform: Platform) : Consumer()

    /**
     * A partner consumes a particular feature
     */
    @Entity
    @Table(name = "consumerPartner")
    data class ConsumerPartner(
            @Id @GeneratedValue @Column(name = "consumerPartner_uuid") val partnerUUID: UUID? = null,
            val name: String,
            val platform: Platform,
            val feature: String) : Consumer()

    /**
     * A team consumes a particular feature
     */
    @Entity
    @Table(name = "consumerTeam")
    data class ConsumerTeam(
            @Id @GeneratedValue @Column(name = "consumerTeam_uuid") val teamUUID: UUID? = null,
            val name: String,
            val platform: Platform,
            val feature: String) : Consumer()
}

/**
 * @param name: i.e: Android
 * @param ecosystem: i.e: Auto, TV, Wear OS
 */
@Entity
@Table(name = "platform")
data class Platform(
        @Id @GeneratedValue @Column(name = "platform_uuid") val platformUUID: UUID? = null,
        val name: String,
        val ecosystem: String
)