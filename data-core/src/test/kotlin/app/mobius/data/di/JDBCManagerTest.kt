package app.mobius.data.di

import org.junit.jupiter.api.Test
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

class JDBCManagerTest {

    @Entity
    @Table(name = "test")
    data class SomeTest(@Id val test: String?)

    private fun getSession() =
            JDBCManager.openSession("secret-hibernate.cfg.xml", SomeTest::class.java)

    @Test
    fun `open session in database`() {
        getSession()
    }

    @Test
    fun `close session in database`() {
//        Execute an empty operation for close session
        JDBCManager.executeQuery(getSession(), "") {}
    }

}