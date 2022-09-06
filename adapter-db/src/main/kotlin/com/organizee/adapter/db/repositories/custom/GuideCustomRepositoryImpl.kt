package com.organizee.adapter.db.repositories.custom

import com.organizee.adapter.db.entities.GuideEntity
import com.organizee.adapter.db.repositories.GuideCustomRepository
import com.organizee.boundaries.db.entities.FilterGuide
import com.organizee.boundaries.db.entities.FilterSortBy
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.TypedQuery

@Repository
class GuideCustomRepositoryImpl(
    private val em: EntityManager
) : GuideCustomRepository {

    override fun getFilteredGuides(filter: FilterGuide): Page<GuideEntity> {

        var customSql = """ 
            select DISTINCT g from GuideEntity g
            join g.categories c
            WHERE 1 = 1
            AND g.type = 'PUBLIC'
        """.trimIndent()

        var countSql = """
            select count( DISTINCT g.id ) from GuideEntity g
            join g.categories c
            WHERE 1 = 1
            AND g.type = 'PUBLIC'
            """.trimIndent()

        var sqlFilter = ""
        var sqlOrder = ""

        if (filter.category.isNotEmpty()) {
            sqlFilter += " AND c.slug = :category"
        }

        sqlOrder += when (filter.sortBy) {
            FilterSortBy.DATE -> " ORDER BY g.createdAt"
            else -> " ORDER BY g.createdAt"
        }

        sqlOrder += when (filter.sort) {
            Sort.Direction.ASC -> " ASC"
            Sort.Direction.DESC -> " DESC"
        }

        countSql += sqlFilter
        customSql += sqlFilter + sqlOrder

        val count: TypedQuery<Long> = em.createQuery(countSql) as TypedQuery<Long>

        val query = em.createQuery(customSql, GuideEntity::class.java)
            .setFirstResult(filter.size * filter.page)
            .setMaxResults(filter.size)

        if (filter.category.isNotEmpty()) {
            query.setParameter("category", filter.category)
            count.setParameter("category", filter.category)
        }


        return PageImpl(
            query.resultList,
            PageRequest.of(filter.page, filter.size),
            count.singleResult
        )
    }

}