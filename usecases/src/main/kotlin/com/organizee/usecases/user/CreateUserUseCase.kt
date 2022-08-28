package com.organizee.usecases.user

import com.organizee.Usecase
import com.organizee.domain.user.User
import com.organizee.usecases.user.commands.NewUserCommand

interface CreateUserUseCase : Usecase<NewUserCommand, User> {
}