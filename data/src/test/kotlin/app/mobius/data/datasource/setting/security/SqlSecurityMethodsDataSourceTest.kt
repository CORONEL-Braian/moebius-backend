package app.mobius.data.datasource.setting.security

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.domain.entity.setting.security.*
import org.hibernate.Session
import org.junit.jupiter.api.*
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlSecurityMethodsDataSourceTest {

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
    fun `given a empty securityMethods, when insert it, then create it with null methods -- should does not throw Exception`() {
        val securityMethods = SecurityMethods()

        assertDoesNotThrow("securityMethods exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

    @Test
    fun `given a identityVerification, when insert securityMethods, then create it -- should does not throw Exception`() {
        val securityMethods = SecurityMethods(
                identityVerification = IdentityVerification()
        )

        assertDoesNotThrow("securityMethods exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

    @Test
    fun `given a 2FA with email pending and securityMethods, when insert securityMethods, then create it -- should does not throw Exception`() {
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
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

    @Test
    fun `given a antiPishingCode, when insert securityMethods, then create it -- should does not throw Exception`() {
        val securityMethods = SecurityMethods(
                antiPishingCode = AntiPishingCode()
        )

        assertDoesNotThrow("securityMethods exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

    @Test
    fun `given a identityVerification, twoFactorAuth and antiPishingCode, when insert securityMethods, then create it -- should does not throw Exception`() {
        val securityMethods = SecurityMethods(
                antiPishingCode = AntiPishingCode()
        )

        assertDoesNotThrow("securityMethods exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

    @Test
    fun `given a securityMethods, when insert it, then create it -- should does not throw Exception`() {
        val securityMethods = SecurityMethods()

        assertDoesNotThrow("securityMethods exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(securityMethods)) {
                    session.save(securityMethods)
                }
            }
        }
    }

}