package app.mobius.data.datasource.setting.security

import app.mobius.data.dataAccess.hibernate.HibernateData
import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.data.util.randomString
import app.mobius.domain.entity.setting.security.Authentication
import app.mobius.domain.entity.setting.security.BasicAuth
import app.mobius.domain.entity.setting.security.Password
import org.hibernate.Session
import app.mobius.domain.entity.setting.security.Session as SecuritySession
import org.junit.jupiter.api.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlAuthenticationDataSourceTest {

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
    fun `given a basicAuth and authentication, when insert authentication, then create it`() {
        val basicAuth = BasicAuth(
                email = "test email",
                password = Password(apiHashPassword = randomString(endIndex = 20))  // TODO: Create fun apiHashPassword(pw: String)
        )

        val authentication = Authentication(
                session = SecuritySession(),
                basicAuth = basicAuth
        )

        assertDoesNotThrow("authentication exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(authentication)) {
                    session.save(authentication)
                }
            }
        }
    }


}