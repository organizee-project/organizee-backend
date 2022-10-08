package com.organizee.web.controllers.like

import com.organizee.domain.Page
import com.organizee.usecases.guide.AddLikeUseCase
import com.organizee.usecases.guide.GetLikesUseCase
import com.organizee.usecases.guide.RemoveLikeUseCase
import com.organizee.usecases.guide.commands.GetLikesCommand
import com.organizee.usecases.guide.commands.NewLikeCommand
import com.organizee.usecases.guide.commands.RemoveLikeCommand
import com.organizee.web.controllers.like.json.response.LikeResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping(value = ["v1/likes"], produces = [MediaType.APPLICATION_JSON_VALUE])
class LikeController(
    private val addLikeUseCase: AddLikeUseCase,
    private val getLikesUseCase: GetLikesUseCase,
    private val removeLikeUseCase: RemoveLikeUseCase
) {
    @PostMapping("/guide/{slug}")
    fun create(
        @PathVariable("slug") slug: String,
        principal: Principal
    ): LikeResponse {
        val like = addLikeUseCase.execute(NewLikeCommand(principal.name, slug))
        return LikeResponse.fromEntity(like)
    }

    @DeleteMapping("/guide/{slug}")
    fun delete(
        @PathVariable("slug") slug: String,
        principal: Principal
    ): ResponseEntity<Any> {
        removeLikeUseCase.execute(RemoveLikeCommand(principal.name, slug))
        return ResponseEntity.noContent().build()
    }


    @GetMapping("/guide/{slug}")
    fun getLikesByGuide(
        @PathVariable("slug") slug: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): ResponseEntity<Page<LikeResponse>> {
        val input = GetLikesCommand.create(slug, page, size)

        val likes = getLikesUseCase.execute(input)

        val response = Page(
            itens = likes.itens.map {
                LikeResponse.fromEntity(it)
            },
            totalPages = likes.totalPages,
            count = likes.count,
            currentPage = likes.currentPage,
            nextPage = likes.nextPage
        )

        return ResponseEntity.ok(response)
    }
}