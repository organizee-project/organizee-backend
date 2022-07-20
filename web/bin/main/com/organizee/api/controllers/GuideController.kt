package com.organizee.api.controllers

import com.organizee.guide.CreateGuideUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = ["v1/guides"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GuideController(
    private val createGuideUseCase: CreateGuideUseCase
) {
    @PostMapping
    fun create(@RequestBody input: CreateGuidePayload): CreateGuideResponse =
        CreateGuideResponse.fromEntity(createGuideUseCase.execute(input.toUseCaseInput()))
}