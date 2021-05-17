package app.mobius.signUp.controller

import app.mobius.MobiusFeatureIntegrationTest
import app.mobius.api.ApiEndpoints
import app.mobius.data.util.randomString
import app.mobius.io.ParentPath
import app.mobius.jsonApi.JsonApi
import app.mobius.jsonApi.model.JsonApiResource
import app.mobius.signUp.service.SignUpService
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.io.path.ExperimentalPathApi


@ExperimentalPathApi
@AutoConfigureMockMvc
@SpringBootTest(classes = [
    MobiusFeatureIntegrationTest::class,
])
class SignUpRestControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Qualifier("signUpServiceMock")
    @Autowired
    private lateinit var signUpService2: SignUpService

    private fun provideFullUrl(endpoint: String) = ApiEndpoints.URL_BASE + endpoint

    @Rule
    var initRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun init() {    
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `0 - when invoke to createPerson with a json api resource mock, then success`() {
        val response = "Success"

        `when`(signUpService2.createPerson(JsonApiResource())).thenReturn(response)

        val result = signUpService2.createPerson(JsonApiResource())
        Assertions.assertEquals(response, result)
    }

    @Test
    fun `1 - when write signUp json api as kt from file, then the contract is success`() {
        JsonApi.writeJsonAsKtFromFile(
                moduleName = "feature_sign_up",
                parentPath = ParentPath.Test.RESOURCES,
                relPath = "/request/signUpJsonApi.json",
                valueType = JsonApiResource::class.java
        )
    }

    @Test
    fun `2 - when requests a signUp endpoint with random username, then status is ok`() {
        var personJsonApi: JsonApiResource = JsonApi.writeJsonAsKtFromFile(
                moduleName = "feature_sign_up",
                parentPath = ParentPath.Test.RESOURCES,
                relPath = "/request/signUpJsonApi.json",
                valueType = JsonApiResource::class.java
        )

//        Change the username value to random
        personJsonApi = personJsonApi.copy(
                data = listOf(
                        personJsonApi.data.first().copy(
                                attributes = mapOf("username" to randomString())
                        )
                )
        )

        val person = JsonApi.writeKtAsJson(personJsonApi)

//        Mock service
        val requestBuilder = MockMvcRequestBuilders
                .post(provideFullUrl("/people/add"))
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("userForTest", "123"))
                .header("Platform-Name", "Android")
                .header("Platform-Ecosystem", "Mobile")
                .header("Environment", "TESTING")
                .contentType(APPLICATION_JSON)
                .content(person)

        val resultMatcher = MockMvcResultMatchers.status().isOk

        mockMvc.perform(requestBuilder)
                .andExpect(resultMatcher)
    }

}