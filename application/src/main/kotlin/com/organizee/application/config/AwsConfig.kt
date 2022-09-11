package com.organizee.application.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.cloudsearchdomain.AmazonCloudSearchDomain
import com.amazonaws.services.cloudsearchdomain.AmazonCloudSearchDomainClientBuilder
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

    @Value("\${search.endpoint}")
    private val serviceEndpoint: String? = null

    @Bean
    fun basicAWSCredentials(): BasicAWSCredentials? {
        return BasicAWSCredentials(awsId, awsKey)
    }

    @Bean
    fun amazonSearch(): AmazonCloudSearchDomain = AmazonCloudSearchDomainClientBuilder.standard()
        .withEndpointConfiguration(
            AwsClientBuilder.EndpointConfiguration(
                serviceEndpoint,
                region
            )
        )
        .withCredentials(AWSStaticCredentialsProvider(basicAWSCredentials()))
        .build()
}