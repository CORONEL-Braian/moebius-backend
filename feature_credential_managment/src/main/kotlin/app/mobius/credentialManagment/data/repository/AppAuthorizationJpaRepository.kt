package app.mobius.credentialManagment.data.repository

import app.mobius.credentialManagment.domain.entity.security.Environment
import app.mobius.credentialManagment.domain.entity.security.Platform
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

/**
 * Use Enum parameter into JpaRepository nativeQuery: https://stackoverflow.com/a/66979684/5279996
 */
interface AppAuthorizationJpaRepository: JpaRepository<Platform, UUID> {

    /**
     * Only for check that the routines working!
     * OBS: This would have helped not to debug tests unnecessarily. When in reality what was not working was the class.
     */
    @Query(value = "SELECT db_mobius_simple_routine_for_testing()", nativeQuery = true)
    fun simpleRoutineForTesting() : Boolean

    @Query(value =
            "SELECT CAST(app_authorization_people_uuid as varchar) app_authorization_people_uuid FROM app_consumer_people " +
                "NATURAL JOIN platform " +
                "NATURAL JOIN app_authorization_people " +
            "WHERE name = :#{#platform.name} " +
                    "AND ecosystem = :#{#platform.ecosystem} " +
                    "AND developer = :#{#developer} " +
                    "AND environment = CAST(:#{#environmentNamedParam.name()} as environment)",
            nativeQuery = true)
    fun findAppAuthorizationDeveloperUUID(
            @Param("platform") platform: Platform,
            @Param("developer") developer: String,
            @Param("environmentNamedParam") environmentParam: Environment
    ) : String

    @Query(value = "SELECT db_mobius_is_match_hash_pw_app(:#{#appAuthorizationDeveloper}, :#{#privateKey}, CAST(:#{#environmentNamedParam.name()} as environment))",
            nativeQuery = true
    )
    fun isValidAppAuthorization(
            @Param("appAuthorizationDeveloper") appAuthorizationDeveloper: UUID,
            @Param("privateKey") privateKey: String,
            @Param("environmentNamedParam") environmentParam: Environment
    ) : Boolean

}