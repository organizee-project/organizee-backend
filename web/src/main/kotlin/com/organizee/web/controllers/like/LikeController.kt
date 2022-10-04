package com.organizee.web.controllers.like

import com.organizee.usecases.guide.AddLikeUseCase
import com.organizee.usecases.guide.commands.NewLikeCommand
import com.organizee.web.controllers.like.json.response.LikeResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
@RequestMapping(value = ["v1/likes"], produces = [MediaType.APPLICATION_JSON_VALUE])
class LikeController(
    private val addLikeUseCase: AddLikeUseCase,
) {
    @PostMapping("/guide/{slug}")
    fun create(
        @PathVariable("slug") slug: String,
        principal: Principal
    ): LikeResponse {
        val like = addLikeUseCase.execute(NewLikeCommand(principal.name, slug))
        return LikeResponse.fromEntity(like)
    }


    /* @GetMapping("/guide/{slug}")
     fun getCommentsByGuide(
         @PathVariable("slug") slug: String,
         @RequestParam(defaultValue = "0") page: Int,
         @RequestParam(defaultValue = "3") size: Int
     ): ResponseEntity<Page<LikeResponse>> {
         val input = GetCommentsCommand.create(slug, page, size)

         val likes = getCommentsUseCase.execute(input)

         val response = Page(
             itens = comments.itens.map {
                 CommentResponse.fromEntity(it)
             },
             totalPages = comments.totalPages,
             count = comments.count,
             currentPage = comments.currentPage,
             nextPage = comments.nextPage
         )

         return ResponseEntity.ok(response)
     }
  */
}