package app.mobius.service

//import app.mobius.domain.entity.security.AppAuthorization
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApplicationAuthorizationService {

    /**
     * Get the bean called appCredentialsRepository, which is auto-generated by Spring,
     * we will use it to handle the data
     */
//    @Autowired
//    private lateinit var appCredentialsRepository: AppCredentialsRepository

    fun getAppAuthorizationSessionToken(
//            appAuthorizationCredentials: AppAuthorization
    ): String {
//        TODO: Impl
        return "sessionToken"
    }

    fun checkAppAuthorizationSessionToken(appAuthorizationSessionToken: String) : Boolean {
//        TODO: Impl
        return true
    }

}