package com.organizee.web.controllers.comment

import com.organizee.usecases.guide.AddCommentUseCase
import com.organizee.web.controllers.comment.json.payloads.CreateCommentPayload
import com.organizee.web.controllers.comment.json.responses.CommentResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid


@RestController
@RequestMapping(value = ["v1/comments"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CommentController(
    private val addCommentUseCase: AddCommentUseCase,
) {
    @PostMapping("/guide/{slug}")
    fun create(
        @Valid @RequestBody input: CreateCommentPayload,
        @PathVariable("slug") slug: String,
        principal: Principal
    ): CommentResponse {
        val comment = addCommentUseCase.execute(
            input.toUseCaseInput(
                slug = slug, userId = principal.name
            )
        )

        return CommentResponse.fromEntity(comment)
    }

}