package app.mobius.data.di

import org.hibernate.query.Query
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

class HibernateUtil {

//    TODO: Could not locate cfg.xml
    private val session = JDBM.Hibernate.openSession()
    private val criteriaBuilder = session.criteriaBuilder

    fun <T> exists(instance: T) {

    }

    fun <T> allTheRows(t: Class<T>) : List<T> {
        val criteriaQuery: CriteriaQuery<T> = getCriteriaQuery(t)

        val root: Root<T> = criteriaQuery.from(t)
        criteriaQuery.select(root)

        val query: Query<T> = session.createQuery(criteriaQuery)
        return query.resultList
    }

    private fun <T> getCriteriaQuery(t: Class<T>) : CriteriaQuery<T> {
        return criteriaBuilder.createQuery(t)
    }

}