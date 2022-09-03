package com.organizee.web.controllers.category

import com.organizee.usecases.category.CreateCategoryUseCase
import com.organizee.usecases.category.GetCategoriesUsecase
import com.organizee.usecases.category.commands.NewCategoryCommand
import com.organizee.web.controllers.category.json.payload.CategoryPayload
import com.organizee.web.controllers.category.json.response.CategoryResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["v1/categories"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CategoryController(
    private val getCategoriesUsecase: GetCategoriesUsecase,
    private val createCategoryUseCase: CreateCategoryUseCase
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

    @PostMapping
    fun create(
        @RequestBody payload: CategoryPayload
    ): ResponseEntity<CategoryResponse> {
        val input = NewCategoryCommand(payload.name)
        val category = createCategoryUseCase.execute(input)

        val response = CategoryResponse(
            id = category.id,
            name = category.name,
            slug = category.slug
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

}