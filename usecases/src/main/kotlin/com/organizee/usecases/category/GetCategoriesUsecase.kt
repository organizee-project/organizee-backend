package com.organizee.usecases.category

import com.organizee.domain.guide.Category

interface GetCategoriesUsecase {
    fun execute(): List<Category>
}