package app.mobius.signUp.controller

import org.springframework.web.bind.annotation.GetMapping

class PersonDtoControllerTest {

    @GetMapping(path = ["/test"])
    fun testPerson() = "Test"

   /* @Test
    fun `deserialize signUpJsonApi to JsonApiRequest`() {
        Assertions.assertEquals(
                JsonApi.writeJsonAsKtFromFile()
        )
    }*/

}