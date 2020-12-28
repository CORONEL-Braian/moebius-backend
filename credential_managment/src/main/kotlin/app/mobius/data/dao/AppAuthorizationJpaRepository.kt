package app.mobius.data.dao

import app.mobius.data.dataAccess.hibernate.HibernateData
import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.domain.entity.security.AppConsumer
import app.mobius.domain.entity.security.Platform
import org.hibernate.Session
import org.hibernate.query.Query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

/**
 * . AUTO IMPLEMENTED by Spring into a Bean called personRepository
 * . Communicates with the SQL data source
 */
@Repository
class AppAuthorizationJpaRepository: AppAuthorizationRepository {

    @Autowired
    private lateinit var entityManager: EntityManager


    override fun isValidCredential(appConsumer: AppConsumer.AppConsumerPeople?, privateKey: String?): Boolean {

//        entityManager.createQuery("SELECT Password ")

        return true
    }

}

object TestSome {
    @JvmStatic
    fun main(args: Array<String>) {
        println(AppAuthorizationJpaRepository().isValidCredential())
    }
}