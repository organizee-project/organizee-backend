package com.organizee.api.controllers.guide

import com.organizee.api.controllers.guide.json.CommentResponse
import com.organizee.api.controllers.guide.json.CreateCommentPayload
import com.organizee.api.controllers.guide.json.CreateGuidePayload
import com.organizee.api.controllers.guide.json.GuideResponse
import com.organizee.guide.CreateGuideUseCase
import com.organizee.guide.GetGuideUseCase
import com.organizee.guide.impl.CreateCommentUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["v1/guides"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GuideController(
    private val createGuideUseCase: CreateGuideUseCase,
    private val getGuideUseCase: GetGuideUseCase,
    private val createCommentUseCase: CreateCommentUseCase
) {
    @PostMapping
    fun create(@RequestBody input: CreateGuidePayload): GuideResponse =
        GuideResponse.fromEntity(createGuideUseCase.execute(input.toUseCaseInput()))

    @GetMapping("{slug}")
    fun getBySlug(@PathVariable("slug") slug: String) =
        GuideResponse.fromEntity(getGuideUseCase.execute(slug))


    @PostMapping("/{slug}/comment")
    fun createComment(
        @PathVariable("slug") slug: String,
        @RequestBody input: CreateCommentPayload
    ) = CommentResponse.fromEntity(createCommentUseCase.execute(input.toUseCaseInput(slug)))
}