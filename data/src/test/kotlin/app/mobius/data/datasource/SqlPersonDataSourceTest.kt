package app.mobius.data.datasource

import app.mobius.data.di.JDBM
import org.junit.jupiter.api.Test

class SqlPersonDataSourceTest {

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
        JDBM.Hibernate.executeQuery(session) {
//            session.save(role)
        }
    }

    @Test
    fun `create identity`() {
//        val identity = Identity()
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session) {
//            session.save(identity)
        }
    }

}