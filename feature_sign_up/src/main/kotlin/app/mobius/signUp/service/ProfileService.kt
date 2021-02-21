package app.mobius.signUp.service

import app.mobius.signUp.data.repository.ProfileRepository
import app.mobius.domain.entity.Profile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProfileService {

    @Autowired
    private lateinit var profileRepository: ProfileRepository

    fun getProfiles(): List<Profile> {
        return profileRepository.findAllProfiles()
    }

    fun isOpen(): Boolean {
        return profileRepository.isEntityManagerOpen()
    }


}