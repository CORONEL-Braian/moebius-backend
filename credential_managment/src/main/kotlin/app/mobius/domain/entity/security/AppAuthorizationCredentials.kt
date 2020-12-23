package app.mobius.domain.entity.security

import java.util.*
import javax.persistence.*

sealed class AppAuthorization(
        @Id @GeneratedValue @Column(name = "application_uuid") val applicationUUID: UUID? = null,
        private val appConsumer: AppConsumer,   //TODO: Check if must be private
        val environment: Environment,
        val appAuthSessionToken: AppAuthSessionToken? = null,
        val hashPassword: String,
        val version: Double
) {

    constructor() : this(
            appConsumer = AppConsumer.AppConsumerPeople(),
            environment = Environment.DEV,
            hashPassword = "",
            version = 0.0
    )

    @Entity
    @Table(name = "app_authorization_people")
    object AppAuthorizationPeople : AppAuthorization()

    @Entity
    @Table(name = "app_authorization_partner")
    object AppAuthorizationPartner : AppAuthorization()

    @Entity
    @Table(name = "app_authorization_team")
    object AppAuthorizationTeam : AppAuthorization()

}


enum class Environment {
    DEV, INTEGRATION, TESTING, STAGING, PRODUCTION
}

sealed class AppConsumer {

    @Entity
    @Table(name = "consumerPeople")
    data class AppConsumerPeople(
            @Id @GeneratedValue @Column(name = "consumerIdentities_uuid") val usersUUID: UUID? = null,
            val platform: Platform
    ) : AppConsumer() {
            constructor() : this(platform = Platform())
    }

    /**
     * A partner consumes a particular feature
     */
    @Entity
    @Table(name = "consumerPartner")
    data class AppConsumerPartner(
            @Id @GeneratedValue @Column(name = "consumerPartner_uuid") val partnerUUID: UUID? = null,
            val name: String,
            val platform: Platform,
            val feature: String) : AppConsumer()

    /**
     * A team consumes a particular feature
     */
    @Entity
    @Table(name = "consumerTeam")
    data class AppConsumerTeam(
            @Id @GeneratedValue @Column(name = "consumerTeam_uuid") val teamUUID: UUID? = null,
            val name: String,
            val platform: Platform,
            val feature: String) : AppConsumer()
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
) {
    constructor() : this(name = "Android", ecosystem = "Mobile")
}