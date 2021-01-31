package app.mobius.domain

import org.springframework.context.annotation.Bean
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class MyUserDetails(

): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//        return mutableListOf(SimpleGrantedAuthority("Developer"))
        return mutableListOf()
    }

    override fun getPassword(): String {
        return passwordEncoder().encode("mySecretePw20")
    }

    override fun getUsername(): String {
        return "aUsername"
    }

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

    private fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}