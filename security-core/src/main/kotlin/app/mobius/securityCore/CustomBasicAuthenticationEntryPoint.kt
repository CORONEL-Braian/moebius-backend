package app.mobius.securityCore

import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomDigestAuthenticationEntryPoint: DigestAuthenticationEntryPoint() {

}
