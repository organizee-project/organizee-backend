package com.organizee.usecases.category

import com.organizee.domain.guide.Category
import com.organizee.usecases.category.commands.NewCategoryCommand

interface CreateCategoryUseCase {
    fun execute(input: NewCategoryCommand): Category
}