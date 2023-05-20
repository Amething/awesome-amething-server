package com.awesome.amething.domain.user

import com.awesome.amething.global.enums.AuthenticatorRole
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table

@Entity
@Table(name = "user")
class User(
    @Column(name = "username", nullable = false, unique = true)
    var username: String = "username",

    @Column(name = "user_password", nullable = false)
    var password: String = "password",

    @Column(name = "user_nickname")
    var nickname: String = "nickname",

    @Column(name = "user_bio")
    var bio: String = "bio",

    @Column(name = "user_profile_picture")
    var profilePicture: String = "profilePicture",

    @Column(name = "user_refresh_token")
    var refreshToken: String = "refreshToken",

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @JoinColumn(name = "user_id")
    @Column(name = "role")
    @CollectionTable(name = "roles")
    val roles: MutableList<AuthenticatorRole> = mutableListOf(AuthenticatorRole.ROLE_MEMBER),
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private var _id: Long? = null

    val id: Long
        get() = this._id!!
}
