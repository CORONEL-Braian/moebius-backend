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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@SpringBootTest(classes = [MobiusApplicationTest::class])
class SomeUseCaseIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun fisrtTest() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/mobiusApplicationTest")
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

}
