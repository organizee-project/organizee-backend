package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.events.FollowEvent
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.domain.user.User
import com.organizee.shared.events.DomainEventPublisherService
import com.organizee.usecases.user.FollowUserUsecase
import com.organizee.usecases.user.commands.FollowUserCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FollowUserUseCaseImpl(
    private val userService: UserService,
    private val eventPublisherService: DomainEventPublisherService<User>
) : FollowUserUsecase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }


    override fun execute(input: FollowUserCommand) {
        logger.info("Executing follow user | userId=${input.userId} follows ${input.username}")

        val user = userService.findByUserIdOrThrow(input.userId)
        val userToFollow = userService.findByUsernameOrThrow(input.username)

        if (user.username == userToFollow.username || user.following.firstOrNull { it.username == userToFollow.username } != null)
            throw ErrorCodes.CANNOT_FOLLOW()

        userService.follow(user, userToFollow)

        eventPublisherService.publish(FollowEvent(user, userToFollow))
    }


}

