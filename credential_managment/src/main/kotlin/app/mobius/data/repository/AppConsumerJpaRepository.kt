package app.mobius.data.repository

import app.mobius.data.dataAccess.spring.SpringDataJpa
import app.mobius.domain.entity.security.AppConsumer
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class AppConsumerJpaRepository : AppConsumerRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    //    Use lazy because lateinit property entityManager has not been initialized
    private val springDataJpa by lazy { SpringDataJpa(entityManager) }

    override fun findAllDevelopers() : List<AppConsumer.Developer> {
        return springDataJpa.allTheRows(AppConsumer.Developer::class.java)
    }

}