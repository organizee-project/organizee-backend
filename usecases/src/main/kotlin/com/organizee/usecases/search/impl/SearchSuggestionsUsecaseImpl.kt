package com.organizee.usecases.search.impl

import com.organizee.boundaries.search.SearchService
import com.organizee.usecases.search.SearchSuggestionsUseCase
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SearchSuggestionsUsecaseImpl(
    private val searchService: SearchService,
) : SearchSuggestionsUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: String): List<String> {
        logger.info("Getting search suggestions | input: $input")
        
        return searchService.suggest(input)
    }
}