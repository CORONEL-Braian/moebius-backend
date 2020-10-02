package app.mobius.data.di

import app.mobius.domain.entity.role.Resource
import org.hibernate.query.Query
import org.junit.jupiter.api.Test
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

class HibernateUtilTest {


    @Test
    fun exists() {
        val session = JDBM.Hibernate.openSession()

        val criteriaBuilder = session.criteriaBuilder
        val criteriaQuery: CriteriaQuery<Resource> = criteriaBuilder.createQuery(Resource::class.java)

        val root: Root<Resource> = criteriaQuery.from(Resource::class.java)
        criteriaQuery.select(root)

        val query: Query<Resource> = session.createQuery(criteriaQuery)
        val results: List<Resource> = query.resultList


        print("CRITERIA QUERY")
        results.map {
            println(it)
        }
    }

    @Test
    fun allTheRow() {
        val hibernate: HibernateUtil = HibernateUtil()

        println("CRITERIA GENERIC QUERY")
        val resources = hibernate.allTheRows(Resource::class.java)
        resources.map {
            println(it)
        }
    }

    @Test
    fun test2() {
        print("12365445")
    }

}