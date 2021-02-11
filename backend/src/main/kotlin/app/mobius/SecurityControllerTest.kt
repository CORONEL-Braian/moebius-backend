package app.mobius

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/main")
class SecurityControllerTest {

    @GetMapping(value = ["/secure"])
    fun secure(): String {
        return "You are authorize to access this page. This is secure page. "
    }

    @GetMapping(value = ["/home"])
    fun home(): String {
        return "This is public page. No need of authentication"
    }
}
