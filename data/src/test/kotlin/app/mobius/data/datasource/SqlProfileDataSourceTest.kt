package app.mobius.data.datasource

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.domain.entity.Gender
import app.mobius.domain.entity.Profile as PersonProfile
import org.hibernate.Session
import org.junit.jupiter.api.*
import org.springframework.util.Assert
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlProfileDataSourceTest {

    private lateinit var hibernate : HibernateUtil
    private lateinit var session : Session

    @BeforeAll
    fun before() {
        hibernate = HibernateUtil()
    }

    @BeforeEach
    fun beforeEach() {
        session = JDBM.Hibernate.openSession()
    }

    @Test
    fun `given a default gender and profile, when insert profile, then create profile -- should does not throw Exception`() {
        val defaultGenderUUID = UUID.fromString("c87ee95b-06f1-52ab-83ed-5d882ae400e6")

        val profile = PersonProfile()

        assertDoesNotThrow("save profile exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(profile)) {
                    session.save(profile)
                }
            }
        }

//        Assert.isTrue(hibernate.getUUID(profile.gender.genderUUID) == defaultGenderUUID, "")
    }

    @Test
    fun `given a gender not default and profile, when insert profile, then create profile -- should does not throw Exception`() {
        val genderUUID = UUID.fromString("8ece806e-cee7-5fb2-9996-2097595eb3f3")

     /*   val profile = PersonProfile(
                gender = Gender(
                        genderUUID = genderUUID
                )
        )

        assertDoesNotThrow("save profile exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(profile)) {
                    session.save(profile)
                }
            }
        }
*/
//        Assert.isTrue(hibernate.getUUID(profile.gender.genderUUID) == defaultGenderUUID, "")


    }

}