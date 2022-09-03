package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.CategoryEntity
import com.organizee.adapter.db.repositories.CategoryRepository
import com.organizee.boundaries.db.services.CategoryService
import com.organizee.domain.guide.Category
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(private val repository: CategoryRepository) : CategoryService {
    override fun saveAll(categories: List<Category>): List<Category> {
        val entities = categories.map {
            CategoryEntity(
                name = it.name,
                slug = it.slug
            )
        }

        return repository.saveAll(entities).map {
            it.toEntity()
        }
    }

    override fun getAll(): List<Category> {
        return repository.findAll().map {
            it.toEntity()
        }
    }

    override fun getAllIdsIn(ids: List<Long>): List<Category> {
        return repository.findByIdIn(ids).map { it.toEntity() }
    }

    override fun save(category: Category): Category {
        return repository.save(CategoryEntity(name = category.name, slug = category.slug))
            .toEntity()
    }
}