package app.mobius.domain.security.authorization

import app.mobius.credentialManagment.domain.entity.security.Platform
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.SpringSecurityCoreVersion

/**
 * Custom authentication token para transportar los headers al AuthenticationProvider
 */
//TODO: Review: https://gist.github.com/adatta02/1103f7a81a5e6c1931bf9a09b6131d59
//TODO: Check impl in UsernamePasswordAuthenticationToken
class AppAuthorizationToken2(
        private val developer: String,
        private val password: String,
        val platform: Platform,
        authorities: MutableCollection<out GrantedAuthority>? = null
) : AbstractAuthenticationToken(authorities) {


    override fun getCredentials(): Any {
        return password
    }

    override fun getPrincipal(): Any {
        return developer
    }

}

open class AppAuthorizationToken : AbstractAuthenticationToken {

    private var developer: String
    private var password: String
    var platform: Platform

    /**
     * This constructor can be safely used by any code that wishes to create a
     * `UsernamePasswordAuthenticationToken`, as the [.isAuthenticated]
     * will return `false`.
     *
     */
    constructor(developer: String, password: String, platform: Platform) : super(null) {
        this.developer = developer
        this.password = password
        this.platform = platform
        isAuthenticated = false
    }

    /**
     * This constructor should only be used by `AuthenticationManager` or
     * `AuthenticationProvider` implementations that are satisfied with
     * producing a trusted (i.e. [.isAuthenticated] = `true`)
     * authentication token.
     *
     * @param principal
     * @param credentials
     * @param authorities
     */
    constructor(developer: String, password: String, platform: Platform, authorities: Collection<GrantedAuthority?>?
    ) : super(authorities) {
        this.developer = developer
        this.password = password
        this.platform = platform
        super.setAuthenticated(true) // must use super, as we override
    }

    override fun getCredentials(): String {
        return password
    }

    override fun getPrincipal(): String {
        return developer
    }

    @Throws(IllegalArgumentException::class)
    override fun setAuthenticated(isAuthenticated: Boolean) {
        require(!isAuthenticated) { "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead" }
        super.setAuthenticated(false)
    }

    override fun eraseCredentials() {
        super.eraseCredentials()
        password = ""
    }

    companion object {
        private const val serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID
    }
}
