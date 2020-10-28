package app.mobius

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/testMoebiusApplication")
    fun testMoebiusApplication(): Test {
        return Test("work")
    }

}

data class Test(val a: String)
