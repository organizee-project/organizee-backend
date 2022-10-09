package com.organizee.domain.guide

import com.organizee.domain.user.User
import java.util.*

data class Saved(
    val id: UUID,
    val user: User,
    val guide: Guide,
) {
    companion object {
        fun create(user: User, guide: Guide) = Saved(
            id = UUID.randomUUID(),
            user = user,
            guide = guide
        )
    }
}
