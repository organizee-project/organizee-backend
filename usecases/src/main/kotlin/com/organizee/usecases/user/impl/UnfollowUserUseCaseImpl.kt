package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.usecases.user.UnfollowUserUseCase
import com.organizee.usecases.user.commands.FollowUserCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UnfollowUserUseCaseImpl(
    private val userService: UserService
) : UnfollowUserUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }


    override fun execute(input: FollowUserCommand) {
        logger.info("Executing unfollow user | userId=${input.userId} unfollows ${input.username}")

        val user = userService.findByUserIdOrThrow(input.userId)
        val userUnfollow = userService.findByUsernameOrThrow(input.username)

        val userFollows = user.following.any {
            it.username == userUnfollow.username
        }

        if (!userFollows)
            throw ErrorCodes.USER_NOT_FOLLOWED()

        userService.unfollow(user, userUnfollow)


    }


}

