package app.mobius.data.datasource

import app.mobius.domain.entity.Person

interface PersonStore {

    fun createPerson(person: Person)

}