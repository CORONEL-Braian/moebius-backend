package app.mobius.data.datasource

import app.mobius.data.di.JDBM
import app.mobius.domain.model.Identity
import app.mobius.domain.model.role.Role
import io.mockk.mockk
import org.junit.jupiter.api.Test

class SqlIdentityDataSourceTest {

    @Test
    fun `create profile`() {

    }

    @Test
    fun `create setting`() {

    }

    @Test
    fun `create role`() {
        val role = mockk<Role>()
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session, "Work") {
            session.save(role)
        }
    }

    @Test
    fun `create identity`() {
        val identity = mockk<Identity>()
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session, "Work") {
            session.save(identity)
        }
    }

}