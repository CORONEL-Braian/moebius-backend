package app.mobius.data.di

import org.junit.jupiter.api.Test
import javax.persistence.*

class JDBCManagerTest {

    @Entity
    @Table(name = "test")
    data class SomeTest(@Id val test: String?)

    private fun getSession() =
            JDBCManager.openSession(SomeTest::class.java)

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