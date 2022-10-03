package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.domain.guide.Comment
import com.organizee.usecases.guide.commands.NewCommentCommand

interface AddCommentUseCase : Usecase<NewCommentCommand, Comment> {

}