package com.organizee.adapter.services

import com.organizee.boundary.db.repositories.CategoryRepository
import com.organizee.boundary.db.services.CategoryService
import com.organizee.guide.Category
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(private val repository: CategoryRepository) : CategoryService {
    override fun getByIds(ids: List<Long>): List<Category> {
        return repository.findByIdIn(ids).map { it.toEntity() }
    }
}