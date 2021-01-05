package app.mobius.service

import app.mobius.data.repository.AppAuthorizationJpaRepository
import app.mobius.domain.entity.security.AppConsumer
import app.mobius.domain.entity.security.Platform
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AppAuthorizationService {

    @Autowired
    private lateinit var appAuthorizationJpaRepository: AppAuthorizationJpaRepository

    fun findAppAuthorizationDeveloperUUID(platform: Platform, developer: String) : String {
//        return appAuthorizationJpaRepository.findAppAuthorizationDeveloperUUID(platform, developer)
        appAuthorizationJpaRepository.findAppAuthorizationDeveloperUUID(platform, developer)
        return ""
    }

    /**
     * @param privateKey: Password to validate
     */
/*    fun isValidAppAuthorization(platform: Platform, developer: String, privateKey: String) : Boolean {
        return appAuthorizationJpaRepository.isValidAppAuthorization(
                findAppAuthorizationDeveloperUUID(platform, developer),
                privateKey
        )
    }*/

     fun isValidAppAuthorization() : Boolean {
         return true
     }

}
