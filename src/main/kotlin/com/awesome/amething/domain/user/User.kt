package com.awesome.amething.domain.user

import com.awesome.amething.global.enums.AuthenticatorRole
import jakarta.persistence.*

@Entity
@Table(name = "user")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id : Long? = null,

    @Column(name = "username", nullable = false, unique = true)
    var username : String = "username",

    @Column(name = "user_password", nullable = false)
    var password : String = "password",

    @Column(name = "user_nickname")
    var nickName : String = "nickname",

    @Column(name = "user_bio")
    var bio : String = "bio",

    @Column(name = "user_profile_picture")
    var profilePicture : String = "profilePicture",

    @Column(name = "user_refresh_token")
    var refreshToken : String = "refreshToken",

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @JoinColumn(name = "user_id") @Column(name = "role") @CollectionTable(name = "roles")
    val roles : MutableList<AuthenticatorRole> = mutableListOf(AuthenticatorRole.ROLE_MEMBER)
)
