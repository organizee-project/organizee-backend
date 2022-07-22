package com.organizee.api.controllers.user

import com.organizee.api.controllers.user.json.CreateUserPayload
import com.organizee.api.controllers.user.json.UserResponse
import com.organizee.user.CreateUserUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = ["v1/users"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController(
    private val createUserUseCase: CreateUserUseCase,
) {
    @PostMapping
    fun create(@RequestBody input: CreateUserPayload): UserResponse =
        UserResponse.fromEntity(createUserUseCase.execute(input.toUseCaseInput()))

}