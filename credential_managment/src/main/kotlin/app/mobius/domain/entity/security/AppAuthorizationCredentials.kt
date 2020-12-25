package app.mobius.domain.entity.security

import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

sealed class AppAuthorization(
        open val appAuthorizationUUID: UUID? = null,
        open val appConsumer: AppConsumer,

        @Enumerated(EnumType.STRING) @Type(type = "pgsql_enum") private val environment: Environment,
        @Column(name = "db_hash_pw") private val hashPassword: String,
        private val version: Double,
        private val sessionToken: String? = null,
) {

    constructor() : this(
            appConsumer = AppConsumer.AppConsumerPeople(),
            environment = Environment.DEV,
            hashPassword = "",
            version = 0.0,
    )

    @Entity
    @Table(name = "app_authorization_people")
    @TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
    data class AppAuthorizationPeople(
            @Id @GeneratedValue @Column(name = "app_authorization_people_uuid")
            override val appAuthorizationUUID: UUID? = null,

            @OneToOne(cascade = [CascadeType.ALL])
            @JoinColumn(name = "app_consumer_people_uuid", referencedColumnName = "app_consumer_people_uuid")
            override val appConsumer: AppConsumer.AppConsumerPeople,
    ) : AppAuthorization()

/*    @Entity
    @Table(name = "app_authorization_partner")
    object AppAuthorizationPartner : AppAuthorization()

    @Entity
    @Table(name = "app_authorization_team")
    object AppAuthorizationTeam : AppAuthorization()*/

}


enum class Environment {
    DEV, INTEGRATION, TESTING, STAGING, PRODUCTION
}

sealed class AppConsumer {

    @Entity
    @Table(name = "app_consumer_people")
    data class AppConsumerPeople(
            @Id @GeneratedValue @Column(name = "app_consumer_people_uuid") val appConsumerPeopleUUID: UUID? = null,

            @OneToOne(cascade = [CascadeType.ALL])
            @JoinColumn(name = "platform_uuid", referencedColumnName = "platform_uuid")
            val platform: Platform
    ) : AppConsumer() {
        constructor() : this(platform = Platform())
    }


//    A partner consumes a particular feature
    @Entity
    @Table(name = "app_consumer_partner")
    data class AppConsumerPartner(
            @Id @GeneratedValue @Column(name = "app_consumer_partner_uuid") val appConsumerPartnerUUID: UUID? = null,

            val name: String,

            @OneToOne(cascade = [CascadeType.ALL])
            @JoinColumn(name = "platform_uuid", referencedColumnName = "platform_uuid")
            val platform: Platform,

            @OneToOne(cascade = [CascadeType.ALL])
            @JoinColumn(name = "feature_uuid", referencedColumnName = "feature_uuid")
            val feature: Feature
    ) : AppConsumer() {
    constructor() : this(name = "", platform = Platform(), feature = Feature())
    }

//      A team consumes a particular feature
    @Entity
    @Table(name = "app_consumer_team")
    data class AppConsumerTeam(
            @Id @GeneratedValue @Column(name = "app_consumer_team_uuid") val appConsumerTeamUUID: UUID? = null,
            val name: String,

            @OneToOne(cascade = [CascadeType.ALL])
            @JoinColumn(name = "platform_uuid", referencedColumnName = "platform_uuid")
            val platform: Platform,

            @OneToOne(cascade = [CascadeType.ALL])
            @JoinColumn(name = "feature_uuid", referencedColumnName = "feature_uuid")
            val feature: Feature
    ) : AppConsumer() {
        constructor() : this(name = "", platform = Platform(), feature = Feature())
    }
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

@Entity
@Table(name = "feature")
data class Feature(
        @Id @GeneratedValue @Column(name = "feature_uuid") val featureUUID: UUID? = null,
        val name: String,
) {
    constructor() : this(name = "Sign Up")
}
