package app.mobius.controller

import app.mobius.domain.model.Deceased
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DeceasedController {

    @GetMapping("/deceased")
    fun getDeceased(): Deceased = TODO()
}