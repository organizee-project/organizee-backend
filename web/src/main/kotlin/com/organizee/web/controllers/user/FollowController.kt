package com.organizee.web.controllers.user

import com.organizee.usecases.user.FollowUserUsecase
import com.organizee.usecases.user.commands.FollowUserCommand
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
@RequestMapping(value = ["v1/users"], produces = [MediaType.APPLICATION_JSON_VALUE])
class FollowController(
    private val followUserUsecase: FollowUserUsecase
) {

    @PostMapping("{username}/follow")
    fun followUser(
        @PathVariable("username") username: String,
        principal: Principal
    ): ResponseEntity<Any> {

        followUserUsecase.execute(FollowUserCommand(principal.name, username))

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}

