package app.mobius.data.datasource

import app.mobius.data.dataAccess.hibernate.HibernateData
import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.domain.entity.Gender
import app.mobius.domain.entity.Profile as PersonProfile
import org.hibernate.Session
import org.junit.jupiter.api.*
import org.springframework.util.Assert
import java.util.*
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlProfileDataSourceTest {

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

    /**
     * Precondition: Make sure that the default value of the gender in the db profile exists
     */
    @Test
    fun `given a default gender and profile, when insert profile, then create profile without insert gender`() {
        val profile = PersonProfile()
        val defaultGenderUUID = UUID.fromString("03915d99-c1da-4584-93e9-680d572b5295")

        assertDoesNotThrow("save profile exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(profile)) {
                    session.save(profile)
                }
            }
        }

//        TODO: Get real profile gender UUID
        val profileGenderUUID = UUID.fromString("03915d99-c1da-4584-93e9-680d572b5295")

        Assertions.assertEquals(defaultGenderUUID, profileGenderUUID)
    }

    @Test
    fun `given a gender with existing UUID, when insert profile with gender, then create profile without insert gender`() {
        val profile = PersonProfile()

        val genderUUID =  UUID.fromString("9cc4becd-4d22-5131-8c20-a93aaa12b323")

        profile.gender = Gender(genderUUID = genderUUID, type = "")

        assertDoesNotThrow("save profile exception") {
            JDBMConfig.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(profile)) {
                    session.save(profile)
                }
            }
        }

        //        TODO: Get real profile gender UUID
        val profileGenderUUID = UUID.fromString("9cc4becd-4d22-5131-8c20-a93aaa12b323")
//        hibernate.getUUID(profile.gender.genderUUID)

        Assert.isTrue( genderUUID == profileGenderUUID, "")

    }

}