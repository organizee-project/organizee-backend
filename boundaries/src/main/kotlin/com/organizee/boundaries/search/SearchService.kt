package com.organizee.boundaries.search

import com.organizee.domain.guide.Guide

interface SearchService {
    fun persist(guide: Guide)
    fun getGuides(filter: String): List<Guide>
    fun suggest(filter: String, suggester: String): List<String>
}