package app.mobius.signUp.controller

import app.mobius.infrastructure.JsonApi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.web.bind.annotation.GetMapping

class PersonDtoControllerTest {

    @GetMapping(path = ["/test"])
    fun testPerson() = "Test"

    @Test
    fun first() {

    }

   /* @Test
    fun `deserialize signUpJsonApi to JsonApiRequest`() {
        Assertions.assertEquals(
                JsonApi.writeJsonAsKtFromFile()
        )
    }*/

}