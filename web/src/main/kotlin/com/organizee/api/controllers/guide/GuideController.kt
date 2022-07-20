package com.organizee.api.controllers.guide

import com.organizee.api.controllers.guide.json.CreateGuidePayload
import com.organizee.api.controllers.guide.json.CreateGuideResponse
import com.organizee.api.controllers.guide.json.GuideResponse
import com.organizee.guide.CreateGuideUseCase
import com.organizee.guide.GetGuideUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["v1/guides"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GuideController(
    private val createGuideUseCase: CreateGuideUseCase,
    private val getGuideUseCase: GetGuideUseCase
) {
    @PostMapping
    fun create(@RequestBody input: CreateGuidePayload): CreateGuideResponse =
        CreateGuideResponse.fromEntity(createGuideUseCase.execute(input.toUseCaseInput()))

    @GetMapping("{slug}")
    fun getBySlug(@PathVariable("slug") slug: String) =
        GuideResponse.fromEntity(getGuideUseCase.execute(slug))
}