package app.mobius.data.di

import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.Resource
import org.hibernate.Session
import org.hibernate.query.Query
import org.junit.jupiter.api.*
import org.springframework.util.Assert
import java.lang.Exception
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HibernateUtilTest {

    private lateinit var hibernate : HibernateUtil


    @BeforeAll
    fun before() {
        hibernate = HibernateUtil()
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

/*    @Test
    fun exists() {
        val session = JDBM.Hibernate.openSession()

        val cb = session.criteriaBuilder

        val cq: CriteriaQuery<Resource> = cb.createQuery(Resource::class.java)

        val root: Root<Resource> = cq.from(Resource::class.java)
        cq.select(root)

        cq.where(cb.equal(root.get<Resource>("name"), "/test"))

        val query: Query<Resource> = session.createQuery(cq)
        val results: List<Resource> = query.resultList

        println("@Test exists()")
        println(results.isEmpty())

        println("@Prod exists()")
        println("UNIQUE FIELDS " + hibernate.getUniqueFieldsOfColumn(Resource::class.java))
//        println(hibernate.exists(Resource(name = "/name", location = "/location")))
    }*/

/*    @Test
    fun isExistingField() {
        Assert.isTrue(
                hibernate.isExistingField(
                        Resource::class.java,
                        "name",
                        "/test"
                ),
                "")
    }*/

/*    @Test
    fun getPropertyValue() {
        val resource = Resource(name = "nameTest", location = "/location")
        println("Testing value: ${hibernate.propertyValue<String>(resource, "name")}")
    }*/



}