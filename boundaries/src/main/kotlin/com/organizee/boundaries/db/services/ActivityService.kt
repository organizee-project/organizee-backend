package com.organizee.boundaries.db.services

import com.organizee.domain.Page
import com.organizee.domain.activity.Activity

interface ActivityService {
    fun save(activity: Activity)

    fun findAllByUser(username: String, page: Int, size: Int): Page<Activity>
}