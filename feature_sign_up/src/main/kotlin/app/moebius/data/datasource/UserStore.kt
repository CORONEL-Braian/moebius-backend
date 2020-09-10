package app.moebius.data.datasource

import app.moebius.domain.entity.Identity

interface IdentityStore {

    fun createIdentity(identity: Identity)

}