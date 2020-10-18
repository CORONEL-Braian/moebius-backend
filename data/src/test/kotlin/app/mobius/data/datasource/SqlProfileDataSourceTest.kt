package app.mobius.data.datasource

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.domain.entity.Profile as PersonProfile
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlProfileDataSourceTest {

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
    fun `given a profile, when insert it, then create it -- should does not throw Exception`() {
        val profile = PersonProfile()

        assertDoesNotThrow("setting exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(profile)) {
                    session.save(profile)
                }
            }
        }

    }

}