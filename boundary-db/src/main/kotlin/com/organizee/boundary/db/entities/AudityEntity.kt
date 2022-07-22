package com.organizee.boundary.db.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class AudityEntity {
    @Column
    @CreationTimestamp
    lateinit var createdAt: LocalDateTime

    @Column
    @UpdateTimestamp
    lateinit var updatedAt: LocalDateTime
}