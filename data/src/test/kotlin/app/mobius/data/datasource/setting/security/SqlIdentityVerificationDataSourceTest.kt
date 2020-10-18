package app.mobius.data.datasource.setting.security

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.domain.entity.setting.security.DocumentationVerification
import app.mobius.domain.entity.setting.security.IdentityVerification
import app.mobius.domain.entity.setting.security.Liveness
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlIdentityVerificationDataSourceTest {

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
    fun `given a liveness, when insert it, then create it -- should does not throw Exception`() {
        val liveness = Liveness()

        assertDoesNotThrow("liveness exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(liveness)) {
                    session.save(liveness)
                }
            }
        }
    }

    @Test
    fun `given a documentationVerification, when insert it, then create it -- should does not throw Exception`() {
        val documentationVerification = DocumentationVerification()

        assertDoesNotThrow("documentationVerification exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(documentationVerification)) {
                    session.save(documentationVerification)
                }
            }
        }

    }

    @Test
    fun `given a identityVerification, when insert it, then create it -- should does not throw Exception`() {
        val identityVerification = IdentityVerification()

        assertDoesNotThrow("identityVerification exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(identityVerification)) {
                    session.save(identityVerification)
                }
            }
        }

    }

}