package app.mobius.security.authentication.configuration

import app.mobius.MobiusFeatureIntegrationTest
import app.mobius.api.ApiEndpoints.URL_BASE
import app.mobius.security.SecurityCoreEndpoints
import app.mobius.security.authentication.controller.SecurityBasicAuthControllerTest
import app.mobius.security.authentication.controller.SecurityCoreEndpointsTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 * Source:
 *  . https://www.baeldung.com/spring-boot-custom-auto-configuration
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = [MobiusFeatureIntegrationTest::class])
class SecurityBasicAuthConfigurationIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private fun provideFullUrl(endpoint: String) = URL_BASE + endpoint

    @Test
    fun `when someone requests a home endpoint, then success`() {
        mockMvc.perform(
                MockMvcRequestBuilders.get(provideFullUrl(SecurityCoreEndpoints.Keys.HOME))
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

//    PRECONDITION: App is running because it is a live test
    /*@Test
    @Throws(IllegalStateException::class, IOException::class)
    fun `when someone requests a home endpoint and app is running, then success`() {
        val fullUrl =  provideFullUrl(SecurityCoreEndpoints.Keys.HOME)
        val testRestTemplate = TestRestTemplate()

        val response: ResponseEntity<String> = testRestTemplate.getForEntity(
                fullUrl, String::class
        )

        Assertions.assertEquals(HttpStatus.OK, response.statusCode)
    }*/

    /**
     * OBS: if you forget to provide credentials (basic auth), your custom auth provider won't be called.
     * Source:
     *  . Provide auth model without annotations: https://stackoverflow.com/a/66191368/5279996
     */
    @Test
    fun `when authenticated developer requests a secure endpoint, then success`() {
        val requestBuilder = MockMvcRequestBuilders
                .get(provideFullUrl(SecurityCoreEndpointsTest.Keys.SECURE))
                .with(httpBasic("braian.coronel", "145"))
                        .header("Platform-Name", "Android")
                        .header("Platform-Ecosystem", "Mobile")

        val resultMatcher = MockMvcResultMatchers.status().isOk

        mockMvc.perform(requestBuilder).andExpect(resultMatcher)
    }

    @Test
    fun `when developer with wrong credentials and headers then unauthorized response`() {
//        "user", "wrongpassword"
//          "wrong header"
//        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
//        assertTrue(response.getBody().contains("Unauthorized"));
    }

}