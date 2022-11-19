package com.organizee.usecases.activity.impl

import com.organizee.boundaries.db.services.ActivityService
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.activity.Activity
import com.organizee.domain.activity.ActivityType
import com.organizee.usecases.activity.AddActivityUseCase
import com.organizee.usecases.activity.commands.NewActivityCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AddAcitivityUseCaseImpl(
    private val userService: UserService,
    private val activityService: ActivityService
) : AddActivityUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: NewActivityCommand) {
        logger.info("Creating new activity | input: $input")

        val user = userService.findByUserIdOrThrow(input.userId)

        val activity = Activity(
            date = input.date,
            type = ActivityType.valueOf(input.type),
            referenceId = input.referenceId,
            user = user,
            description = input.description
        )

        activityService.save(activity)
    }
}