package app.mobius.security.authentication.controllerTest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/security")
class SecurityDigestAuthController {

    @GetMapping(value = ["/secure"])
    fun secure(): String {
        return "You are authorize to access this page. This is secure page. "
    }

    @GetMapping(value = ["/home"])
    fun home(): String {
        return "This is public page. No need of authentication"
    }
}
