package app.mobius.data.repository

import app.mobius.data.dataAccess.spring.SpringDataJpa
import app.mobius.domain.entity.security.AppAuthorization
import app.mobius.domain.entity.security.AppConsumer
import app.mobius.domain.entity.security.Platform
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 * . AUTO IMPLEMENTED by Spring into a Bean called personRepository
 * . Communicates with the SQL data source
 */
@Repository
class AppAuthorizationJpaRepository: AppAuthorizationRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

//    Use lazy because lateinit property entityManager has not been initialized
    private val springDataJpa by lazy { SpringDataJpa(entityManager) }

    override fun isEntityManagerOpen() = entityManager.isOpen

    override fun isValidAppAuthorization(appConsumer: AppConsumer.Developer, privateKey: String): Boolean {
        val androidMobile = Platform(name = "Android", ecosystem = "Mobile")

        return entityManager.isOpen
    }

    override fun findAllDevelopers() : List<AppAuthorization.Developer> {
        return springDataJpa.allTheRows(AppAuthorization.Developer::class.java)
    }

}