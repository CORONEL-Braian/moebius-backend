package app.mobius.data.datasource

import app.mobius.domain.model.Person

interface PersonStore {

    fun createPerson(person: Person)

}