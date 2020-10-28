package app.mobius.controller

import app.mobius.domain.entity.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController {

    @GetMapping("/profile")
    fun getProfile(): Profile = TODO()

}