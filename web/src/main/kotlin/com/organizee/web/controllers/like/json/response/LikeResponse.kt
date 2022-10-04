package com.organizee.web.controllers.like.json.response

import com.organizee.domain.guide.Like
import com.organizee.web.controllers.shared.responses.BasicUserResponse
import java.time.LocalDateTime

data class LikeResponse(
    val user: BasicUserResponse,
    val createdAt: LocalDateTime
) {
    companion object {
        fun fromEntity(like: Like) = LikeResponse(
            user = BasicUserResponse(username = like.user.username, imgUrl = ""),
            createdAt = like.createdAt
        )
    }
}