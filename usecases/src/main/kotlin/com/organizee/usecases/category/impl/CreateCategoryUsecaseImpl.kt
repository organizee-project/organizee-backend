package com.organizee.usecases.category.impl

import com.organizee.boundaries.db.services.CategoryService
import com.organizee.domain.guide.Category
import com.organizee.usecases.category.CreateCategoryUseCase
import com.organizee.usecases.category.commands.NewCategoryCommand
import org.springframework.stereotype.Component

@Component
class CreateCategoryUsecaseImpl(
    private val categoryService: CategoryService
) : CreateCategoryUseCase {
    override fun execute(input: NewCategoryCommand): Category {
        val model = Category.create(name = input.name)
        return categoryService.save(model)
    }

}