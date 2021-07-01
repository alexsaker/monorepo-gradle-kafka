package com.demo.warehouse;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import shared.models.ArticleEvent;

/**
 * ArticleListener
 */
@Service
public class ArticleEventConsumer {

    @KafkaListener(topics = "${warehouse.kafka-topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void listenToArticleEvents(ArticleEvent articleEvent) {
        System.out.println(articleEvent);
    }

}