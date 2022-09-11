package com.organizee.usecases.search.impl

import com.organizee.boundaries.search.SearchService
import com.organizee.usecases.search.SearchSuggestionsUseCase
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SearchSuggestionsUsecaseImpl(
    private val searchService: SearchService,
    @Value("\${search.suggester}")
    private val suggester: String

) : SearchSuggestionsUseCase {
    override fun execute(input: String): List<String> {
        return searchService.suggest(input, suggester)
    }
}