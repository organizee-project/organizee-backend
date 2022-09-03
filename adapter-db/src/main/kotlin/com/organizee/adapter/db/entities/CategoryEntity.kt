package com.organizee.adapter.db.entities

import com.organizee.domain.guide.Category
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tb_category")
data class CategoryEntity(
    @Id
    @GeneratedValue
    val id: Long = 0,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val slug: String
) : Serializable {
    fun toEntity(): Category {
        return Category(
            id = id,
            name = name,
            slug = slug
        )
    }
}