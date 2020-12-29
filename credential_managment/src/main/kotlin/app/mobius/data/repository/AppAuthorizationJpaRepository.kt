package app.mobius.data.repository

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

    /*@Autowired
    private lateinit var platformJpaRepository: PlatformJpaRepository*/

//    override fun isValidCredential(appConsumer: AppConsumer.AppConsumerPeople?, privateKey: String?): Boolean {
    override fun isValidCredential(): Boolean {
        val androidMobile = Platform(name = "Android", ecosystem = "Mobile")

        return entityManager.isOpen
    }

    override fun isEntityManagerOpen() = entityManager.isOpen

}

/*
@Repository
interface PlatformJpaRepository: JpaRepository<Platform, String>
*/
