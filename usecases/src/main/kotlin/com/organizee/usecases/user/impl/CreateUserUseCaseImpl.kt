package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.user.User
import com.organizee.usecases.user.CreateUserUseCase
import com.organizee.usecases.user.commands.NewUserCommand
import org.springframework.stereotype.Service
import com.organizee.boundaries.firebase.UserService as FirebaseUserService

@Service
class CreateUserUseCaseImpl(
    private val userService: UserService,
    private val firebaseUserService: FirebaseUserService
) : CreateUserUseCase {
    override fun execute(input: NewUserCommand): User {
        val user = User.createNormalUser(
            email = input.email,
            name = input.name,
            surname = input.surname,
            username = input.username,
        )

        val found = userService.userExists(user.email, user.username)

        if (found) {
            throw Exception("User already exists")
        }

        val createdUser = firebaseUserService.createUser(user, input.password)

        return persistUser(createdUser)
    }

    private fun persistUser(user: User) =
        userService.create(user)

}