package app.mobius.controller

import app.mobius.domain.entity.security.AppAuthorization
import app.mobius.domain.entity.security.AppConsumer
import app.mobius.service.AppAuthorizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

// TODO: Migrate this controller to AppAuthorizationServiceTest

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

    @GetMapping("/findAllAppAuthorizationDevelopers")
    @ResponseBody
    fun findAllAppAuthorizationDevelopers() : List<AppAuthorization.Developer> {
        return appAuthorizationService.findAllAppAuthorizationDevelopers()
    }

    @GetMapping("/findAllAppConsumerDevelopers")
    @ResponseBody
    fun findAllAppConsumerDevelopers() : List<AppConsumer.Developer> {
        return appAuthorizationService.findAllAppConsumerDevelopers()
    }

}