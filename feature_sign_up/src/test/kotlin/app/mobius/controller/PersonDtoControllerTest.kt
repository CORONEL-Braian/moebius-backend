package app.mobius.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.web.bind.annotation.GetMapping

class PersonDtoControllerTest {

    @GetMapping(path = ["/test"])
    fun testPerson() = "Test"

    @Test
    fun first() {

    }


}