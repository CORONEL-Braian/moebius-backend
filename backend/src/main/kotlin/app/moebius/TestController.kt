package app.moebius

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/greet")
    fun greet(): Test {
        return Test("asd")
    }

}

data class Test(val a: String)
