package app.mobius

import app.mobius.data.repository.PlatformJpaRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

@ExtendWith(SpringExtension::class)
@SpringBootTest(properties = ["app.mobius.CredentialManagment.enabled"])
@AutoConfigureMockMvc
@Import(PlatformJpaRepository::class)
class AppAuthorizationUseCaseIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired(required = false)
    private val schedulingConfiguration: CredentialManagmentConfiguration? = null

    @Test
    fun fisrtTest() {

    }

}