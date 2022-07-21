package com.organizee.boundary.db.entities

import com.organizee.guide.Category
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class CategoryEntity(
    @Id
    @GeneratedValue
    val id: Long,
    @Column(nullable = false)
    val title: String,
    @Column(nullable = false)
    val slug: String,
    @Column
    val createdAt: LocalDateTime
) : Serializable {
    fun toEntity() =
        Category(
            title = title,
            slug = slug,
        )
}