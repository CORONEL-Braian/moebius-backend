package app.mobius.credentialManagment.data.repository

import app.mobius.credentialManagment.domain.entity.security.Environment
import app.mobius.credentialManagment.domain.entity.security.Platform
import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.TypeDef
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Repository
interface AppAuthorizationJpaRepository: JpaRepository<Platform, UUID> {

    @Query(value =
            "SELECT CAST(app_authorization_people_uuid as varchar) app_authorization_people_uuid FROM app_consumer_people " +
                "NATURAL JOIN platform " +
                "NATURAL JOIN app_authorization_people " +
            "WHERE name = :#{#platform.name} AND ecosystem = :#{#platform.ecosystem} AND developer = :#{#developer} AND environment = :#{#environment}" ,
            nativeQuery = true)
    fun findAppAuthorizationDeveloperUUID(
            @Param("platform") platform: Platform,
            @Param("developer") developer: String,
            @Param("environment") environment: Environment,
    ) : String


    @Query(value = "SELECT db_mobius_is_match_hash_pw_app(:#{#appAuthorizationDeveloper}, :#{#privateKey}, :#{#environment})",
            nativeQuery = true
    )
    fun isValidAppAuthorization(
            @Param("appAuthorizationDeveloper") appAuthorizationDeveloper: UUID,
            @Param("privateKey") privateKey: String,
            @Param("environment") environment: Environment
    ) : Boolean

}

