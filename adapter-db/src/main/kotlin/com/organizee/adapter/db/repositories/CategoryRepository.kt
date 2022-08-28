package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<CategoryEntity, Long> {
    fun findByIdIn(ids: List<Long>): List<CategoryEntity>
}