package com.organizee.web.controllers.comment

import com.organizee.domain.Page
import com.organizee.usecases.guide.AddCommentUseCase
import com.organizee.usecases.guide.GetCommentsUseCase
import com.organizee.usecases.guide.GetReferencedCommentsUseCase
import com.organizee.usecases.guide.RemoveCommentUseCase
import com.organizee.usecases.guide.commands.GetCommentsCommand
import com.organizee.usecases.guide.commands.GetReferencedCommentsCommand
import com.organizee.usecases.guide.commands.RemoveCommentCommand
import com.organizee.web.controllers.comment.json.payloads.CreateCommentPayload
import com.organizee.web.controllers.comment.json.responses.CommentResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping(value = ["v1/comments"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CommentController(
    private val addCommentUseCase: AddCommentUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val removeCommentUseCase: RemoveCommentUseCase,
    private val getReferencedCommentsUseCase: GetReferencedCommentsUseCase
) {

    @DeleteMapping("/{id}")
    fun deleteComment(
        @PathVariable("id") id: UUID,
        principal: Principal
    ): ResponseEntity<Any> {
        removeCommentUseCase.execute(RemoveCommentCommand(principal.name, id))

        return ResponseEntity.noContent().build()
    }

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

    @GetMapping("/{id}/comments")
    fun getCommentsByComment(
        @PathVariable("id") id: UUID,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): ResponseEntity<Page<CommentResponse>> {
        val input = GetReferencedCommentsCommand.create(id, page, size)

        val comments = getReferencedCommentsUseCase.execute(input)

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