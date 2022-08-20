package com.organizee.adapter.services

import com.organizee.boundary.db.entities.CategoryEntity
import com.organizee.boundary.db.repositories.CategoryRepository
import com.organizee.boundary.db.services.CategoryService
import com.organizee.guide.Category
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(private val repository: CategoryRepository) : CategoryService {
    override fun getByIds(ids: List<Long>): List<CategoryEntity> {
        return repository.findByIdIn(ids)
    }

    override fun saveAll(categories: List<Category>): List<Category> {
        val entities = categories.map {
            CategoryEntity(
                name = it.name,
                slug = it.slug
            )
        }

        return repository.saveAll(entities).map {
            Category(id = it.id, name = it.name, slug = it.slug)
        }
    }


}