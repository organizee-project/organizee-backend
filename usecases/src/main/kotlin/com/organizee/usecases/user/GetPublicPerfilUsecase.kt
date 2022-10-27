package com.organizee.usecases.user

import com.organizee.Usecase
import com.organizee.usecases.user.commands.GetPerfilCommand
import com.organizee.usecases.user.responses.PerfilUseCaseResponse

interface GetPublicPerfilUsecase : Usecase<GetPerfilCommand, PerfilUseCaseResponse> {
}