package app.mobius.controller

import app.mobius.domain.model.Identity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IdentityController(
) {

    @GetMapping("/identity")
    fun getIdentity(): Identity = TODO()

    @PostMapping("/identity")
    fun createIdentity() {
        TODO()
    }

}
