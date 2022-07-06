package com.organizee.guide

import com.organizee.Guide
import com.organizee.Usecase
import com.organizee.guide.commands.NewGuideCommand

interface CreateGuideUseCase : Usecase<NewGuideCommand, Guide> {

}