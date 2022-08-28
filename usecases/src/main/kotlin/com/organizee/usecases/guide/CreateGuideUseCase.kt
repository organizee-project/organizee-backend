package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.commands.NewGuideCommand

interface CreateGuideUseCase : Usecase<NewGuideCommand, Guide> {

}