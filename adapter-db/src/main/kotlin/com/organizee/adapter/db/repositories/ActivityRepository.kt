package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.ActivityEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ActivityRepository : PagingAndSortingRepository<ActivityEntity, UUID> {

    fun findAllByUserUsernameOrderByCreatedAtDesc(
        username: String,
        pageable: Pageable
    ): Page<ActivityEntity>
}