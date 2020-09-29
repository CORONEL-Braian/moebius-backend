package app.mobius.data.datasource.role

import app.mobius.data.di.JDBM
import app.mobius.domain.entity.role.Role
import org.junit.jupiter.api.Test

class SqlRoleDataSourceTest {

    @Test
    fun `create a role`() {
        val role = Role()

        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session) {
            session.save(role)
        }
    }

}