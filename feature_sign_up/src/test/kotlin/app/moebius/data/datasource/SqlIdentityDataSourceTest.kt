package app.moebius.data.datasource

import app.mobius.data.di.JDBCManager
import app.moebius.domain.entity.Identity
import io.mockk.mockk
import org.junit.jupiter.api.Test

class SqlIdentityDataSourceTest {

    var sqlUserDataSource: SqlIdentityDataSource = SqlIdentityDataSource()
    val session = JDBCManager.openSession(annotatedClass = Identity::class.java)

    @Test
    fun `create identity`() {
        val identity = mockk<Identity>()

        JDBCManager.executeQuery(session, "Work") {
            session.save(identity)
        }
    }

}