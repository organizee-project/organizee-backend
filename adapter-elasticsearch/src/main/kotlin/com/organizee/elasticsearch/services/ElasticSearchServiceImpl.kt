package com.organizee.elasticsearch.services

import com.organizee.boundaries.search.SearchService
import com.organizee.domain.Page
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.domain.guide.Guide
import com.organizee.elasticsearch.entities.GuideEntity
import com.organizee.elasticsearch.repositories.GuideSearchRepository
import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHitSupport
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ElasticSearchServiceImpl(
    private val guideSearchRepository: GuideSearchRepository,
    private val elasticsearchOperations: ElasticsearchOperations
) : SearchService {
    override fun persist(guide: Guide) {

        val guide = GuideEntity.valueOf(guide)

        guideSearchRepository.save(guide);
    }

    override fun delete(guide: Guide) {
        guideSearchRepository.deleteById(guide.slug)
    }

    override fun getGuides(filter: String, page: Int, size: Int): Page<Guide> {
        val queryBuilder = QueryBuilders
            .multiMatchQuery(filter, "title", "topics", "categories")
            .fuzziness(Fuzziness.AUTO)

        val searchQuery = NativeSearchQueryBuilder()
            .withFilter(queryBuilder)
            .withPageable(PageRequest.of(page, size))
            .build()

        val articles: SearchHits<GuideEntity> =
            elasticsearchOperations.search(
                searchQuery,
                GuideEntity::class.java,
                IndexCoordinates.of("search")
            )

        val items = articles.searchHits.map {
            it.content.toEntity()
        }

        val page = SearchHitSupport.searchPageFor(articles, searchQuery.pageable)

        return Page(
            items = items,
            totalPages = page.totalPages,
            count = page.totalElements,
            currentPage = page.number,
            nextPage = if (page.totalPages > page.number + 1) page.number + 1 else page.number

        )

    }

    override fun suggest(filter: String): List<String> {

        val queryBuilder: QueryBuilder = QueryBuilders
            .wildcardQuery("title", "$filter*")

        val searchQuery = NativeSearchQueryBuilder()
            .withFilter(queryBuilder)
            .withPageable(PageRequest.of(0, 10))
            .build()

        val searchSuggestions: SearchHits<GuideEntity> = elasticsearchOperations.search(
            searchQuery,
            GuideEntity::class.java,
            IndexCoordinates.of("search")
        )

        val suggestions = searchSuggestions.searchHits.map {
            it.content.title
        }

        return suggestions.toSet().toList()
    }

    override fun update(newGuide: Guide, oldGuide: Guide) {
        val guide =
            guideSearchRepository.findByIdOrNull(oldGuide.slug) ?: throw ErrorCodes.GUIDE_NOT_FOUND(
                listOf(newGuide.slug)
            )

        guideSearchRepository.deleteById(oldGuide.slug)

        val newGuide = guide.copy(
            slug = newGuide.slug,
            title = newGuide.title,
            imgUrl = newGuide.imgUrl,
            subtitle = newGuide.subtitle,
            topics = newGuide.topics,
            content = newGuide.content,
            createdAt = oldGuide.createdAt.toString(),
            updatedAt = newGuide.updatedAt.toString()
        )

        guideSearchRepository.save(newGuide)
    }
}