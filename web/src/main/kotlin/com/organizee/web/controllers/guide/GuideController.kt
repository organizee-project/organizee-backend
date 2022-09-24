package com.organizee.web.controllers.guide

import com.organizee.domain.Page
import com.organizee.usecases.guide.*
import com.organizee.usecases.guide.commands.DeleteGuideCommand
import com.organizee.usecases.guide.commands.GetPublicGuidesCommand
import com.organizee.web.controllers.guide.json.payloads.CreateGuidePayload
import com.organizee.web.controllers.guide.json.payloads.UpdateGuidePayload
import com.organizee.web.controllers.guide.json.responses.GuideDetailsResponse
import com.organizee.web.controllers.guide.json.responses.GuideResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid


@RestController
@RequestMapping(value = ["v1/guides"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GuideController(
    private val createGuideUseCase: CreateGuideUseCase,
    private val getGuideUseCase: GetGuideUseCase,
    private val getPublicGuidesUseCase: GetPublicGuidesUseCase,
    private val removeGuideUseCase: RemoveGuideUseCase,
    private val updateGuideUseCase: UpdateGuideUseCase
) {
    @PostMapping
    fun create(
        @Valid @RequestBody input: CreateGuidePayload,
        principal: Principal
    ): GuideDetailsResponse {
        val guide = createGuideUseCase.execute(input.toUseCaseInput(principal.name))

        return GuideDetailsResponse.fromEntity(guide)
    }

    @GetMapping("{slug}")
    fun getBySlug(@PathVariable("slug") slug: String) =
        GuideDetailsResponse.fromEntity(getGuideUseCase.execute(slug))


    @GetMapping
    fun list(
        @RequestParam(defaultValue = "") category: String,
        @RequestParam(defaultValue = "") sortBy: String,
        @RequestParam(defaultValue = "asc") sort: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): ResponseEntity<Page<GuideResponse>> {
        val input = GetPublicGuidesCommand.create(category, sortBy, sort, page, size)

        val guides = getPublicGuidesUseCase.execute(input)

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

    @DeleteMapping("/{slug}")
    fun removeGuide(
        @PathVariable("slug") slug: String,
        principal: Principal

    ): ResponseEntity<Unit> {
        removeGuideUseCase.execute(
            DeleteGuideCommand(
                slug = slug, userId = principal.name
            )
        )

        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{slug}")
    fun updateGuide(
        @PathVariable("slug") slug: String,
        @RequestBody input: UpdateGuidePayload
    ): ResponseEntity<GuideDetailsResponse> {
        val response = updateGuideUseCase.execute(input.toUseCaseInput(slug))
        return ResponseEntity.ok(GuideDetailsResponse.fromEntity(response))
    }
}