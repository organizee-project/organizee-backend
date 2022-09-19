package com.organizee.search

import com.amazonaws.services.cloudsearchdomain.AmazonCloudSearchDomain
import com.amazonaws.services.cloudsearchdomain.model.*
import com.organizee.boundaries.search.SearchService
import com.organizee.domain.guide.Category
import com.organizee.domain.guide.Guide
import com.organizee.domain.guide.GuideType
import com.organizee.search.entities.BatchRequest
import com.organizee.shared.extensions.JacksonSerializer
import org.apache.commons.io.IOUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class SearchServiceImpl(
    private val searchClient: AmazonCloudSearchDomain
) : SearchService {

    companion object {
        private val logger = LoggerFactory.getLogger(SearchServiceImpl::class.java)
    }

    override fun persist(guide: Guide) {
        val requestJson = JacksonSerializer.serialize(arrayOf(BatchRequest.createAdd(guide)))

        if (requestJson.isNullOrEmpty())
            return

        val uploadDocumentsRequest = UploadDocumentsRequest()
        val inputStream = IOUtils.toInputStream(requestJson, "UTF-8")
        uploadDocumentsRequest.documents = inputStream
        uploadDocumentsRequest.contentLength = requestJson.toByteArray().size.toLong()
        uploadDocumentsRequest.setContentType(ContentType.Applicationjson)

        searchClient.uploadDocuments(uploadDocumentsRequest)
    }

    override fun delete(guide: Guide) {
        val requestJson = JacksonSerializer.serialize(arrayOf(BatchRequest.createDelete(guide)))

        if (requestJson.isNullOrEmpty())
            return

        val uploadDocumentsRequest = UploadDocumentsRequest()
        val inputStream = IOUtils.toInputStream(requestJson, "UTF-8")
        uploadDocumentsRequest.documents = inputStream
        uploadDocumentsRequest.contentLength = requestJson.toByteArray().size.toLong()
        uploadDocumentsRequest.setContentType(ContentType.Applicationjson)

        searchClient.uploadDocuments(uploadDocumentsRequest)
    }

    override fun getGuides(filter: String): List<Guide> {
        logger.info("Searching guides | filter: $filter")

        val searchRequest = SearchRequest()
        searchRequest.query = filter

        return try {
            val response = searchClient.search(searchRequest)
            buildGuidesResponse(response)
        } catch (e: Exception) {
            logger.warn("Error getting search result returning empty list as a fallback", e)
            emptyList()
        }
    }

    override fun suggest(filter: String, suggester: String): List<String> {
        logger.info("Searching from suggestions | filter: $filter and suggester: $suggester")

        val suggestRequest = SuggestRequest()
        suggestRequest.query = filter
        suggestRequest.suggester = suggester

        return try {
            val response = searchClient.suggest(suggestRequest)
            response.suggest.suggestions.map { it.suggestion }
        } catch (e: Exception) {
            logger.warn(
                "Error getting search suggestions returning empty list as a fallback | filter: $filter and suggester: $suggester",
                e
            )
            emptyList()
        }
    }

    override fun update(newGuide: Guide, oldGuide: Guide) {
        delete(oldGuide)
        persist(newGuide)
    }

    private fun buildGuidesResponse(searchResponse: SearchResult) =
        searchResponse.hits.hit.map {
            Guide(
                slug = it.id,
                title = it.fields.getValue("title")[0],
                subtitle = it.fields.getValue("subtitle")[0],
                content = "",
                type = GuideType.PUBLIC,
                categories = it.fields.getOrDefault("categories", emptyList()).map { category ->
                    Category(name = category, slug = category)
                },
                topics = it.fields.getValue("topics"),
            )
        }

}

