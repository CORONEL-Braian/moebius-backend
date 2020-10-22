package app.mobius.data.datasource.setting.security

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.domain.entity.setting.security.Token
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlTokenDataSourceTest {

    private lateinit var hibernate: HibernateUtil
    private lateinit var session: Session

    @BeforeAll
    fun before() {
        hibernate = HibernateUtil()
    }

    @BeforeEach
    fun beforeEach() {
        session = JDBM.Hibernate.openSession()
    }

    @Test
    fun `given a token, when insert it, then create it`() {
        val token = Token()

        assertDoesNotThrow("token exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(token)) {
                    session.save(token)
                }
            }
        }
    }

}
