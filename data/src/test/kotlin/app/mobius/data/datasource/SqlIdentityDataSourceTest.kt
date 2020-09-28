package app.mobius.data.datasource

import app.mobius.data.di.JDBM
import app.mobius.domain.model.Identity
import app.mobius.domain.entity.role.Role
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
//        val role = Role()
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session, "Work") {
//            session.save(role)
        }
    }

    @Test
    fun `create identity`() {
//        val identity = Identity()
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session, "Work") {
//            session.save(identity)
        }
    }

}