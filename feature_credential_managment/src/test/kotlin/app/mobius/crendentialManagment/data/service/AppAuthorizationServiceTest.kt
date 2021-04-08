package app.mobius.crendentialManagment.data.service

import app.mobius.MobiusFeatureIntegrationTest
import app.mobius.credentialManagment.data.repository.AppAuthorizationJpaRepository
import app.mobius.credentialManagment.domain.entity.security.Environment
import app.mobius.credentialManagment.domain.entity.security.Platform
import app.mobius.crendentialManagment.data.CredentialManagmentTestPropertySource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import java.util.*
import javax.annotation.PostConstruct

/**
 * OBS: Not mock JpaRepository, you must use MobiusFeatureIntegrationTest and @Autowired
 * https://www.baeldung.com/spring-beancreationexception
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = [MobiusFeatureIntegrationTest::class])
@ContextConfiguration(classes = [CredentialManagmentTestPropertySource::class])
@TestPropertySource(locations = ["/testPropertySource.properties"])
open class AppAuthorizationServiceTest {

    @Autowired
    private lateinit var appAuthorizationJpaRepository: AppAuthorizationJpaRepository

    @Autowired
    private lateinit var propertySource: CredentialManagmentTestPropertySource

    private lateinit var developerName: String
    private lateinit var privateKey: String

    /**
     * This scope is executed after autowired for initialize providers of TestPropertySource
     *
     * Source:
     *  . Autowiring order: https://stackoverflow.com/a/38068933/5279996
     */
    @PostConstruct
    fun afterAutowired() {
        developerName = propertySource.developerName!!
        privateKey = propertySource.privateKey!!
    }

    private fun provideDeveloperName(developerName: String = this.developerName) = developerName

    private fun providePrivateKey(privateKey: String = this.privateKey) = privateKey

    private fun providePlatform(
            name: String = "Android",
            ecosystem: String = "Mobile"
    ) = Platform(name = name, ecosystem = ecosystem)

    private fun provideEnvironment(environment: Environment = Environment.DEV) = environment

    private fun provideAppAuthorizationDeveloperUUID(
            platform: Platform = providePlatform(),
            developerName: String = provideDeveloperName(),
            environment: Environment = provideEnvironment()
    ) = UUID.fromString(
            appAuthorizationJpaRepository.findAppAuthorizationDeveloperUUID(platform, developerName, environment)
    )

    @Test
    open fun `Given a Test Property Source, When variables retrieved, Then the values are returned`() {
        Assertions.assertNotNull(propertySource.developerName)
        Assertions.assertNotNull(propertySource.privateKey)
    }

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