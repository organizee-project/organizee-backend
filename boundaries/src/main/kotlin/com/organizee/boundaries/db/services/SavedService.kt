package com.organizee.boundaries.db.services

import com.organizee.domain.guide.Saved

interface SavedService {
    fun findSavedOrThrow(userId: String, slug: String): Saved
    fun findSaved(userId: String, slug: String): Saved?
    fun remove(saved: Saved)
}