package com.organizee.boundary.db.repositories

import com.organizee.boundary.db.entities.GuideEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GuideRepository : JpaRepository<GuideEntity, UUID> {

}