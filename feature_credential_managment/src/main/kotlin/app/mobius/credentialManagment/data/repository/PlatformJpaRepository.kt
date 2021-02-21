package app.mobius.credentialManagment.data.repository

import app.mobius.data.dataAccess.spring.SpringDataJpa
import app.mobius.credentialManagment.domain.entity.security.Platform
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Predicate

@Repository
class PlatformJpaRepository: PlatformRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

//    Use lazy because lateinit property entityManager has not been initialized
    private val springDataJpa by lazy { SpringDataJpa(entityManager) }

    /**
     * https://stackoverflow.com/a/65520425/5279996
     * https://stackoverflow.com/a/47604610/5279996
     */
    override fun findAndroidPlatforms() : List<Platform> {
        val cb = entityManager.criteriaBuilder

        val queryPlatform = cb.createQuery(Platform::class.java)
        val platform = queryPlatform.from(Platform::class.java)

        val predicates: MutableList<Predicate> = mutableListOf()
        predicates.add(cb.equal(platform.get<String>("name"), "Android"))
//        predicates.add(cb.equal(platform.get<String>("ecosystem"), "Mobile"))

        queryPlatform.select(platform).where(
                *predicates.map { it }.toTypedArray()
        )

        return entityManager.createQuery(queryPlatform).resultList
    }

}