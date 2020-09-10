package app.mobius.data.datasource

import app.moebius.domain.entity.Identity

interface DeceasedStore {

    fun saveDeceased(identity: Identity)

}