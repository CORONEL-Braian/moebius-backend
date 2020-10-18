package app.mobius.data.datasource

import app.mobius.domain.model.Person

interface DeceasedStore {

    fun saveDeceased(person: Person)

}