package app.mobius.service

import org.springframework.stereotype.Service

interface PersonService {
    fun createPerson()
}

@Service
class BasePersonService: PersonService {

    override fun createPerson() {
        TODO("Not yet implemented")
    }

    /**
     * Describe if a person is valid
     */
    fun isValidPerson() {

    }

}
