package com.organizee.boundaries.db.services

import com.organizee.domain.guide.Category

interface CategoryService {
    fun saveAll(categories: List<Category>): List<Category>
    fun getAll(): List<Category>
    fun save(category: Category): Category
}