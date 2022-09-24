package com.organizee.application.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource


@Configuration
class FirebaseConfig(
    @Value("classpath:service-account.json")
    val serviceAccount: Resource
) {


    @Bean
    fun firebaseAuth(): FirebaseAuth {
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount.inputStream))
            .build()

        val firebaseApp = FirebaseApp.initializeApp(options)
        return FirebaseAuth.getInstance(firebaseApp)
    }
}