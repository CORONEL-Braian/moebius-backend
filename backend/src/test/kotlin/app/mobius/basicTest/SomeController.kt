package app.mobius.basicTest

import app.mobius.security.SecurityCoreEndpoints
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping(SecurityCoreEndpoints.Keys.MOBIUS_APPLICATION_TEST)
    fun mobiusApplicationTest(): String {
        return "Hello world"
    }

}