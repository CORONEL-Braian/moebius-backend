package app.mobius.crendentialManagment.data.service

import app.mobius.MobiusFeatureIntegrationTest
import app.mobius.credentialManagment.data.repository.AppAuthorizationJpaRepository
import app.mobius.credentialManagment.domain.entity.security.Environment
import app.mobius.credentialManagment.domain.entity.security.Platform
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

/**
 * OBS: Not mock JpaRepository, you must use MobiusFeatureIntegrationTest and @Autowired
 * https://www.baeldung.com/spring-beancreationexception
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = [MobiusFeatureIntegrationTest::class])
open class AppAuthorizationServiceTest {

//    @MockBean
    @Autowired
    private lateinit var appAuthorizationJpaRepository: AppAuthorizationJpaRepository

    @Test
    fun `test a simple routine`() {
        assert(appAuthorizationJpaRepository.simpleRoutineForTesting())
    }

    @Test
    fun `check if auth is valid`() {
        val platform = Platform(name = "Android", ecosystem = "Mobile")
        val developerName = "userForTest"
        val privateKey = "123"
        val environment = Environment.TESTING

        assert(appAuthorizationJpaRepository.isValidAppAuthorization(UUID.fromString("28933dbe-16d5-5578-8309-417418288635"), "123", Environment.TESTING))
//        Assertions.assertNotNull(UUID.fromString(appAuthorizationJpaRepository.findAppAuthorizationDeveloperUUID(platform, developerName, environment)))
    }

    @Test
    fun `should return true when an app authorization is valid`() {
        val platform = Platform(name = "Android", ecosystem = "Mobile")
        val developerName = "userForTest"
        val privateKey = "123"
        val environment = Environment.TESTING

//        TODO: Send environment as Enum
//        Caused by: org.postgresql.util.PSQLException: ERROR: operator does not exist: environment = character varying
        Mockito
                .`when`(appAuthorizationJpaRepository.isValidAppAuthorization(
                            UUID.fromString(appAuthorizationJpaRepository.findAppAuthorizationDeveloperUUID(platform, developerName, environment)),   // Bug: always is null
                            privateKey,
                            environment)
                )   // Bug: always is false
                .thenReturn(true)
    }

    @Test
    fun `should return false when app authorization is valid app`() {
        val appAuthorizationDeveloper = "f60b447c-90c7-4edd-9399-cb7ebd9051a8"
        val androidMobile = Platform(name = "Android", ecosystem = "Mobile")
        val developerName = "userForTest"
        val environment = Environment.TESTING
        val privateKey = "randomKey"

        Mockito
                .`when`(appAuthorizationJpaRepository.findAppAuthorizationDeveloperUUID(androidMobile, developerName, environment))
                .thenReturn(appAuthorizationDeveloper)
        Mockito
                .`when`(appAuthorizationJpaRepository.isValidAppAuthorization(
                        UUID.fromString(appAuthorizationDeveloper), privateKey, environment)
                )
                .thenReturn(false)
    }

}