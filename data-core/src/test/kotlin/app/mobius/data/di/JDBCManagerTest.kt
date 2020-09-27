package app.mobius.data.di

import org.junit.jupiter.api.Test
import javax.persistence.*

class JDBCManagerTest {

    @Entity
    @Table(name = "test")
    data class SomeTest(@Id val test: String?)


    @Test
    fun `open session type 1`() {
        JDBCManager.openSession_1(SomeTest::class.java)
    }

    @Test
    fun `close session typ 1 in database`() {
        val session = JDBCManager.openSession_1(SomeTest::class.java)

//        Execute an empty operation for close session
        JDBCManager.executeQuery(session, "") {}
    }

}