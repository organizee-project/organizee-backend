package com.organizee.adapter.s3.services

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.organizee.boundaries.fileStorage.FileInput
import com.organizee.boundaries.fileStorage.FileService
import org.springframework.stereotype.Component

@Component
class FilePersistService(
    private val s3: AmazonS3
) : FileService {
    companion object {
        const val BUCKET_NAME = "organizee-public-files"
    }

    override fun addPublicFile(file: FileInput): String {
        val metadata = ObjectMetadata()

        metadata.contentLength = file.size
        metadata.contentType = file.type

        s3.putObject(
            PutObjectRequest(
                BUCKET_NAME,
                file.name,
                file.stream,
                metadata
            )
        )

        return s3.getUrl(BUCKET_NAME, file.name).toExternalForm()
    }
}