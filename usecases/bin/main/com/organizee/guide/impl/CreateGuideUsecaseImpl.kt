package com.organizee.guide.impl

import com.organizee.Guide
import com.organizee.guide.CreateGuideUseCase
import com.organizee.guide.commands.NewGuideCommand

class CreateGuideUsecaseImpl(val guideService: GuideService) : CreateGuideUseCase {
    override fun execute(input: NewGuideCommand): Guide {

        Guide.create(title = input.title, subtitle = input.subtitle, content = input.content)


    }
}