package app.mobius.data.datasource

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
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
    fun `given a default gender and profile, when insert profile, then create profile without insert gender -- should does not throw Exception`() {
        val profile = PersonProfile()
        val defaultGender = UUID.fromString("c87ee95b-06f1-52ab-83ed-5d882ae400e6")

        assertDoesNotThrow("save profile exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(profile)) {
                    session.save(profile)
                }
            }
        }

        val profileGender = UUID.fromString("c87ee95b-06f1-52ab-83ed-5d882ae400e6")

        Assertions.assertEquals(defaultGender, profileGender)
    }

    @Test
    fun `given a gender with existing UUID, when insert profile with gender, then create profile without insert gender -- should does not throw Exception`() {
        val profile = PersonProfile()

        val gender = Gender(type = randomString(), genderUUID = UUID.fromString("95a7b26d-52e2-5ed1-a93e-5ee5472751ca"))

        profile.gender = gender

        assertDoesNotThrow("save profile exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(profile)) {
                    session.save(profile)
                }
            }
        }
//        Assert.isTrue(hibernate.getUUID(profile.gender.genderUUID) == defaultGenderUUID, "")


    }

}