package com.organizee.boundary.db.services

import com.organizee.guide.Category

interface CategoryService {
    fun getByIds(ids: List<Long>): List<Category>
}