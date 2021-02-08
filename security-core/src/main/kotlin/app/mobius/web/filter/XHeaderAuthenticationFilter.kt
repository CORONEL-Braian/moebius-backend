package app.mobius.web.filter

import app.mobius.credentialManagment.domain.entity.security.Platform
import app.mobius.domain.security.authorization.AppAuthorizationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Custom filter for the Spring Security filter chain.
 * Checks the request headers and the location for the Authorization header, starting with "Basic."
 *
 * Sources:
 *  . https://www.baeldung.com/spring-security-custom-filter
 *  . Add a filter: https://stackoverflow.com/q/19825946/5279996
 */
class XHeaderAuthenticationFilter: OncePerRequestFilter() {

    /**
     * Source:
     *  . Add filter to the spring config: https://stackoverflow.com/a/57341317/5279996
     */
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val headerAuthorization: String = request.getHeader(HEADER_AUTHORIZATION)
        val headerPlatformName: String = request.getHeader(HEADER_PLATFORM_NAME)
        val headerPlatformEcosystem: String = request.getHeader(HEADER_PLATFORM_ECOSYSTEM)

        val authentication = AppAuthorizationToken(
                developer = decoderDeveloperFromHeaderAuthorization(headerAuthorization),
                password = decoderPasswordFromHeaderAuthorization(headerAuthorization),
                platform = Platform(name = headerPlatformName, ecosystem = headerPlatformEcosystem)
        )

        /*if (false) {
            throw SecurityException()
        }*/

        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }

    /**
     * https://stackoverflow.com/a/16000878/5279996
     */
    private fun decoderDeveloperFromHeaderAuthorization(headerAuthorization: String): String {
        val token = headerAuthorization.split("Basic ").last()
        val credentials = String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8)
        return credentials.split(":").first()
    }

    private fun decoderPasswordFromHeaderAuthorization(headerAuthorization: String): String {
        val token = headerAuthorization.split("Basic ").last()
        val credentials = String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8)
        return credentials.split(":").last()
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_PLATFORM_NAME = "Platform-Name"
        private const val HEADER_PLATFORM_ECOSYSTEM = "Platform-Ecosystem"
    }

}