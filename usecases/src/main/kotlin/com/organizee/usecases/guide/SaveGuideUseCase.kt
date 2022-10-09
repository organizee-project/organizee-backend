package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.usecases.guide.commands.SaveGuideCommand

interface SaveGuideUseCase : Usecase<SaveGuideCommand, Unit> {}