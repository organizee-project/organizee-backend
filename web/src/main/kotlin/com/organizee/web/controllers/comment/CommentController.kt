package com.organizee.web.controllers.comment

import com.organizee.domain.Page
import com.organizee.usecases.guide.AddCommentUseCase
import com.organizee.usecases.guide.GetCommentsUseCase
import com.organizee.usecases.guide.commands.GetCommentsCommand
import com.organizee.web.controllers.comment.json.payloads.CreateCommentPayload
import com.organizee.web.controllers.comment.json.responses.CommentResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid


@RestController
@RequestMapping(value = ["v1/comments"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CommentController(
    private val addCommentUseCase: AddCommentUseCase,
    private val getCommentsUseCase: GetCommentsUseCase
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

    @GetMapping("/guide/{slug}")
    fun getCommentsByGuide(
        @PathVariable("slug") slug: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): ResponseEntity<Page<CommentResponse>> {
        val input = GetCommentsCommand.create(slug, page, size)

        val comments = getCommentsUseCase.execute(input)

        val response = Page(
            itens = comments.itens.map {
                CommentResponse.fromEntity(it)
            },
            totalPages = comments.totalPages,
            count = comments.count,
            currentPage = comments.currentPage,
            nextPage = comments.nextPage
        )

        return ResponseEntity.ok(response)
    }

}