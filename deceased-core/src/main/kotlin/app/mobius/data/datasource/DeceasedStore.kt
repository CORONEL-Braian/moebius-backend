package app.mobius.data.datasource

import app.moebius.domain.entity.User

interface DeceasedStore {

    fun saveDeceased(user: User)

}