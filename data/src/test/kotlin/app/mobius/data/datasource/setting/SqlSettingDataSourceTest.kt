package app.mobius.data.datasource.setting

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.*
import app.mobius.domain.entity.setting.Setting
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlSettingDataSourceTest {

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
    fun `create a empty setting`() {
        val setting = Setting()

        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session) {
            session.save(setting)
        }
    }

}