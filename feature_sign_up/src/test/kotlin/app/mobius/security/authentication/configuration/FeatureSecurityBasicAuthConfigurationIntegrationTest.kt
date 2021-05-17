package app.mobius.security.authentication.configuration

import app.mobius.MobiusFeatureIntegrationTest
import app.mobius.api.ApiEndpoints.URL_BASE
import app.mobius.security.authentication.controller.FeatureSecurityCoreEndpointsTest
import app.mobius.web.filter.XHeaderAuthenticationFilter.Companion.HEADER_ENVIRONMENT
import app.mobius.web.filter.XHeaderAuthenticationFilter.Companion.HEADER_PLATFORM_ECOSYSTEM
import app.mobius.web.filter.XHeaderAuthenticationFilter.Companion.HEADER_PLATFORM_NAME
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@SpringBootTest(classes = [MobiusFeatureIntegrationTest::class])
class FeatureSecurityBasicAuthConfigurationIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private fun provideFullUrl(endpoint: String) = URL_BASE + endpoint

    /**
     * OBS: if you forget to provide credentials (basic auth), your custom auth provider won't be called.
     * Source:
     *  . Provide auth model without annotations: https://stackoverflow.com/a/66191368/5279996
     */
    @Test
    fun `when authenticated developer requests a secure endpoint, then success`() {
        val requestBuilder = MockMvcRequestBuilders
                .get(provideFullUrl(FeatureSecurityCoreEndpointsTest.Keys.SECURE))
                .with(httpBasic("userForTest", "123"))
                        .header(HEADER_PLATFORM_NAME, "Android")
                        .header(HEADER_PLATFORM_ECOSYSTEM, "Mobile")
                        .header(HEADER_ENVIRONMENT, "TESTING")

        val resultMatcher = MockMvcResultMatchers.status().isOk

        mockMvc.perform(requestBuilder).andExpect(resultMatcher)
    }

    @Test
    fun `when developer with wrong password and headers then unauthorized response`() {
        val requestBuilder = MockMvcRequestBuilders
                .get(provideFullUrl(FeatureSecurityCoreEndpointsTest.Keys.SECURE))
                .with(httpBasic("userForTest", "wrongpassword"))
                .header("Platform-Name", "Android")
                .header("Platform-Ecosystem", "Mobile")
                .header("Environment", "TESTING")

        val resultMatcher = MockMvcResultMatchers.status().isUnauthorized

        mockMvc.perform(requestBuilder).andExpect(resultMatcher)
    }

    @Test
    fun `when developer with the absence of header platform-name then an exception is throwed`() {
        val requestBuilder = MockMvcRequestBuilders
                .get(provideFullUrl(FeatureSecurityCoreEndpointsTest.Keys.SECURE))
                .with(httpBasic("userForTest", "123"))
                .header("Platform-Ecosystem", "Mobile")
                .header("Environment", "TESTING")

        val resultMatcher = MockMvcResultMatchers.status().is4xxClientError

        mockMvc.perform(requestBuilder).andExpect(resultMatcher)
    }
    @Test
    fun `when developer with the absence of header platform-ecosystem then an exception is throwed`() {
        val requestBuilder = MockMvcRequestBuilders
                .get(provideFullUrl(FeatureSecurityCoreEndpointsTest.Keys.SECURE))
                .with(httpBasic("userForTest", "mobius123"))
                .header("Platform-Name", "Android")
                .header("Environment", "TESTING")

        val resultMatcher = MockMvcResultMatchers.status().is4xxClientError

        mockMvc.perform(requestBuilder).andExpect(resultMatcher)
    }

}