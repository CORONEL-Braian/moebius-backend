package app.mobius.data.datasource

import app.mobius.domain.model.Identity

interface IdentityStore {

    fun createIdentity(identity: Identity)

}