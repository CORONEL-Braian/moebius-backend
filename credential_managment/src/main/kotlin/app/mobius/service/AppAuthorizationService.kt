package app.mobius.service

import app.mobius.data.repository.AppAuthorizationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppAuthorizationService {

    @Autowired
    private lateinit var appAuthorizationRepository: AppAuthorizationRepository

    /**
     * @param privateKey: Password to validate
     */
//    fun isValidCredential(appConsumer: AppConsumer.AppConsumerPeople, privateKey: String) : Boolean {
    fun isValidCredential() : Boolean {
//        TODO: Impl
        return true
    }


    fun isOpen() : Boolean {
        return appAuthorizationRepository.isEntityManagerOpen()
    }


}
