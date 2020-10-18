package app.mobius.data.datasource

import app.mobius.domain.entity.Person

interface DeceasedStore {

    fun saveDeceased(person: Person)

}