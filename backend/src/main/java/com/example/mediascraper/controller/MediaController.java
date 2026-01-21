package com.example.mediascraper.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediascraper.dto.MediaResponse;
import com.example.mediascraper.dto.ScrapeRequest;
import com.example.mediascraper.entity.Media;
import com.example.mediascraper.enums.MediaType;
import com.example.mediascraper.service.MediaService;
import com.example.mediascraper.service.ScrapingService;

@RestController
@RequestMapping("/api")
public class MediaController {

    private final ScrapingService scrapingService;
    private final MediaService mediaService;

    public MediaController(
        ScrapingService scrapingService,
        MediaService mediaService
    ) {
        this.scrapingService = scrapingService;
        this.mediaService = mediaService;
    }

    @PostMapping("/scrape")
    public ResponseEntity<Void> scrape(@RequestBody ScrapeRequest request) {
        if (request.getUrls() == null || request.getUrls().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        scrapingService.scrapeAndSave(request.getUrls());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/media")
    public ResponseEntity<MediaResponse> getMedia(
        @RequestParam(required = false) MediaType type,
        @RequestParam(required = false) String search,
        @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<Media> media = mediaService.getMedia(type, search, pageable);
        return ResponseEntity.ok(new MediaResponse(media));
    }
}