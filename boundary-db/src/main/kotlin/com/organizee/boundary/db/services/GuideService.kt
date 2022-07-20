package com.organizee.boundary.db.services

import com.organizee.Guide

interface GuideService {
    fun create(guide: Guide): Guide
}