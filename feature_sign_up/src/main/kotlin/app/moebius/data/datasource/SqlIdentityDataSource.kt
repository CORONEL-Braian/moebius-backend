package app.moebius.data.datasource

import app.mobius.data.di.JDBCManager
import app.moebius.domain.entity.Identity
import javax.persistence.*

@Entity
@Table(name = "test")
data class SomeTest(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val test: String?)

class SqlIdentityDataSource: IdentityStore {

    override fun createIdentity(identity: Identity) {


    }

    fun test() {
        val session = JDBCManager.openSession(annotatedClass = SomeTest::class.java)

        JDBCManager.executeQuery(session, "Work") {
            session.save(SomeTest("3"))
        }
    }

}

fun main() {
//    SqlIdentityDataSource().createIdentity()
    SqlIdentityDataSource().test()
}

