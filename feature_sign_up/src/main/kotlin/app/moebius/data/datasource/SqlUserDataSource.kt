package app.moebius.data.datasource

import app.moebius.domain.entity.Identity


class SqlIdentityDataSource: IdentityStore {

    override fun createIdentity(identity: Identity) {
//        TODO: Create a connection with db
    }

}

fun main() {
//    SqlIdentityDataSource().createIdentity()
}