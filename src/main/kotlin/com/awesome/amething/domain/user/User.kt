package com.awesome.amething.domain.user

import com.awesome.amething.global.enums.AuthenticatorRole
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "user")
class User (username : String, password : String, nickname : String, bio : String, profilePicture : String, refreshToken : String, role: AuthenticatorRole) : UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id : Long? = null

    @Column(name = "username", nullable = false, unique = true)
    private var username : String = username

    @Column(name = "user_password", nullable = false)
    private var password : String = password

    @Column(name = "user_nickname")
    var nickName : String = nickname

    @Column(name = "user_bio")
    var bio : String = bio

    @Column(name = "user_profile_picture")
    var profilePicture : String = profilePicture

    @Column(name = "user_refresh_token")
    var refreshToken : String = refreshToken

    @Enumerated
    @ElementCollection
    @JoinColumn(name = "user_id") @Column(name = "role") @CollectionTable(name = "roles")
    val roles : MutableList<AuthenticatorRole> = mutableListOf(role)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        var grantedAuthorityRole : MutableList<String> = this.roles.map { roles -> roles.name } as MutableList<String>
        return grantedAuthorityRole.map {roleName -> SimpleGrantedAuthority (roleName)} as MutableList<out GrantedAuthority>
    }

    override fun getPassword(): String = this.password

    override fun getUsername(): String = this.username

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }
}