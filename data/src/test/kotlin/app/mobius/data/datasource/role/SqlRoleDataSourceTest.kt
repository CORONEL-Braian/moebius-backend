package app.mobius.data.datasource.role

import app.mobius.data.di.JDBM
import app.mobius.domain.entity.role.Role
import org.junit.jupiter.api.Test

class SqlRoleDataSourceTest {

    @Test
    fun `create a resource`() {
//        val role = Role()

        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session, "Work") {
//            session.save(role)
        }
    }

}