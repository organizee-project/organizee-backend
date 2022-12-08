package com.organizee.adapter.db.repositories.custom

import com.organizee.adapter.db.entities.GuideEntity
import com.organizee.adapter.db.repositories.FollowRepository
import com.organizee.adapter.db.repositories.GuideCustomRepository
import com.organizee.boundaries.db.entities.FilterGuide
import com.organizee.boundaries.db.entities.FilterGuideFollowing
import com.organizee.boundaries.db.entities.FilterSortBy
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.Tuple
import javax.persistence.TypedQuery

@Repository
class GuideCustomRepositoryImpl(
    private val em: EntityManager,
    private val followRepository: FollowRepository
) : GuideCustomRepository {

    override fun getFilteredGuides(filter: FilterGuide): Page<GuideEntity> {

        var customSql = """ 
            select DISTINCT g, count(l) from GuideEntity g
            left join g.categories c
            left join g.likes l
            WHERE 1 = 1
            AND g.type = 'PUBLIC'
        """.trimIndent()

        var countSql = """
            select count( DISTINCT g.id ) from GuideEntity g
            left join g.categories c
            left join g.likes l
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
            else -> " ORDER BY count(l)  "
        }

        sqlOrder += when (filter.sort) {
            Sort.Direction.ASC -> " ASC"
            Sort.Direction.DESC -> " DESC"
        }

        countSql += "$sqlFilter GROUP BY g.id "
        customSql += "$sqlFilter GROUP BY g.id $sqlOrder"

        val count: TypedQuery<Long> = em.createQuery(countSql) as TypedQuery<Long>

        val query = em.createQuery(customSql, Tuple::class.java)
            .setFirstResult(filter.size * filter.page)
            .setMaxResults(filter.size)

        if (filter.category.isNotEmpty()) {
            query.setParameter("category", filter.category)
            count.setParameter("category", filter.category)
        }

        val total = count.resultList.size.toLong()


        return PageImpl(
            query.resultList.map { it.get(0) as GuideEntity },
            PageRequest.of(filter.page, filter.size),
            total
        )
    }

    override fun getFollowingFilteredGuides(filter: FilterGuideFollowing): Page<GuideEntity> {

        val following = followRepository.findAllByFromId(filter.userId).map { it.to.id }

        var customSql = """ 
            select DISTINCT g, count(l) from GuideEntity g
            left join g.categories c
            left join g.likes l
            WHERE 1 = 1
            AND g.user.id IN :following
            AND g.type = 'PUBLIC'
        """.trimIndent()

        var countSql = """
            select count( DISTINCT g.id ) from GuideEntity g
            left join g.categories c
            left join g.likes l
            WHERE 1 = 1
            AND g.user.id IN :following
            AND g.type = 'PUBLIC'
            """.trimIndent()

        var sqlFilter = ""
        var sqlOrder = ""


        sqlOrder += when (filter.sortBy) {
            FilterSortBy.DATE -> " ORDER BY g.createdAt"
            else -> " ORDER BY count(l)  "
        }

        sqlOrder += when (filter.sort) {
            Sort.Direction.ASC -> " ASC"
            Sort.Direction.DESC -> " DESC"
        }

        countSql += "$sqlFilter GROUP BY g.id "
        customSql += "$sqlFilter GROUP BY g.id $sqlOrder"

        val count: TypedQuery<Long> = em.createQuery(countSql) as TypedQuery<Long>

        val query = em.createQuery(customSql, Tuple::class.java)
            .setFirstResult(filter.size * filter.page)
            .setMaxResults(filter.size)

        query.setParameter("following", following)
        count.setParameter("following", following)

        val total = count.resultList.size.toLong()

        val response = try {
            PageImpl(
                query.resultList.map { it.get(0) as GuideEntity },
                PageRequest.of(filter.page, filter.size),
                total
            )

        } catch (e: Exception) {
            PageImpl(
                emptyList<GuideEntity>(),
                PageRequest.of(filter.page, filter.size),
                0L
            )
        }

        return response

    }
}
