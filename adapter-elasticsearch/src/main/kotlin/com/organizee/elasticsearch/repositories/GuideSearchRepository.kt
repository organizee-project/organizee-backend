package com.organizee.elasticsearch.repositories

import com.organizee.elasticsearch.entities.GuideEntity
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface GuideSearchRepository : ElasticsearchRepository<GuideEntity, String> {
}