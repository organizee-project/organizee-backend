package com.organizee.usecases.activity

import com.organizee.Usecase
import com.organizee.usecases.activity.commands.NewActivityCommand

interface AddActivityUseCase : Usecase<NewActivityCommand, Unit> {}