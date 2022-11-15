package com.organizee.application.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AwsConfig {

    @Value("\${aws.cloudsearch.accessKey}")
    private val awsId: String? = null

    @Value("\${aws.cloudsearch.secretKey}")
    private val awsKey: String? = null

    @Value("\${aws.cloudsearch.region}")
    private val region: String? = null

    @Bean
    fun basicAWSCredentials(): BasicAWSCredentials? {
        return BasicAWSCredentials(awsId, awsKey)
    }

    @Bean
    fun amazonS3(): AmazonS3 {

        val client = AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(basicAWSCredentials()))

        client.region = region

        return client.build()
    }
}