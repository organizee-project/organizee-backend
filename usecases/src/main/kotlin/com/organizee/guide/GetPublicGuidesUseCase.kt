package com.organizee.guide

import com.organizee.Page
import com.organizee.Usecase
import com.organizee.guide.commands.GetPublicGuidesCommand

interface GetPublicGuidesUseCase : Usecase<GetPublicGuidesCommand, Page<Guide>> {}