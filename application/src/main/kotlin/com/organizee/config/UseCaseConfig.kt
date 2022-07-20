package com.organizee.config

import com.organizee.boundary.db.services.GuideService
import com.organizee.guide.CreateGuideUseCase
import com.organizee.guide.GetGuideUseCase
import com.organizee.guide.impl.CreateGuideUsecaseImpl
import com.organizee.guide.impl.GetGuideUsecaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig(val guideService: GuideService) {
    @Bean
    fun createNewGuideUsecase(): CreateGuideUseCase = CreateGuideUsecaseImpl(guideService)

    @Bean
    fun getGuideUsecase(): GetGuideUseCase = GetGuideUsecaseImpl(guideService)
}