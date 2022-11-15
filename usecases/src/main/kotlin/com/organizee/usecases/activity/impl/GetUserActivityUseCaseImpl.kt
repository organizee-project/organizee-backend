package com.organizee.usecases.activity.impl

import com.organizee.boundaries.db.services.ActivityService
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.Page
import com.organizee.domain.activity.Activity
import com.organizee.usecases.activity.GetUserActivitiesUseCase
import com.organizee.usecases.activity.commands.GetUserActivitiesCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GetUserActivitiesUseCaseImpl(
    private val userService: UserService,
    private val activityService: ActivityService
) : GetUserActivitiesUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: GetUserActivitiesCommand): Page<Activity> {
        logger.info("Getting user activities | username: ${input.username}")
        val user = userService.findByUsernameOrThrow(input.username)

        return activityService.findAllByUser(user.username, input.page, input.size)
    }
}