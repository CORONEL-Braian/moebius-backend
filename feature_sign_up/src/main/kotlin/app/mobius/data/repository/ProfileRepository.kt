package app.mobius.data.repository

import app.mobius.domain.entity.Profile
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root


interface ProfileRepository {
    fun getProfiles(): List<Profile>
    fun isEntityManagerOpen(): Boolean
}

@Repository
class ProfileJpaRepository: ProfileRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager


    override fun isEntityManagerOpen(): Boolean {
        return entityManager.isOpen
    }

    override fun getProfiles(): List<Profile> {
        val cb = entityManager.criteriaBuilder
        val query: CriteriaQuery<Profile> = cb.createQuery(Profile::class.java)
        val user: Root<Profile> = query.from(Profile::class.java)

        query.select(user)

        return entityManager.createQuery(query).resultList
    }

}