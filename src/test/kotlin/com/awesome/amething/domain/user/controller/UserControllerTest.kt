package com.awesome.amething.domain.user.controller

import com.awesome.amething.domain.user.dto.UserRegisterDto
import com.awesome.amething.global.util.Loggable
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Transactional
internal class UserControllerTest(
    @Autowired
    private val userController: UserController,
) : Loggable {

    @Test
    fun register() {
        val userRegisterDto = UserRegisterDto("user", "registration", "test", "controller")
        userController.register(userRegisterDto)
    }
}