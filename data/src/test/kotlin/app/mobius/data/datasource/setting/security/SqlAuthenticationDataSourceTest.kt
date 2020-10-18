package app.mobius.data.datasource.setting.security

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
import app.mobius.domain.entity.setting.security.Authentication
import app.mobius.domain.entity.setting.security.BasicAuth
import app.mobius.domain.entity.setting.security.Password
import org.hibernate.Session
import app.mobius.domain.entity.setting.security.Session as SecuritySession
import org.junit.jupiter.api.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlAuthenticationDataSourceTest {

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
    fun `given a basicAuth and authentication, when insert authentication, then create it -- should does not throw Exception`() {
        val basicAuth = BasicAuth(
                email = "test email",
                password = Password(apiHashPassword = randomString(endIndex = 20))  // TODO: Create fun apiHashPassword(pw: String)
        )

        val authentication = Authentication(
                session = SecuritySession(),
                basicAuth = basicAuth
        )

        assertDoesNotThrow("authentication exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(authentication)) {
                    session.save(authentication)
                }
            }
        }
    }


}