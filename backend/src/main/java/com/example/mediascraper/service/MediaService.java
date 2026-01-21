package com.example.mediascraper.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.mediascraper.entity.Media;
import com.example.mediascraper.enums.MediaType;
import com.example.mediascraper.repository.MediaScraperRepository;

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
}
