package com.organizee.usecases.user

import com.organizee.Usecase
import com.organizee.domain.user.User
import com.organizee.usecases.user.commands.UpdateUserCommand

interface UpdateUserUseCase : Usecase<UpdateUserCommand, User> {
}