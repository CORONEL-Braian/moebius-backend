package app.mobius.data.di

import org.junit.jupiter.api.Test
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

class JDBCManagerTest {

//      ----- With Hibernate CFG  -----

    @Entity
    @Table(name = "test")
    data class SomeTest(@Id val test: String?)

    @Test
    fun `open session with hibernate cfg and only one entity`() {
        JDBCManager.HibernateCfg.openSession(SomeTest::class.java)
    }

    @Test
    fun `close session with hibernate cfg and only one entity`() {
        val session = JDBCManager.HibernateCfg.openSession(SomeTest::class.java)

//        Execute an empty operation for close session
        JDBCManager.HibernateCfg.executeQuery(session, "") {}
    }

    @Test
    fun `open session with hibernate cfg and reflextion for all mapped entities`() {
        JDBCManager.HibernateCfg.openSessionWithCfgForAll()
    }

//      ----- With Persistence  -----

    @Test
    fun `open session with JPA`() {
        JDBCManager.CustomPersistence.openSessionWithJPA(SomeTest::class.java)
    }

    @Test
    fun `create some entity with JPA`() {
        val session = JDBCManager.CustomPersistence.openSessionWithJPA(SomeTest::class.java)
        JDBCManager.CustomPersistence.executeQuery(session, "Work", SomeTest::class.java) {
            session.save(SomeTest("123"))
        }
    }



}