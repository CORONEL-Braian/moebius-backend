package app.mobius.data.datasource

import app.mobius.data.dataAccess.hibernate.HibernateData
import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.domain.entity.security.AppAuthorization
import app.mobius.domain.entity.security.AppConsumer
import app.mobius.domain.entity.security.Platform
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppAuthorizationDataSourceTest {

    private lateinit var hibernate : HibernateData
    private lateinit var session : Session

    @BeforeAll
    fun before() {
        hibernate = HibernateData()
    }

    @BeforeEach
    fun beforeEach() {
        session = JDBMConfig.Hibernate.openSession()
    }

    @Test
    fun `when select platforms in db, then the Android Mobile platform exists`() {
        val androidMobile = Platform(name = "Android", ecosystem = "Mobile")

        JDBMConfig.Hibernate.executeQuery(session) {
            assert(
                    hibernate.allTheRows(Platform::class.java).any {
                        androidMobile.name == it.name && androidMobile.ecosystem == it.ecosystem
                    }
            )
        }
    }

    @Test
    fun `when select platforms in db, then the iOS Mobile platform exists`() {
        val iOSMobile = Platform(name = "iOS", ecosystem = "Mobile")

        JDBMConfig.Hibernate.executeQuery(session) {
            assert(
                    hibernate.allTheRows(Platform::class.java).any {
                        iOSMobile.name == it.name && iOSMobile.ecosystem == it.ecosystem
                    }
            )
        }
    }

    @Test
    fun `when select platforms in db, then the Web Mobile platform exists`() {
        val webMobile = Platform(name = "Web", ecosystem = "Mobile")

        JDBMConfig.Hibernate.executeQuery(session) {
            assert(
                    hibernate.allTheRows(Platform::class.java).any {
                        webMobile.name == it.name && webMobile.ecosystem == it.ecosystem
                    }
            )
        }
    }

    @Test
    fun `when select all app consumer people in db, then it is not empty`() {

        JDBMConfig.Hibernate.executeQuery(session) {
            assert(
                    hibernate.allTheRows(Platform::class.java).isNotEmpty()
            )
        }
    }

//    Kotlin is broken with $$$a. The solution is put a \ before of $ if not exists
    @Test
    fun `print special characters when data type crypt is consumed`() {
        println("\$2a\$08\$tgBY0UrkKNgMCbSGdMw9kOR5BEv9mIogQkcJvfoScyNCIepWG/z6C")
//        println("$2a$08$tgBY0UrkKNgMCbSGdMw9kOR5BEv9mIogQkcJvfoScyNCIepWG/z6C")
//        println("$$$a")
    }

    @Test
    fun `when select db_mobius_is_match_hash_pw_app function, then it exists`() {
        val query = session.createNativeQuery("SELECT ${Routines.IS_MATCH_HASH_PW_APP}('a920aec3-a58c-51f8-9228-a661a8a3cc71', '');")
        query.list().map {
            println("SQLQuery: $it")
        }
    }

    @Test
    fun `when filter an app_authorization_people, then get it`() {
        val androidMobile = Platform(name = "Android", ecosystem = "Mobile")

        JDBMConfig.Hibernate.executeQuery(session) {
            val appConsumerPeopleWithAndroidMobile = hibernate.allTheRows(AppConsumer.AppConsumerPeople::class.java)
                    .first {
                        it.platform.name == androidMobile.name &&
                        it.platform.ecosystem == androidMobile.ecosystem &&
                        it.description == "store"
            }

            val appAuthorizationPeople = hibernate.allTheRows(AppAuthorization.AppAuthorizationPeople::class.java)
                    .first {
                        it.appConsumer.appConsumerUUID == appConsumerPeopleWithAndroidMobile.appConsumerUUID
            }

            Assertions.assertNotNull(appAuthorizationPeople.appAuthorizationUUID)

            println("appAuthorizationPeople: " + appAuthorizationPeople.appAuthorizationUUID)
        }
    }

    @Test
    fun `when checks if is match hash pw of app authorization, then the match is false`() {
        val androidMobile = Platform(name = "Android", ecosystem = "Mobile")

        JDBMConfig.Hibernate.executeQuery(session) {
            val appConsumerPeopleWithAndroidMobile = hibernate.allTheRows(AppConsumer.AppConsumerPeople::class.java)
                    .first {
                        it.platform.name == androidMobile.name &&
                                it.platform.ecosystem == androidMobile.ecosystem &&
                                it.description == "store"
                    }

            val appAuthorizationPeople = hibernate.allTheRows(AppAuthorization.AppAuthorizationPeople::class.java)
                    .first {
                        it.appConsumer.appConsumerUUID == appConsumerPeopleWithAndroidMobile.appConsumerUUID
                    }
            println("appAuthorizationPeople: " + appAuthorizationPeople.appAuthorizationUUID)

             val query = session.createNativeQuery(
                     "SELECT ${Routines.IS_MATCH_HASH_PW_APP}(" +
                                "'${appAuthorizationPeople.appAuthorizationUUID}', " +
                                "'incorrectPassword')"
             )

            Assertions.assertFalse(query.list().first() as Boolean)
        }
    }
}