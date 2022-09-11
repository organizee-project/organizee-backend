package com.organizee.web.controllers.search

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
    ): List<GuideResponse> {
        return searchGetGuidesUseCase.execute(q).map { GuideResponse.fromEntity(it) }
    }

    @GetMapping("suggestions")
    fun getSuggestions(
        @RequestParam(defaultValue = "") q: String,
    ): List<String> {
        return searchSuggestionsUseCase.execute(q)
    }
}