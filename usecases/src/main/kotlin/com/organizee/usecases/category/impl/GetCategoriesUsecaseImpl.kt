package com.organizee.usecases.category.impl

import com.organizee.boundaries.db.services.CategoryService
import com.organizee.domain.guide.Category
import com.organizee.usecases.category.GetCategoriesUsecase
import org.springframework.stereotype.Component

@Component
class GetCategoriesUsecaseImpl(
    private val categoryService: CategoryService
) : GetCategoriesUsecase {
    override fun execute(): List<Category> {
        return categoryService.getAll()
    }
}