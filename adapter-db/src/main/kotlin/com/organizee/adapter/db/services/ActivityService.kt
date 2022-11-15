package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.ActivityEntity
import com.organizee.adapter.db.repositories.ActivityRepository
import com.organizee.adapter.db.repositories.UserRepository
import com.organizee.boundaries.db.services.ActivityService
import com.organizee.domain.Page
import com.organizee.domain.activity.Activity
import com.organizee.domain.exceptions.ErrorCodes
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ActivityServiceImpl(
    private val repository: ActivityRepository,
    private val userRepository: UserRepository
) : ActivityService {
    override fun save(activity: Activity) {
        val user = userRepository.findByIdOrNull(activity.user.id)
            ?: throw ErrorCodes.USER_NOT_FOUND(emptyList())

        val entity = ActivityEntity.from(activity, user)

        repository.save(entity)
    }

    override fun findAllByUser(username: String, page: Int, size: Int): Page<Activity> {
        val response = repository.findAllByUserUsernameOrderByCreatedAtDesc(
            username,
            PageRequest.of(page, size)
        ).map { it.toEntity() }

        return Page.of(response)

    }

}