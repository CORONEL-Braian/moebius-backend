package app.mobius.securityCore.configuration

import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomOncePerRequestFilter: OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val headerAuthorization = request.getHeader(HEADER_AUTHORIZATION)
        filterChain.doFilter(request, response)
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
    }

}