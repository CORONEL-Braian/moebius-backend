package app.moebius.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping("/user")
    fun greet(): Test {
        return Test("newUser")
    }

}

data class Test(val a: String)
