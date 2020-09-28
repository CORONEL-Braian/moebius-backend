package app.mobius.data.datasource

import app.mobius.domain.model.Identity

interface DeceasedStore {

    fun saveDeceased(identity: Identity)

}