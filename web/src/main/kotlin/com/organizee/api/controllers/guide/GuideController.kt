package com.organizee.api.controllers.guide

import com.organizee.Page
import com.organizee.api.controllers.guide.json.CommentResponse
import com.organizee.api.controllers.guide.json.CreateCommentPayload
import com.organizee.api.controllers.guide.json.CreateGuidePayload
import com.organizee.api.controllers.guide.json.GuideResponse
import com.organizee.guide.CreateCommentUseCase
import com.organizee.guide.CreateGuideUseCase
import com.organizee.guide.GetGuideUseCase
import com.organizee.guide.GetPublicGuidesUseCase
import com.organizee.guide.commands.GetPublicGuidesCommand
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["v1/guides"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GuideController(
    private val createGuideUseCase: CreateGuideUseCase,
    private val getGuideUseCase: GetGuideUseCase,
    private val getPublicGuidesUseCase: GetPublicGuidesUseCase,
    private val createCommentUseCase: CreateCommentUseCase
) {
    @PostMapping
    fun create(@RequestBody input: CreateGuidePayload): GuideResponse =
        GuideResponse.fromEntity(createGuideUseCase.execute(input.toUseCaseInput()))

    @GetMapping("{slug}")
    fun getBySlug(@PathVariable("slug") slug: String) =
        GuideResponse.fromEntity(getGuideUseCase.execute(slug))


    @GetMapping
    fun list(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): ResponseEntity<Page<GuideResponse>> {
        val guides = getPublicGuidesUseCase.execute(
            input = GetPublicGuidesCommand(
                page = page,
                size = size
            )
        )

        val response = Page(
            itens = guides.itens.map {
                GuideResponse.fromEntity(it)
            },
            totalPages = guides.totalPages,
            count = guides.count,
            currentPage = guides.currentPage,
            nextPage = guides.nextPage
        )

        return ResponseEntity.ok(response)
    }


    @PostMapping("/{slug}/comment")
    fun createComment(
        @PathVariable("slug") slug: String,
        @RequestBody input: CreateCommentPayload
    ) = CommentResponse.fromEntity(createCommentUseCase.execute(input.toUseCaseInput(slug)))
}