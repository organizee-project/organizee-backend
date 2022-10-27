package com.organizee.adapter.db.entities

import com.organizee.domain.guide.Reference
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_reference")
data class ReferenceEntity(
    @Id
    val id: UUID,
    @Column(nullable = false)
    val url: String,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guide_id", nullable = false)
    val guide: GuideEntity,
    @Column
    val createdAt: LocalDateTime
) : Serializable {

    companion object {
        fun from(reference: Reference, guide: GuideEntity) =
            ReferenceEntity(
                id = reference.id,
                url = reference.url,
                guide,
                createdAt = LocalDateTime.now()
            )
    }

    fun toEntity() =
        Reference(
            id = id, url = url
        )
}