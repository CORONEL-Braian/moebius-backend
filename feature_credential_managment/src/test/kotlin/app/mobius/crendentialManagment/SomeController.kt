package app.mobius.crendentialManagment

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SomeControllerTest {

    @GetMapping("/mobiusApplicationTest")
    fun mobiusApplicationTest(): String {
        return "Hello world"
    }

}