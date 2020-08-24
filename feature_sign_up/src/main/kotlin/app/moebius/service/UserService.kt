package app.moebius.service

import org.springframework.stereotype.Service

interface UserService {
    fun createUser()
}

@Service
class BaseUserService: UserService {

    override fun createUser() {
        TODO("Not yet implemented")
    }

    /**
     * Describe if a user is valid
     */
    fun isValidUser() {

    }

}
