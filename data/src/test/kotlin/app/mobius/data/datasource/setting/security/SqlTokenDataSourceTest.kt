package app.mobius.data.datasource.setting.security

import app.mobius.data.dataAccess.hibernate.HibernateData
import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.domain.entity.setting.security.Token
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlTokenDataSourceTest {

    private lateinit var hibernate: HibernateData
    private lateinit var session: Session

    @BeforeAll
    fun before() {
        hibernate = HibernateData()
    }

    @BeforeEach
    fun beforeEach() {
        session = JDBMConfig.Hibernate.openSession()
    }

    @Test
    fun `given a token, when insert it, then create it`() {
        val token = Token()

        assertDoesNotThrow("token exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(token)) {
                    session.save(token)
                }
            }
        }
    }

}
