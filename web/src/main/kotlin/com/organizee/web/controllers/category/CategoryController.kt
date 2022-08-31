package com.organizee.web.controllers.category

import com.organizee.usecases.category.GetCategoriesUsecase
import com.organizee.web.controllers.category.json.response.CategoryResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = ["v1/categories"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CategoryController(
    private val getCategoriesUsecase: GetCategoriesUsecase,

    ) {

    @GetMapping
    fun list(): ResponseEntity<List<CategoryResponse>> {
        
        val response = getCategoriesUsecase.execute().map {
            CategoryResponse(
                id = it.id, name = it.name, slug = it.slug
            )
        }

        return ResponseEntity.ok(response)
    }

}