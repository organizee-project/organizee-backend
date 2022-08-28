package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.domain.Page
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.commands.GetPublicGuidesCommand

interface GetPublicGuidesUseCase : Usecase<GetPublicGuidesCommand, Page<Guide>> {}