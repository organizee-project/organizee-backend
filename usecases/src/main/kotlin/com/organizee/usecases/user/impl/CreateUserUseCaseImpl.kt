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
            password = input.password

        )

        firebaseUserService.createUser(user)

        // return userService.create(user)

        return user
    }
}