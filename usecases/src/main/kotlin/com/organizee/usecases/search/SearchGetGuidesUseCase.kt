package com.organizee.usecases.search

import com.organizee.Usecase
import com.organizee.domain.Page
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.commands.SearchGetPublicGuidesCommand

interface SearchGetGuidesUseCase : Usecase<SearchGetPublicGuidesCommand, Page<Guide>> {

}