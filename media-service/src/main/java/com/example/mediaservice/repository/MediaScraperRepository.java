package com.example.mediaservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediaservice.entity.Media;
import com.example.mediaservice.enums.MediaType;

public interface MediaScraperRepository extends JpaRepository<Media, Long> {
     Page<Media> findByType(
        MediaType type,
        Pageable pageable
    );

    Page<Media> findByMediaUrlContainingIgnoreCase(
        String search,
        Pageable pageable
    );

    Page<Media> findByTypeAndMediaUrlContainingIgnoreCase(
        MediaType type,
        String search,
        Pageable pageable
    );
}
