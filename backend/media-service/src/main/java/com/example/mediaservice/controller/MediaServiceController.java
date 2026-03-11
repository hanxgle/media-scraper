package com.example.mediaservice.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediaservice.dto.MediaResponse;
import com.example.mediaservice.enums.MediaType;
import com.example.mediaservice.service.MediaService;

@RestController
@RequestMapping("/api")
public class MediaServiceController {

    private final MediaService mediaService;

    public MediaServiceController(
        MediaService mediaService
    ) {
        this.mediaService = mediaService;
    }

    @GetMapping("/media")
    public ResponseEntity<MediaResponse> getMedia(
        @RequestParam(required = false) MediaType type,
        @RequestParam(required = false) String search,
        @PageableDefault(size = 10) Pageable pageable
    ) {
        MediaResponse mediaResponse = mediaService.getMedia(type, search, pageable);
        return ResponseEntity.ok(mediaResponse);
    }
}