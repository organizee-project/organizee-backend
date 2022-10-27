package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.FollowersEntity
import com.organizee.adapter.db.entities.UserEntity
import com.organizee.adapter.db.repositories.FollowRepository
import com.organizee.adapter.db.repositories.UserRepository
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.domain.user.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val followRepository: FollowRepository
) : UserService {
    override fun update(user: User): User {
        val entity = findEntityByIdOrThrow(user.id)

        entity.update(user)

        return userRepository.save(entity).toEntity()
    }

    @Transactional
    override fun create(user: User): User {
        return userRepository.save(UserEntity.create(user)).toEntity()
    }

    override fun findByUsernameOrThrow(username: String): User {
        return userRepository.findByUsername(username)?.toEntity()
            ?: throw ErrorCodes.USER_NOT_FOUND(listOf(username))
    }

    override fun findByUserIdOrThrow(userId: String): User {
        return findEntityByIdOrThrow(userId).toEntity()
    }

    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)?.toEntity()
    }

    override fun userExists(username: String): Boolean {
        return when (userRepository.findByUsername(username)) {
            null -> false
            else -> true
        }
    }

    override fun findById(id: String) =
        userRepository.findByIdOrNull(id)?.toEntity()

    override fun follow(user: User, followUser: User) {
        val userEntity =
            userRepository.findByUsername(user.username)
                ?: throw ErrorCodes.USER_NOT_FOUND(listOf(user.username))

        val followedUser = userRepository.findByUsername(followUser.username)
            ?: throw ErrorCodes.USER_NOT_FOUND(listOf(user.username))

        followRepository.save(FollowersEntity(UUID.randomUUID(), userEntity, followedUser))

    }

    override fun unfollow(user: User, followUser: User) {
        val follow =
            followRepository.findByFromUsernameAndToUsername(user.username, followUser.username)
                ?: throw ErrorCodes.FOLLOW_NOT_FOUND()


        followRepository.delete(follow)

    }

    private fun findEntityByIdOrThrow(userId: String) =
        userRepository.findByIdOrNull(userId) ?: throw ErrorCodes.USER_ID_NOT_FOUND()

}