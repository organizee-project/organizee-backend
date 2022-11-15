package com.organizee.boundaries.search

import com.organizee.domain.Page
import com.organizee.domain.guide.Guide

interface SearchService {
    fun persist(guide: Guide)
    fun delete(guide: Guide)
    fun getGuides(filter: String, page: Int, size: Int): Page<Guide>
    fun suggest(filter: String): List<String>
    fun update(newGuide: Guide, oldGuide: Guide)
}