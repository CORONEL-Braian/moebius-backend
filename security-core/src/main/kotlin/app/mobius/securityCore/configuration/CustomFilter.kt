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
 * https://www.baeldung.com/spring-security-custom-filter
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