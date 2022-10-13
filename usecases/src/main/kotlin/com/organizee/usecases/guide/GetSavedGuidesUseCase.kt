package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.domain.Page
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.commands.GetSavedGuidesCommand

interface GetSavedGuidesUseCase : Usecase<GetSavedGuidesCommand, Page<Guide>> {}