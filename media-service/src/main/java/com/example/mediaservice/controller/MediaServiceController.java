package com.example.mediaservice.controller;

import java.util.List;

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

import com.example.mediaservice.dto.MediaResponse;
import com.example.mediaservice.entity.Media;
import com.example.mediaservice.enums.MediaType;
import com.example.mediaservice.service.MediaService;

import com.example.mediaservice.dto.MediaDto;

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
        Page<Media> media = mediaService.getMedia(type, search, pageable);
        return ResponseEntity.ok(new MediaResponse(media));
    }

    @PostMapping("/media")
    public ResponseEntity<Void> saveMedia(@RequestBody List<MediaDto> mediaList) {
        mediaService.saveAll(mediaList);
        return ResponseEntity.ok().build();
    }
}