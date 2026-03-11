package com.example.mediaservice.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.mediaservice.dto.MediaResponse;
import com.example.mediaservice.entity.Media;
import com.example.mediaservice.enums.MediaType;
import com.example.mediaservice.repository.MediaScraperRepository;

@Service
public class MediaService {

    private final MediaScraperRepository repository;


    public MediaService(MediaScraperRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "media_cache", key = "{ #type, #search, #pageable.pageNumber }")
    public MediaResponse getMedia(
        MediaType type,
        String search,
        Pageable pageable
    ) {
        Page<Media> mediaPage;
        System.out.println("DEBUG: Fetching data from getMedia...");
        if (type != null && search != null) {
            mediaPage = repository.findByTypeAndMediaUrlContainingIgnoreCase(type, search, pageable);
        }
        else if (type != null) {
            mediaPage = repository.findByType(type, pageable);
        }
        else if (search != null) {
            mediaPage = repository.findByMediaUrlContainingIgnoreCase(search, pageable);
        }
        mediaPage = repository.findAll(pageable);

        return new MediaResponse(mediaPage);
    }
}
