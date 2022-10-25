package com.organizee.usecases.user

import com.organizee.Usecase
import com.organizee.usecases.user.commands.FollowUserCommand

interface FollowUserUsecase : Usecase<FollowUserCommand, Unit> {
}