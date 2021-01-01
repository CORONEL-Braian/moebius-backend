package app.mobius.service

import app.mobius.data.repository.AppAuthorizationRepository
import app.mobius.data.repository.AppConsumerRepository
import app.mobius.domain.entity.security.AppAuthorization
import app.mobius.domain.entity.security.AppConsumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppAuthorizationService {

    @Autowired
    private lateinit var appAuthorizationRepository: AppAuthorizationRepository

    @Autowired
    private lateinit var appConsumerRepository: AppConsumerRepository

    fun isOpen() : Boolean {
        return appAuthorizationRepository.isEntityManagerOpen()
    }

    /**
     * @param privateKey: Password to validate
     */
    fun isValidAppAuthorization(appConsumer: AppConsumer.Developer, privateKey: String) : Boolean {
        return appAuthorizationRepository.isValidAppAuthorization(appConsumer, privateKey)
    }

    fun findAllAppAuthorizationDevelopers(): List<AppAuthorization.Developer> {
        return appAuthorizationRepository.findAllDevelopers()
    }

    fun findAllAppConsumerDevelopers() : List<AppConsumer.Developer> {
        return appConsumerRepository.findAllDevelopers()
    }

    fun findAppAuthorizationDeveloperUUID() : String {
        return appAuthorizationRepository.findAppAuthorizationDeveloperUUID()
    }


}
