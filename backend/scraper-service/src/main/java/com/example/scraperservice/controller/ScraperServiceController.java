package com.example.scraperservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scraperservice.dto.ScrapeRequest;
import com.example.scraperservice.service.ScrapingService;

@RestController
@RequestMapping("/api")
public class ScraperServiceController {

    private final ScrapingService scrapingService;

    public ScraperServiceController(
        ScrapingService scrapingService
    ) {
        this.scrapingService = scrapingService;
    }

    @PostMapping("/scrape")
    public ResponseEntity<Void> scrape(@RequestBody ScrapeRequest request) {
        if (request.getUrls() == null || request.getUrls().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        scrapingService.scrapeAndSave(request.getUrls());
        return ResponseEntity.ok().build();
    }
}