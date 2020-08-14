package app.moebius.controller

import app.moebius.entity.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
) {

    @GetMapping("/user")
    fun getUser(): User = TODO()

    @PostMapping("/user")
    fun createUser() {
        TODO()
    }

}

