package app.mobius.signUp.data.repository

import app.mobius.domain.entity.Profile
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

@Repository
class ProfileJpaRepository: ProfileRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun isEntityManagerOpen(): Boolean {
        return entityManager.isOpen
    }

//    TODO: Use SpringDataJpa#allTheRows
    override fun findAllProfiles(): List<Profile> {
        val cb = entityManager.criteriaBuilder
        val query: CriteriaQuery<Profile> = cb.createQuery(Profile::class.java)
        val profile: Root<Profile> = query.from(Profile::class.java)

        query.select(profile)

        return entityManager.createQuery(query).resultList
    }

}