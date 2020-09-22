package app.mobius.data.datasource

import app.mobius.data.di.JDBCManager
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

class SomeTest {

    @Entity
    @Table(name = "test")
    data class SomeTest(@Id val test: String?)

    @Test
    fun `create a mockk of Test`() {
//        Given
        val someTest = mockk<SomeTest>()
        every { someTest.test } returns "a"

//        When
        val result = someTest.test

//        Then
        verify { someTest.test }
        assertEquals("a", result)
    }

    @Test
    fun `save a mockk of Test`() {
        val someTest = SomeTest("1")
        val session = JDBCManager.openSession(annotatedClass = SomeTest::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(someTest)
        }
    }

}