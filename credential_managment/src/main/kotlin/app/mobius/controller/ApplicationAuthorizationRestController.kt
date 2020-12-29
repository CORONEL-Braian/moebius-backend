package app.mobius.controller

import app.mobius.service.AppAuthorizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/appAuthorization")
class ApplicationAuthorizationRestController {

    @Autowired
    private lateinit var appAuthorizationService: AppAuthorizationService

    @GetMapping("/isOpen")
    @ResponseBody
    fun isOpen() : Boolean {
        return appAuthorizationService.isOpen()
    }

}