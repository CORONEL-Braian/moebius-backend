package app.mobius.data.datasource.setting.security

import app.mobius.data.dataAccess.hibernate.HibernateData
import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.domain.entity.setting.security.*
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlSecurityDataSourceTest {

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
    fun `given a default security, when insert security, then create it`() {
        val security = Security()

        assertDoesNotThrow("security exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(security)) {
                    session.save(security)
                }
            }
        }
    }

    @Test
    fun `given a securityMethods empty and security, when insert security, then create it`() {
        val security = Security(authentication = Authentication(), securityMethods = SecurityMethods())

        assertDoesNotThrow("security exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(security)) {
                    session.save(security)
                }
            }
        }
    }

}