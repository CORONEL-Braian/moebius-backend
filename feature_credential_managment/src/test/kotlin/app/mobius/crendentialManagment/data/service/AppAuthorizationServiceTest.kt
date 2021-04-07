package app.mobius.crendentialManagment.data.service

import app.mobius.MobiusFeatureIntegrationTest
import app.mobius.credentialManagment.data.repository.AppAuthorizationJpaRepository
import app.mobius.credentialManagment.domain.entity.security.Environment
import app.mobius.credentialManagment.domain.entity.security.Platform
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
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

    @Autowired
    private lateinit var appAuthorizationJpaRepository: AppAuthorizationJpaRepository

    private fun providePlatform(
            name: String = "Android",
            ecosystem: String = "Mobile"
    ) = Platform(name = name, ecosystem = ecosystem)

    private fun provideDeveloperName(developerName: String = "userForTest") = developerName

    private fun providePrivateKey(privateKey: String = "123") = privateKey

    private fun provideEnvironment(environment: Environment = Environment.DEV) = environment

    fun provideAppAuthorizationDeveloperUUID(
            platform: Platform = providePlatform(),
            developerName: String = provideDeveloperName(),
            environment: Environment = provideEnvironment()
    ) = UUID.fromString(
            appAuthorizationJpaRepository.findAppAuthorizationDeveloperUUID(platform, developerName, environment)
    )

    @Test
    fun `test a simple routine`() {
        assert(appAuthorizationJpaRepository.simpleRoutineForTesting())
    }

    @Test
    fun `when find a app authorization developer UUID for Testing, then a UUID is not null`() {
        Assertions.assertNotNull(
                provideAppAuthorizationDeveloperUUID(environment = provideEnvironment(Environment.TESTING))
        )
    }

    @Test
    fun `when check if an app authorization is valid, thene should return true`() {
        assert(
                appAuthorizationJpaRepository.isValidAppAuthorization(
                        provideAppAuthorizationDeveloperUUID(environment = provideEnvironment(Environment.TESTING)),
                        providePrivateKey(),
                        provideEnvironment(Environment.TESTING)
                )
        )
    }

    @Test
    fun `should return false when app authorization is valid app`() {
        val privateKey = "randomKey"

        Assertions.assertFalse(
                appAuthorizationJpaRepository.isValidAppAuthorization(
                        provideAppAuthorizationDeveloperUUID(environment = provideEnvironment(Environment.TESTING)),
                        providePrivateKey(privateKey),
                        provideEnvironment(Environment.TESTING)
                )
        )
    }

}