package com.organizee.boundary.db.services

import com.organizee.boundary.db.entities.CategoryEntity
import com.organizee.guide.Category

interface CategoryService {
    fun getByIds(ids: List<Long>): List<CategoryEntity>
    fun saveAll(categories: List<Category>): List<Category>
}