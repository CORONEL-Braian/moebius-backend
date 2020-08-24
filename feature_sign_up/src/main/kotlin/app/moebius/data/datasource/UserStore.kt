package app.moebius.data.datasource

import app.moebius.domain.entity.User

interface UserStore {

    fun createUser(user: User)

}