package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.domain.Page
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.commands.GetFollowingGuidesCommand

interface GetFollowingGuidesUseCase : Usecase<GetFollowingGuidesCommand, Page<Guide>> {}