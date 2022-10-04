package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.domain.guide.Like
import com.organizee.usecases.guide.commands.NewLikeCommand

interface AddLikeUseCase : Usecase<NewLikeCommand, Like> {

}