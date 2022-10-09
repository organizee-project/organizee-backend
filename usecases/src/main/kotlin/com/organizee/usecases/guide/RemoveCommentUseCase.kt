package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.usecases.guide.commands.RemoveCommentCommand

interface RemoveCommentUseCase : Usecase<RemoveCommentCommand, Unit> {}