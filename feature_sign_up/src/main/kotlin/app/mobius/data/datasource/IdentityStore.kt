package app.mobius.data.datasource

import app.mobius.domain.entity.Identity

interface IdentityStore {

    fun createIdentity(identity: Identity)

}