package app.mobius

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SomeControllerTest {

    @GetMapping("/mobiusApplicationTest2")
    fun mobiusApplicationTest(): String {
        return "Hello world"
    }

}
