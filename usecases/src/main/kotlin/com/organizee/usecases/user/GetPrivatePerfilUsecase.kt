package com.organizee.usecases.user

import com.organizee.Usecase
import com.organizee.usecases.user.commands.GetPrivatePerfilCommand
import com.organizee.usecases.user.responses.PerfilUseCaseResponse

interface GetPrivatePerfilUsecase : Usecase<GetPrivatePerfilCommand, PerfilUseCaseResponse> {
}