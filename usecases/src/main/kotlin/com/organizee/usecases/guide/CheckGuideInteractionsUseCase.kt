package com.organizee.usecases.guide

import com.organizee.Usecase
import com.organizee.usecases.guide.commands.CheckGuideInteractionsCommand
import com.organizee.usecases.guide.responses.CheckGuideInteractionsOutput

interface CheckGuideInteractionsUseCase :
    Usecase<CheckGuideInteractionsCommand, CheckGuideInteractionsOutput> {

}