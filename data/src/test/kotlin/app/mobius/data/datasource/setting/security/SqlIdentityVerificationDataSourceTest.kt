package app.mobius.data.datasource.setting.security

import app.mobius.data.dataAccess.hibernate.HibernateData
import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.domain.entity.setting.security.DocumentationVerification
import app.mobius.domain.entity.setting.security.IdentityVerification
import app.mobius.domain.entity.setting.security.Liveness
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlIdentityVerificationDataSourceTest {

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
    fun `given a liveness, when insert it, then create it`() {
        val liveness = Liveness()

        assertDoesNotThrow("liveness exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(liveness)) {
                    session.save(liveness)
                }
            }
        }
    }

    @Test
    fun `given a documentationVerification, when insert it, then create it`() {
        val documentationVerification = DocumentationVerification()

        assertDoesNotThrow("documentationVerification exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(documentationVerification)) {
                    session.save(documentationVerification)
                }
            }
        }

    }

    @Test
    fun `given a identityVerification, when insert it, then create it`() {
        val identityVerification = IdentityVerification()

        assertDoesNotThrow("identityVerification exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(identityVerification)) {
                    session.save(identityVerification)
                }
            }
        }

    }

}