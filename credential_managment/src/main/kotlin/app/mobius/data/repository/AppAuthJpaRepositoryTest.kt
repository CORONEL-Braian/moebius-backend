package app.mobius.data.repository

import app.mobius.domain.entity.security.Platform
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface AppAuthJpaRepositoryTest: JpaRepository<Platform, UUID> {

    @Query(value = "SELECT CAST(platform_uuid as varchar) platform_uuid, CAST(app_consumer_people_uuid as varchar) app_consumer_people_uuid FROM platform NATURAL JOIN app_consumer_people", nativeQuery = true)
    fun findAllPlatforms(): List<Any>
//    TODO: Return object in relation with SELECT

}

