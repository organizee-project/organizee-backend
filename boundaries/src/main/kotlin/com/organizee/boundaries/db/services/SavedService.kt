package com.organizee.boundaries.db.services

import com.organizee.domain.guide.Saved

interface SavedService {
    fun findSaved(userId: String, slug: String): Saved?
}