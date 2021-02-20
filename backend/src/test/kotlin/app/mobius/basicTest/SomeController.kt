package app.mobius.basicTest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping(BackendEndpoints.MOBIUS_APPLICATION_TEST)
    fun mobiusApplicationTest(): String {
        return "Hello world"
    }

}