package app.mobius.crendentialManagment.service

import app.mobius.MobiusFeatureIntegrationTest
import app.mobius.credentialManagment.domain.entity.security.Environment
import app.mobius.credentialManagment.service.AppAuthorizationService
import app.mobius.credentialManagment.domain.entity.security.Platform
import app.mobius.crendentialManagment.CredentialManagmentFeatureTestConfiguration
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@AutoConfigureMockMvc
@SpringBootTest(classes = [MobiusFeatureIntegrationTest::class])
@CredentialManagmentFeatureTestConfiguration
class AppAuthorizationServiceIntegrationTest {

    @Autowired
    private lateinit var appAuthorizationService: AppAuthorizationService

    @Test
    fun `should return true when is valid authorization of service`() {
        val platform = Platform(name = "Android", ecosystem = "Mobile")
        val developer = "userForTest"
        val privateKey = "123"
        val environment = Environment.TESTING

        assert(appAuthorizationService.isValidAppAuthorization(platform, developer, privateKey, environment))
    }

    @Test
    fun `should return false when is invalid authorization of service`() {
        val platform = Platform(name = "Android", ecosystem = "Mobile")
        val developer = "userForTest"
        val privateKey = "invalidKey"
        val environment = Environment.TESTING

        Assertions.assertFalse(appAuthorizationService.isValidAppAuthorization(platform, developer, privateKey, environment))
    }

}