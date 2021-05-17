package app.mobius.data.datasource.setting

import app.mobius.data.dataAccess.hibernate.HibernateData
import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.domain.entity.setting.Setting
import org.hibernate.Session
import org.junit.jupiter.api.*
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlSettingDataSourceTest {

    private lateinit var hibernate : HibernateData
    private lateinit var session : Session

    @BeforeAll
    fun before() {
        hibernate = HibernateData()
    }

    @BeforeEach
    fun beforeEach() {
        session = JDBMConfig.Hibernate.openSession()
    }

    @Test
    fun `given a setting, when insert it, then create it`() {
        val setting = Setting()

        assertDoesNotThrow("setting exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(setting)) {
                    session.save(setting)
                }
            }
        }

    }

}