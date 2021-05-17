package app.mobius.signUp.configuration

import app.mobius.signUp.service.SignUpService
import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.io.path.ExperimentalPathApi

@Configuration
open class SignUpRestIntegrationTestConfiguration {

    /**
     * OBS: Use 'Mock' as sufix in value Bean for not duplicate beans
     */
    @ExperimentalPathApi
    @Bean("signUpServiceMock")
    open fun signUpService(): SignUpService {
        return Mockito.mock(SignUpService::class.java)
    }

}