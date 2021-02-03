package app.mobius.securityCore.configuration

import org.springframework.web.filter.OncePerRequestFilter
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
 *  . Add filter to the spring config: https://stackoverflow.com/a/57341317/5279996
 */
class XHeaderAuthenticationFilter: OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val headerAuthorization = request.getHeader(HEADER_AUTHORIZATION)
        val headerPlatformName = request.getHeader(HEADER_PLATFORM_NAME)
        val headerPlatformEcosystem = request.getHeader(HEADER_PLATFORM_ECOSYSTEM)

//        TODO: Add headers to spring config


        filterChain.doFilter(request, response)
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_PLATFORM_NAME = "Platform-Name"
        private const val HEADER_PLATFORM_ECOSYSTEM = "Platform-Ecosystem"
    }

}