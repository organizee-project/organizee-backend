package com.organizee.boundary.db.services

import com.organizee.guide.Guide

interface GuideService {
    fun create(guide: Guide, categories: List<Long>): Guide
    fun getGuide(slug: String): Guide
}