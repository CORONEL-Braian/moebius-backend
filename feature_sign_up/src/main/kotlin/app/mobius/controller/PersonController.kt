package app.mobius.controller

import app.mobius.domain.entity.Person
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(
) {

    @GetMapping("/person")
    fun getPerson(): Person = TODO()

    @PostMapping("/person")
    fun createPerson() {
        TODO()
    }

}
