package com.organizee.config

import com.organizee.guide.CreateGuideUseCase
import com.organizee.guide.impl.CreateGuideUsecaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig {
    @Bean
    fun createNewGuideUsecase(): CreateGuideUseCase = CreateGuideUsecaseImpl()
}