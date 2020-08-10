package app.moebius

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/greet")
    fun greet(): String {
        return "Hello, World"
    }

}