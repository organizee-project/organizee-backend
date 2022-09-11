package com.organizee.usecases.search.impl

import com.organizee.boundaries.search.SearchService
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.GetGuideUseCase
import com.organizee.usecases.search.SearchGetGuidesUseCase
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SearchGetGuidesUsecaseImpl(private val searchService: SearchService) :
    SearchGetGuidesUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(GetGuideUseCase::class.java)
    }

    override fun execute(input: String): List<Guide> {
        logger.info("Searching guides | filter=$input")
        return searchService.getGuides(input)
    }
}