package app.mobius.data.datasource

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.domain.entity.security.Platform
import org.hibernate.Session
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppAuthorizationDataSourceTest {

    private lateinit var hibernate : HibernateUtil
    private lateinit var session : Session

    @BeforeAll
    fun before() {
        hibernate = HibernateUtil()
    }

    @BeforeEach
    fun beforeEach() {
        session = JDBM.Hibernate.openSession()
    }

    @Test
    fun `when select platforms in db, then the Android Mobile platform exists`() {
        val androidMobile = Platform(name = "Android", ecosystem = "Mobile")

        JDBM.Hibernate.executeQuery(session) {
            assert(
                    hibernate.allTheRows(Platform::class.java).any {
                        androidMobile.name == it.name && androidMobile.ecosystem == it.ecosystem
                    }
            )
        }
    }

    @Test
    fun `when select platforms in db, then the iOS Mobile platform exists`() {
        val iOSMobile = Platform(name = "iOS", ecosystem = "Mobile")

        JDBM.Hibernate.executeQuery(session) {
            assert(
                    hibernate.allTheRows(Platform::class.java).any {
                        iOSMobile.name == it.name && iOSMobile.ecosystem == it.ecosystem
                    }
            )
        }
    }

    @Test
    fun `when select platforms in db, then the Web Mobile platform exists`() {
        val webMobile = Platform(name = "Web", ecosystem = "Mobile")

        JDBM.Hibernate.executeQuery(session) {
            assert(
                    hibernate.allTheRows(Platform::class.java).any {
                        webMobile.name == it.name && webMobile.ecosystem == it.ecosystem
                    }
            )
        }
    }

    @Test
    fun `when select all app consumer people in db, then it is not empty`() {

        JDBM.Hibernate.executeQuery(session) {
            assert(
                    hibernate.allTheRows(Platform::class.java).isNotEmpty()
            )
        }
    }

    @Test
    fun `when select db_mobius_is_match_hash_pw_app function, then it exists`() {
        val query = session.createNativeQuery("SELECT ${Routines.IS_MATCH_HASH_PW_APP}()")
        query.list().map {
            println("SQLQuery: $it")
        }
    }

    @Test
    fun `when checks the app authorization people with valid pw, then the match is true`() {
//    TODO
    }

}