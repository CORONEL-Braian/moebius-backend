package app.moebius.controller

import app.moebius.domain.model.Account
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController {

    @GetMapping
    fun getAccount(): Account = TODO("Data source does not exist yet")

}