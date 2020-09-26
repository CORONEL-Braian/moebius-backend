package app.mobius.controller

import app.mobius.domain.entity.Account
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController {

    @GetMapping
    fun getAccount(): Account = TODO("Data source does not exist yet")

}