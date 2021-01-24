package app.mobius.securityCore.controllerTest

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/security")
class SecurityDigestAuthController {

    @PostMapping("/digestAuth")
    @ResponseBody
    fun digestAuth(
            @RequestBody any: Any
    ) : String {
        return "Success"
    }
}
