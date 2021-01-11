package app.mobius.data.dataAccess.spring

import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

open class SpringDataJpa(val entityManager: EntityManager) {

    fun <T> allTheRows(t: Class<T>) : List<T> {
        val criteriaBuilder = entityManager.criteriaBuilder
        val query: CriteriaQuery<T> = criteriaBuilder.createQuery(t)
        val root: Root<T> = query.from(t)

        query.select(root)
        return entityManager.createQuery(query).resultList
    }

}