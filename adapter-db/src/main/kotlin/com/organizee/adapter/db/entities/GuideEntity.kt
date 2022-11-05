package com.organizee.adapter.db.entities

import com.organizee.domain.guide.Guide
import com.organizee.domain.guide.GuideType
import org.hibernate.annotations.Type
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_guide")
data class GuideEntity(
    @Id
    val id: UUID,
    @Column(nullable = false)
    val title: String,
    @Column(nullable = false)
    val slug: String,
    @Column
    val subtitle: String,
    @Column
    @Type(type = "text")
    val content: String,
    @Column
    val type: String,
    @Column(unique = false, nullable = false, columnDefinition = "varchar default ''")
    val imgUrl: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @Column
    @CollectionTable(name = "tb_guide_topics", joinColumns = [JoinColumn(name = "guide_id")])
    @ElementCollection
    val topics: List<String> = emptyList(),
    @Column
    @ManyToMany
    val categories: List<CategoryEntity> = emptyList(),
    @Column
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "guide")
    val comments: List<CommentEntity> = emptyList(),
    @Column
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guide", cascade = [CascadeType.ALL])
    var references: List<ReferenceEntity> = emptyList(),

    @Column
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guide", cascade = [CascadeType.REMOVE])
    var likes: List<LikeEntity> = emptyList(),

    @Column
    val createdAt: LocalDateTime,
    @Column
    val updatedAt: LocalDateTime? = null
) : Serializable {
    companion object {
        fun from(guide: Guide, user: UserEntity, categories: List<CategoryEntity>): GuideEntity {
            val entity = GuideEntity(
                id = UUID.randomUUID(),
                title = guide.title,
                slug = guide.slug,
                subtitle = guide.subtitle,
                content = guide.content,
                type = guide.type.name,
                categories = categories,
                topics = guide.topics,
                user = user,
                createdAt = guide.createdAt,
                imgUrl = guide.imgUrl
            )

            val referencesEntity = guide.references.map { reference ->
                ReferenceEntity.from(reference, entity)
            }

            entity.references = referencesEntity

            return entity
        }


    }

    fun toEntity() =
        Guide(
            title = title,
            imgUrl = imgUrl,
            subtitle = subtitle,
            content = content,
            slug = slug,
            type = GuideType.valueOf(type),
            categories = categories.map { it.toEntity() },
            references = references.map { it.toEntity() },
            topics = topics,
            user = user.toEntity(),
            likesCount = likes.count(),
            createdAt = createdAt,
            updatedAt = updatedAt
        )
}