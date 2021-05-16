package app.mobius.signUp.controller

import app.mobius.MobiusFeatureIntegrationTest
import app.mobius.api.ApiEndpoints
import app.mobius.jsonApi.model.JsonApiResource
import app.mobius.signUp.configuration.SignUpRestControllerTestConfiguration
import app.mobius.signUp.service.SignUpService
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
@AutoConfigureMockMvc
@SpringBootTest(classes = [
    MobiusFeatureIntegrationTest::class,
])
class SignUpRestControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @ExperimentalPathApi
    @Autowired
    private lateinit var signUpService: SignUpService

    private fun provideFullUrl(endpoint: String) = ApiEndpoints.URL_BASE + endpoint

    @Rule
    var initRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `0 - when person requests a signUp endpoint, then success`() {
        `when`(signUpService.createPerson(JsonApiResource())).thenReturn("Success test")

        val result = signUpService.createPerson(JsonApiResource())
        assert(result.isNotEmpty())
    }

    @Test
    fun `1 - when person requests a signUp endpoint, then success`() {

//        Mock service


        val requestBuilder = MockMvcRequestBuilders
                .post(provideFullUrl("/people/add"))
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("userForTest", "123"))
                .header("Platform-Name", "Android")
                .header("Platform-Ecosystem", "Mobile")
                .header("Environment", "TESTING")

        val resultMatcher = MockMvcResultMatchers.status().isOk

//        mockMvc.perform(requestBuilder).andExpect(resultMatcher)
    }


}