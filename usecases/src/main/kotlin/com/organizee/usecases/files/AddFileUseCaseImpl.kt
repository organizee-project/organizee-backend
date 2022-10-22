package com.organizee.usecases.files

import com.organizee.boundaries.fileStorage.FileInput
import com.organizee.boundaries.fileStorage.FileService
import com.organizee.domain.file.File
import com.organizee.usecases.files.commands.AddFileCommand
import org.springframework.stereotype.Component

@Component
class AddFileUseCaseImpl
    (
    private val fileService: FileService
) : AddFileUseCase {


    override fun execute(input: AddFileCommand): String {
        val file = File.valueOf(input.type)

        return fileService.addPublicFile(
            FileInput(
                name = file.name, stream = input.stream, size = input.size, type = input.type
            )
        )

    }
}