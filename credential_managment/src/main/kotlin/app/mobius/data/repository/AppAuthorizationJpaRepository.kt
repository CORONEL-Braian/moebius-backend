package app.mobius.data.repository

import app.mobius.domain.entity.security.Platform
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface AppAuthorizationJpaRepository: JpaRepository<Platform, UUID> {

    @Query(value = "SELECT CAST(app_authorization_people_uuid as varchar) app_authorization_people_uuid " +
            "FROM app_consumer_people " +
            "NATURAL JOIN platform NATURAL JOIN app_authorization_people " +
            "WHERE name = :#{#platform.name} AND ecosystem = :#{#platform.ecosystem} AND developer = :#{#developer}" ,
            nativeQuery = true)
    fun findAppAuthorizationDeveloperUUID(
            @Param("platform") platform: Platform,
            @Param("developer") developer: String
    ) : String

//    fun isValidAppAuthorization(appAuthorizationDeveloper: UUID, privateKey: String) : Boolean
//    fun isValidAppAuthorization() : Boolean

}

