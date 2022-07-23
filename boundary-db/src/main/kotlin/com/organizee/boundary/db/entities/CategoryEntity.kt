package com.organizee.boundary.db.entities

import com.organizee.guide.Category
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tb_category")
data class CategoryEntity(
    @Id
    @GeneratedValue
    val id: Long,
    @Column(nullable = false)
    val title: String,
    @Column(nullable = false)
    val slug: String
) : Serializable {
    fun toEntity() =
        Category(
            title = title,
            slug = slug,
        )
}