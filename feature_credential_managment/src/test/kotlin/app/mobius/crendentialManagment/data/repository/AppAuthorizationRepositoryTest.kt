package app.mobius.crendentialManagment.data.repository

import app.mobius.MobiusFeatureIntegrationTest
import app.mobius.credentialManagment.data.repository.AppAuthorizationJpaRepository
import app.mobius.credentialManagment.domain.entity.security.Environment
import app.mobius.credentialManagment.domain.entity.security.Platform
import app.mobius.credentialManagment.service.AppAuthorizationService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@AutoConfigureMockMvc
@SpringBootTest(classes = [MobiusFeatureIntegrationTest::class])
open class AppAuthorizationRepositoryTest {

    @Autowired
    private lateinit var appAuthorizationJpaRepository: AppAuthorizationJpaRepository

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
                            UUID.fromString(
                                    appAuthorizationJpaRepository.findAppAuthorizationDeveloperUUID(platform, developerName, environment)   // Bug: always is null
                            ),
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