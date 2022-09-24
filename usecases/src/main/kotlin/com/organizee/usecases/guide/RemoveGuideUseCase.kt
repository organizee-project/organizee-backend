package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.usecases.guide.commands.DeleteGuideCommand

interface RemoveGuideUseCase : Usecase<DeleteGuideCommand, Unit> {

}