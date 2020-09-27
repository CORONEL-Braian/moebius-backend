package app.mobius.data.di

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.FileInputStream
import java.io.InputStream
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

class JDBCManagerTest {

//      ----- With Hibernate CFG  -----

    @Entity
    @Table(name = "test")
    data class SomeTest(@Id val test: String?)

    @Test
    fun `get input stream for configuration of session factory`() {
        val currentWorkingDir = System.getProperty("user.dir")
        val absoulutePath = "$currentWorkingDir/src/main/resources/secret-hibernate.cfg.xml"
        val targetStream: InputStream = FileInputStream(absoulutePath)
        Assertions.assertNotEquals(targetStream, null)
    }

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
        JDBCManager.CustomPersistence.openSessionWithJPA()
    }

    @Test
    fun `create some entity with JPA`() {
        val session = JDBCManager.CustomPersistence.openSessionWithJPA()
        JDBCManager.CustomPersistence.executeQuery(session, ) {
            session.save(SomeTest("123"))
        }
    }



}