package com.organizee.guide

import com.organizee.Usecase
import com.organizee.guide.commands.NewCommentCommand

interface CreateCommentUseCase : Usecase<NewCommentCommand, Comment> {}