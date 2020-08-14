package app.moebius.controller

import app.moebius.entity.Deceased
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DeceasedController {

    @GetMapping("/deceased")
    fun getDeceased() = Deceased(
            deceasedId = 1,
            dni = 123456789,
            provider = "",
            registrationDatetime = "",
            datetime = "",
            country = "",
            province = "",
            locality = "",
            observation = ""
    )

}