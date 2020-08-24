package app.moebius.controller

import app.moebius.domain.model.Deceased
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DeceasedController {

    @GetMapping("/deceased")
    fun getDeceased(): Deceased = TODO()
}