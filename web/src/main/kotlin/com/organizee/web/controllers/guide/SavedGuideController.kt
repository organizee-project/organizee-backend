package com.organizee.web.controllers.guide

import com.organizee.domain.Page
import com.organizee.usecases.guide.GetSavedGuidesUseCase
import com.organizee.usecases.guide.RemoveSavedGuideUseCase
import com.organizee.usecases.guide.SaveGuideUseCase
import com.organizee.usecases.guide.commands.GetSavedGuidesCommand
import com.organizee.usecases.guide.commands.SaveGuideCommand
import com.organizee.web.controllers.guide.json.responses.GuideResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping(value = ["v1/saved/guides"], produces = [MediaType.APPLICATION_JSON_VALUE])
class SavedGuideController(
    private val saveGuideUseCase: SaveGuideUseCase,
    private val removeSavedGuideUseCase: RemoveSavedGuideUseCase,
    private val getSavedGuidesUseCase: GetSavedGuidesUseCase
) {

    @PostMapping("/{slug}")
    fun saveGuide(
        @PathVariable("slug") slug: String,
        principal: Principal
    ): ResponseEntity<Any> {
        saveGuideUseCase.execute(SaveGuideCommand(principal.name, slug))
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{slug}")
    fun removeSavedGuide(
        @PathVariable("slug") slug: String,
        principal: Principal
    ): ResponseEntity<Any> {
        removeSavedGuideUseCase.execute(SaveGuideCommand(principal.name, slug))
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun getSavedGuides(
        principal: Principal,
        @RequestParam(defaultValue = "") category: String,
        @RequestParam(defaultValue = "") sortBy: String,
        @RequestParam(defaultValue = "asc") sort: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): Page<GuideResponse> {

        val guides = getSavedGuidesUseCase.execute(
            GetSavedGuidesCommand.create(
                userId = principal.name,
                page = page,
                size = size
            )
        )

        return Page(
            items = guides.items.map {
                GuideResponse.fromEntity(it)
            },
            totalPages = guides.totalPages,
            count = guides.count,
            currentPage = guides.currentPage,
            nextPage = guides.nextPage
        )
    }
}