package com.organizee.user

import com.organizee.Usecase
import com.organizee.user.commands.NewUserCommand

interface CreateUserUseCase : Usecase<NewUserCommand, User> {
}