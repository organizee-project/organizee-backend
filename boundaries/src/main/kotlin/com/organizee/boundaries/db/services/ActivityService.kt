package com.organizee.boundaries.db.services

import com.organizee.domain.activity.Activity

interface ActivityService {
    fun save(activity: Activity)
}