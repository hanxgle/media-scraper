package com.example.mediaservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.mediaservice.entity.Media;
import com.example.mediaservice.enums.MediaType;
import com.example.mediaservice.repository.MediaScraperRepository;

import com.example.mediaservice.dto.MediaDto;

@Service
public class MediaService {

    private final MediaScraperRepository repository;

    public MediaService(MediaScraperRepository repository) {
        this.repository = repository;
    }

    public Page<Media> getMedia(
        MediaType type,
        String search,
        Pageable pageable
    ) {
        if (type != null && search != null) {
            return repository.findByTypeAndMediaUrlContainingIgnoreCase(type, search, pageable);
        }
        else if (type != null) {
            return repository.findByType(type, pageable);
        }
        else if (search != null) {
            return repository.findByMediaUrlContainingIgnoreCase(search, pageable);
        }
        return repository.findAll(pageable);
    }

    @Transactional
    public void saveAll(List<MediaDto> mediaList) {
        List<Media> entities = mediaList.stream()
            .map(dto -> {
                Media media = new Media();
                media.setPageUrl(dto.getPageUrl());
                media.setMediaUrl(dto.getMediaUrl());
                media.setType(MediaType.valueOf(dto.getType()));
                media.setCreatedAt(dto.getCreatedAt());
                return media;
            })
            .toList();

        repository.saveAll(entities);
    }
}
