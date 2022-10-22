package com.organizee.usecases.files

import com.organizee.Usecase
import com.organizee.usecases.files.commands.AddFileCommand

interface AddFileUseCase : Usecase<AddFileCommand, String> {
}