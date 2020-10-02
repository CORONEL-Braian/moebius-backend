package app.mobius.controller

import app.mobius.domain.model.Person
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IdentityController(
) {

    @GetMapping("/identity")
    fun getIdentity(): Person = TODO()

    @PostMapping("/identity")
    fun createIdentity() {
        TODO()
    }

}
