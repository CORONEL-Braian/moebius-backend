package app.mobius.data.datasource.setting.security

import app.mobius.data.dataAccess.hibernate.HibernateData
import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.domain.entity.setting.security.*
import org.hibernate.Session
import org.junit.jupiter.api.*
import java.util.*
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlSecurityMethodsDataSourceTest {

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
    fun `given a empty securityMethods, when insert it, then create it with null methods`() {
        val securityMethods = SecurityMethods()

        assertDoesNotThrow("securityMethods exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

    @Test
    fun `given a identityVerification, when insert securityMethods, then create it`() {
        val securityMethods = SecurityMethods(
                identityVerification = IdentityVerification()
        )

        assertDoesNotThrow("securityMethods exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

    @Test
    fun `given a 2FA with email pending and securityMethods, when insert securityMethods, then create it`() {
        val twoFactorAuth = TwoFactorAuth(
                googleAuthentication = GoogleAuth(),
                smsAuthentication = SMSAuthentication(),
                emailVerification = EmailVerification(
                        emailVerificationStatus = EmailVerificationStatus.PENDING,
                        token = Token(created = Date(), expiry = Date())
                )
        )
        val securityMethods = SecurityMethods(
                twoFactorAuth = twoFactorAuth
        )

        assertDoesNotThrow("securityMethods exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

    @Test
    fun `given a antiPishingCode, when insert securityMethods, then create it`() {
        val securityMethods = SecurityMethods(
                antiPishingCode = AntiPishingCode()
        )

        assertDoesNotThrow("securityMethods exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

    @Test
    fun `given a identityVerification, twoFactorAuth and antiPishingCode, when insert securityMethods, then create it`() {
        val securityMethods = SecurityMethods(
                antiPishingCode = AntiPishingCode()
        )

        assertDoesNotThrow("securityMethods exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

    @Test
    fun `given a securityMethods, when insert it, then create it`() {
        val securityMethods = SecurityMethods()

        assertDoesNotThrow("securityMethods exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

}