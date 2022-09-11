package com.organizee.usecases.search

import com.organizee.Usecase
import com.organizee.domain.guide.Guide

interface SearchGetGuidesUseCase : Usecase<String, List<Guide>> {

}