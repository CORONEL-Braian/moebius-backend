package app.mobius.basicTest

import app.mobius.MobiusApplicationTest
import app.mobius.api.ApiEndpoints.URL_BASE
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest(classes = [MobiusApplicationTest::class])
class SomeUseCaseIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun fisrtTest() {
        mockMvc.perform(
                get("${URL_BASE}${Endpoints.MOBIUS_APPLICATION_TEST}")
        ).andExpect(status().isOk)
    }

}
