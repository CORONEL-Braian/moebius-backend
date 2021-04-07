package app.mobius.credentialManagment.data.repository

import app.mobius.credentialManagment.domain.entity.security.Environment
import app.mobius.credentialManagment.domain.entity.security.Platform
import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EnumType
import javax.persistence.Enumerated

interface AppAuthorizationJpaRepository: JpaRepository<Platform, UUID> {

    /**
     * Only for checek that the routines working!
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
//                    "AND environment = :#{#environment}",

//                    "AND environment = :#{#environment.name()}",    //TODO: Check possible solution: https://stackoverflow.com/a/59840623/5279996
//                  could not extract ResultSet; SQL [n/a]; nested exception is org.hibernate.exception.SQLGrammarException: could not extract ResultSet

//                    "AND environment = CAST(:#{#environment.name() as environment)}",    //TODO: Check possible solution: https://stackoverflow.com/a/59840623/5279996
//                    "AND environment = CAST(:#{#environment} as environment)",    //No function matches the given name and argument types. You might need to add explicit type casts.
//                    "AND environment = CAST('TESTING' as environment)",
            nativeQuery = true)
    fun findAppAuthorizationDeveloperUUID(
            @Param("platform") platform: Platform,
            @Param("developer") developer: String,
            @Param("environmentNamedParam") environmentParam: Environment
    ) : String

    /**
     * Use Enum parameter into JpaRepository nativeQuery: https://stackoverflow.com/a/66979684/5279996
     */
    @Query(value = "SELECT db_mobius_is_match_hash_pw_app(:#{#appAuthorizationDeveloper}, :#{#privateKey}, CAST(:#{#environmentNamedParam.name()} as environment))",
            nativeQuery = true
    )
    fun isValidAppAuthorization(
            @Param("appAuthorizationDeveloper") appAuthorizationDeveloper: UUID,
            @Param("privateKey") privateKey: String,
            @Param("environmentNamedParam") environmentParam: Environment
    ) : Boolean






//    @Query(value = "SELECT db_mobius_is_match_hash_pw_app(:#{#appAuthorizationDeveloper}, :#{#privateKey}, :#{#env.PRODUCTION})", //Failed to load ApplicationContext
//    @Query(value = "SELECT db_mobius_is_match_hash_pw_app(:#{#appAuthorizationDeveloper}, :#{#privateKey}, :#{#env})",  // Failed to load ApplicationContext
//    @Query(value = "SELECT db_mobius_is_match_hash_pw_app(:#{#appAuthorizationDeveloper}, :#{#privateKey}, :#{#env.name()})",  // Failed to load ApplicationContext
//    @Query(value = "SELECT db_mobius_is_match_hash_pw_app(:#{#appAuthorizationDeveloper}, :#{#privateKey}, CAST(:#{#env.name()} as environment))",  // Failed to load ApplicationContext

    @Query(value = "SELECT db_mobius_is_match_hash_pw_app(:#{#appAuthorizationDeveloper}, :#{#privateKey}, CAST(:#{#environmentNamedParam} as environment))",  // Failed to load ApplicationContext
            nativeQuery = true
    )
    fun isValidAppAuthorization3(
            @Param("appAuthorizationDeveloper") appAuthorizationDeveloper: UUID,
            @Param("privateKey") privateKey: String,
            @Param("environmentNamedParam") environmentParam: String
    ) : Boolean

    @Query(value = "SELECT db_mobius_is_match_hash_pw_app_2(:#{#appAuthorizationDeveloperParam}, :#{#privateKeyParam}, :#{#environmentParam})",
            nativeQuery = true
    )
    fun isValidAppAuthorization2(
            @Param("appAuthorizationDeveloperParam") appAuthorizationDeveloper: UUID,
            @Param("privateKeyParam") privateKey: String,
            @Param("environmentParam") environment: String
    ) : Boolean

    @Query(
            value = "SELECT some_routine(CAST(:#{#environmentNamedParam} as environment))",
            nativeQuery = true
    )
    fun yourFunction(
            @Param("environmentNamedParam") environmentParam: String
    ) : Boolean

}

