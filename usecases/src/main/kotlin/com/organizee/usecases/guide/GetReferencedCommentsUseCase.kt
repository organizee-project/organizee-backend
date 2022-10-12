package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.domain.Page
import com.organizee.domain.guide.Comment
import com.organizee.usecases.guide.commands.GetReferencedCommentsCommand

interface GetReferencedCommentsUseCase : Usecase<GetReferencedCommentsCommand, Page<Comment>> {

}