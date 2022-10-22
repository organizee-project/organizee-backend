package com.organizee.web.controllers.file

import com.organizee.usecases.files.AddFileUseCase
import com.organizee.usecases.files.commands.AddFileCommand
import com.organizee.web.controllers.file.reponses.FileResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream

@RestController
@RequestMapping(value = ["v1/files"], produces = [MediaType.APPLICATION_JSON_VALUE])
class FileController(
    private val addFileUseCase: AddFileUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun upload(

        @RequestPart("file") file: MultipartFile
    ): FileResponse {

        val url = addFileUseCase.execute(
            AddFileCommand(
                name = file.originalFilename,
                stream = ByteArrayInputStream(file.bytes),
                size = file.size,
                type = file.contentType

            )
        )

        return FileResponse(url)

    }
}