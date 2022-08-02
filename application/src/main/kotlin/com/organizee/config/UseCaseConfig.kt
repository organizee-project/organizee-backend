package com.organizee.config

import com.organizee.boundary.db.services.CommentService
import com.organizee.boundary.db.services.GuideService
import com.organizee.boundary.db.services.UserService
import com.organizee.guide.CreateCommentUseCase
import com.organizee.guide.CreateGuideUseCase
import com.organizee.guide.GetGuideUseCase
import com.organizee.guide.GetPublicGuidesUseCase
import com.organizee.guide.impl.CreateCommentUseCaseImpl
import com.organizee.guide.impl.CreateGuideUsecaseImpl
import com.organizee.guide.impl.GetGuideUsecaseImpl
import com.organizee.guide.impl.GetPublicGuidesUsecaseImpl
import com.organizee.user.CreateUserUseCase
import com.organizee.user.impl.CreateUserUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig(
    val guideService: GuideService,
    val commentService: CommentService,
    val userService: UserService
) {
    @Bean
    fun createNewGuideUsecase(): CreateGuideUseCase = CreateGuideUsecaseImpl(guideService)

    @Bean
    fun getGuideUsecase(): GetGuideUseCase = GetGuideUsecaseImpl(guideService)

    @Bean
    fun getPublicGuides(): GetPublicGuidesUseCase = GetPublicGuidesUsecaseImpl(guideService)

    @Bean
    fun commentUsecase(): CreateCommentUseCase = CreateCommentUseCaseImpl(commentService)

    @Bean
    fun createUserUseCase(): CreateUserUseCase = CreateUserUseCaseImpl(userService)
}