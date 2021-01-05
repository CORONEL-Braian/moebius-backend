package app.mobius.controller

import app.mobius.domain.entity.security.Platform
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

    @GetMapping("/findAppAuthorizationDeveloperUUID")
    @ResponseBody
    fun findAppAuthorizationDeveloperUUID() : String {
        val platform = Platform(name = "Android", ecosystem = "Mobile")
        val developer = "Braian Coronel"
        val privateKey = "123"

//        return appAuthorizationService.isValidAppAuthorization(platform, developer, privateKey)
//        return appAuthorizationService.isValidAppAuthorization()
        return appAuthorizationService.findAppAuthorizationDeveloperUUID(platform, developer)
    }

}