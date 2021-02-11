package app.mobius.signUp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Works in:
 *  app.mobius
 *  app.mobius.security
 *  app.mobius.security.other
 *  app.mobius.signUp
 *  app.mobius.signUp.controller
 */
@RestController
@RequestMapping("/signup")
class SecuritySignUpControllerTest {

    @GetMapping(value = ["/secure"])
    fun secure(): String {
        return "You are authorize to access this page. This is secure page. "
    }

    @GetMapping(value = ["/home"])
    fun home(): String {
        return "This is public page. No need of authentication"
    }
}
