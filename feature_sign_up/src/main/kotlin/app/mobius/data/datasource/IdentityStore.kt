package app.mobius.data.datasource

import app.mobius.domain.model.Person

interface IdentityStore {

    fun createIdentity(person: Person)

}