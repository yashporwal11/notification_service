package com.example.notificationService.NotificationService.repository;

import com.example.notificationService.NotificationService.entity.es.EsData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsRepository extends ElasticsearchRepository<EsData, Integer> {
}
