package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.domain.Page
import com.organizee.domain.guide.Like
import com.organizee.usecases.guide.commands.GetLikesCommand

interface GetLikesUseCase : Usecase<GetLikesCommand, Page<Like>> {

}