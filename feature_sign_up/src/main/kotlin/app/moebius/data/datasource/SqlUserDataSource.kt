package app.moebius.data.datasource

import app.mobius.data.di.InjectionContainer
import app.moebius.domain.entity.Identity


class SqlIdentityDataSource: IdentityStore {

    override fun createIdentity(identity: Identity) {
//        TODO: Create a connection with db

//        val test = InjectionContainer.test()

    }

}

fun main() {
//    SqlIdentityDataSource().createIdentity()
}