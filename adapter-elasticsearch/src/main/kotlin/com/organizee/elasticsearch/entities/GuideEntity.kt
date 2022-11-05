package com.organizee.elasticsearch.entities

import com.organizee.domain.guide.Category
import com.organizee.domain.guide.Guide
import com.organizee.domain.guide.GuideType
import com.organizee.domain.user.User
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDateTime


@Document(indexName = "search")
data class GuideEntity(
    @Id
    val slug: String,
    val title: String,
    val imgUrl: String,
    val subtitle: String,
    val content: String,
    val topics: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    @Field(type = FieldType.Nested)
    val user: UserEntity,
    val createdAt: String,
    val updatedAt: String? = null
) {
    companion object {
        fun valueOf(guide: Guide) = GuideEntity(
            title = guide.title,
            imgUrl = guide.imgUrl,
            slug = guide.slug,
            subtitle = guide.subtitle,
            content = guide.content,
            topics = guide.topics,
            categories = guide.categories.map { it.name },
            createdAt = guide.createdAt.toString(),
            updatedAt = guide.updatedAt?.toString(),
            user = UserEntity(
                name = guide.user.name,
                surname = guide.user.surname,
                username = guide.user.username,
                imgUrl = guide.imgUrl
            )
        )
    }

    fun toEntity() = Guide(
        title = title,
        imgUrl = imgUrl,
        slug = slug,
        subtitle = subtitle,
        content = content,
        type = GuideType.PUBLIC,
        user = User(
            id = "",
            name = user.name,
            surname = user.surname,
            username = user.imgUrl,
            description = "",
            imgUrl = user.imgUrl,
        ),
        categories = categories.map {
            Category(
                name = it,
                slug = it
            )
        },
        topics = topics,
        comments = listOf(),
        references = listOf(),
        likesCount = 0,
        createdAt = LocalDateTime.parse(createdAt),
        updatedAt = updatedAt?.let { LocalDateTime.parse(updatedAt) }

    )
}

data class UserEntity(
    val name: String,
    val surname: String,
    val username: String,
    val imgUrl: String
)

