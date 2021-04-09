package app.mobius.security.authentication.configuration

import app.mobius.MobiusFeatureIntegrationTest
import app.mobius.api.ApiEndpoints.URL_BASE
import app.mobius.security.SecurityCoreEndpoints
import app.mobius.security.authentication.controller.SecurityCoreEndpointsTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
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
    fun `1 - when someone requests a home endpoint, then success`() {
        mockMvc.perform(
                MockMvcRequestBuilders.get(provideFullUrl(SecurityCoreEndpoints.Keys.HOME))
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    /**
     * OBS: if you forget to provide credentials (basic auth), your custom auth provider won't be called.
     * Source:
     *  . Provide auth model without annotations: https://stackoverflow.com/a/66191368/5279996
     */
    @Test
    fun `2A - when authenticated developer requests a secure endpoint, then success`() {
        val requestBuilder = MockMvcRequestBuilders
                .get(provideFullUrl(SecurityCoreEndpointsTest.Keys.SECURE))
                .with(httpBasic("userForTest", "123"))
                        .header("Platform-Name", "Android")
                        .header("Platform-Ecosystem", "Mobile")
                        .header("Environment", "TESTING")

        val resultMatcher = MockMvcResultMatchers.status().isOk

        mockMvc.perform(requestBuilder).andExpect(resultMatcher)
    }

    @Test
    fun `2B - when developer with wrong password and headers then unauthorized response`() {
        val requestBuilder = MockMvcRequestBuilders
                .get(provideFullUrl(SecurityCoreEndpointsTest.Keys.SECURE))
                .with(httpBasic("userForTest", "wrongpassword"))
                .header("Platform-Name", "Android")
                .header("Platform-Ecosystem", "Mobile")
                .header("Environment", "TESTING")

        val resultMatcher = MockMvcResultMatchers.status().isUnauthorized

        mockMvc.perform(requestBuilder).andExpect(resultMatcher)
    }

    @Test
    fun `3A - when developer with the absence of header platform-name then an 4xx client error is getted`() {
        val requestBuilder = MockMvcRequestBuilders
                .get(provideFullUrl(SecurityCoreEndpointsTest.Keys.SECURE))
                .with(httpBasic("userForTest", "123"))
                .header("Platform-Ecosystem", "Mobile")
                .header("Environment", "TESTING")

        val resultMatcher = MockMvcResultMatchers.status().is4xxClientError

        mockMvc.perform(requestBuilder).andExpect(resultMatcher)
    }
    @Test
    fun `3B - when developer with the absence of header platform-ecosystem then an 4xx client error is getted`() {
        val requestBuilder = MockMvcRequestBuilders
                .get(provideFullUrl(SecurityCoreEndpointsTest.Keys.SECURE))
                .with(httpBasic("userForTest", "123"))
                .header("Platform-Name", "Android")
                .header("Environment", "TESTING")

        val resultMatcher = MockMvcResultMatchers.status().is4xxClientError

        mockMvc.perform(requestBuilder).andExpect(resultMatcher)
    }

    @Test
    fun `3C - when developer with the absence of header environment then an 4xx client error is getted`() {
        val requestBuilder = MockMvcRequestBuilders
                .get(provideFullUrl(SecurityCoreEndpointsTest.Keys.SECURE))
                .with(httpBasic("userForTest", "123"))
                .header("Platform-Name", "Android")
                .header("Platform-Ecosystem", "Mobile")

        val resultMatcher = MockMvcResultMatchers.status().is4xxClientError

        mockMvc.perform(requestBuilder).andExpect(resultMatcher)
    }

}