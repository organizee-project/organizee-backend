package com.organizee.web.controllers.user

import com.organizee.usecases.user.FollowUserUsecase
import com.organizee.usecases.user.UnfollowUserUseCase
import com.organizee.usecases.user.commands.FollowUserCommand
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping(value = ["v1/users"], produces = [MediaType.APPLICATION_JSON_VALUE])
class FollowController(
    private val followUserUsecase: FollowUserUsecase,
    private val unfollowUseCase: UnfollowUserUseCase,
) {

    @PostMapping("{username}/follow")
    fun followUser(
        @PathVariable("username") username: String,
        principal: Principal
    ): ResponseEntity<Any> {

        followUserUsecase.execute(FollowUserCommand(principal.name, username))

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping("{username}/follow")
    fun unfollowUser(
        @PathVariable("username") username: String,
        principal: Principal
    ): ResponseEntity<Any> {

        unfollowUseCase.execute(FollowUserCommand(principal.name, username))

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}

