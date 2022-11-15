package com.organizee.usecases.activity

import com.organizee.Usecase
import com.organizee.domain.Page
import com.organizee.domain.activity.Activity
import com.organizee.usecases.activity.commands.GetUserActivitiesCommand

interface GetUserActivitiesUseCase : Usecase<GetUserActivitiesCommand, Page<Activity>> {}