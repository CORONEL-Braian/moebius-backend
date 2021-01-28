package app.mobius.securityCore.configuration

import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import kotlin.jvm.Throws


/**
 * Custom filter for the Spring Security filter chain.
 *
 * Sources:
 *  . https://www.baeldung.com/spring-security-custom-filter
 *  . Add a filter: https://stackoverflow.com/q/19825946/5279996
 */
class CustomFilter : GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(
            request: ServletRequest?,
            response: ServletResponse?,
            chain: FilterChain) {
        chain.doFilter(request, response)
    }

}