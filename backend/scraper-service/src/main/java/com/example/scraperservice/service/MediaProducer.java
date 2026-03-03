package com.example.scraperservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.common.events.MediaEvent;

@Service
public class MediaProducer {

    private final KafkaTemplate<String, MediaEvent> kafkaTemplate;

    public MediaProducer(KafkaTemplate<String, MediaEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(MediaEvent event) {
        kafkaTemplate.send("media-topic", event);
    }
}
