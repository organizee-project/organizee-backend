package com.organizee.web.controllers.search

import com.organizee.domain.Page
import com.organizee.usecases.guide.commands.SearchGetPublicGuidesCommand
import com.organizee.usecases.search.SearchGetGuidesUseCase
import com.organizee.usecases.search.SearchSuggestionsUseCase
import com.organizee.web.controllers.guide.json.responses.GuideResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = ["v1/search"], produces = [MediaType.APPLICATION_JSON_VALUE])
class SearchController(
    private val searchGetGuidesUseCase: SearchGetGuidesUseCase,
    private val searchSuggestionsUseCase: SearchSuggestionsUseCase
) {
    @GetMapping
    fun getList(
        @RequestParam(defaultValue = "") q: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): Page<GuideResponse> {
        val response =
            searchGetGuidesUseCase.execute(SearchGetPublicGuidesCommand.create(q, page, size))


        return Page(
            items = response.items.map {
                GuideResponse.fromEntity(it)
            },
            totalPages = response.totalPages,
            count = response.count,
            currentPage = response.currentPage,
            nextPage = response.nextPage
        )
    }

    @GetMapping("suggestions")
    fun getSuggestions(
        @RequestParam(defaultValue = "") q: String,
    ): List<String> {
        return searchSuggestionsUseCase.execute(q)
    }
}