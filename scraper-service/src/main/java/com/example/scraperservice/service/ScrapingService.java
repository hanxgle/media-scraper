package com.example.scraperservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.scraperservice.exception.ScrapingException;
import com.example.scraperservice.scraper.HtmlScraper;

import com.example.scraperservice.dto.MediaDto;

@Service
public class ScrapingService {
    private final HtmlScraper scraper;
    private final RestTemplate restTemplate;

    public ScrapingService(HtmlScraper scraper, RestTemplate restTemplate) {
        this.scraper = scraper;
        this.restTemplate = restTemplate;
    }

    public void scrapeAndSave(List<String> urls) {
        for (String url : urls) {
            try {
                List<MediaDto> mediaList = scraper.scrape(url);
                restTemplate.postForObject(
                    "http://media-service:8081/api/media",
                    mediaList,
                    Void.class
                );
            } catch (IOException e) {
                throw new ScrapingException("Unable to scrape media from URL: " + url, e);
            }
        }
    }
}
