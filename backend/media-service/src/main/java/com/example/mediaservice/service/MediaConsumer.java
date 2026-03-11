package com.example.mediaservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.common.events.MediaEvent;
import com.example.mediaservice.entity.Media;
import com.example.mediaservice.enums.MediaType;
import com.example.mediaservice.repository.MediaScraperRepository;

@Service
public class MediaConsumer {

    private final MediaScraperRepository repository;

    public MediaConsumer(MediaScraperRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "media-topic", groupId = "media-group")
    public void consume(MediaEvent event) {
        Media media = new Media();
        media.setPageUrl(event.pageUrl());
        media.setMediaUrl(event.mediaUrl());
        media.setType(MediaType.valueOf(event.type()));
        media.setCreatedAt(event.createdAt());
        repository.save(media);
    }
}
