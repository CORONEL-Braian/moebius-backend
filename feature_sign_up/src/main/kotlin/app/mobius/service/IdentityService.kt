package app.mobius.service

import org.springframework.stereotype.Service

interface IdentityService {
    fun createIdentity()
}

@Service
class BaseIdentityService: IdentityService {

    override fun createIdentity() {
        TODO("Not yet implemented")
    }

    /**
     * Describe if a identity is valid
     */
    fun isValidIdentity() {

    }

}
