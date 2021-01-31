package app.mobius.securityCore.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import java.io.PrintWriter
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

/**
 *  Handle requests that do not contain the "Authorization" header
 *      returning a full page for a 401 Unauthorized response back to the client.
 */
@Component
class CustomBasicAuthenticationEntryPoint: BasicAuthenticationEntryPoint() {

    /**
     * TODO: Renders a json representation of the error for a REST API
     */
    @Throws(IOException::class, ServletException::class)
    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {
        super.commence(request, response, authException)
        response.addHeader(
                "WWW-Authenticate",
                "Digest realm=\"$realmName\""
        )
        response.status = HttpServletResponse.SC_UNAUTHORIZED;
//        TODO Delete HTML
        val writer: PrintWriter = response.writer
        writer.println("HTTP Status 401 - " + authException.message);
    }

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        realmName = "Mobius Backend"
        super.afterPropertiesSet()
    }

}
