package app.moebius.data.datasource

import app.moebius.domain.entity.User


class SqlUserDataSource: UserStore {

    override fun createUser(user: User) {
//        TODO: Create a connection with db
    }

}

fun main() {
//    SqlUserDataSource().createUser()
}