package com.example.mediascraper.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mediascraper.entity.Media;
import com.example.mediascraper.exception.ScrapingException;
import com.example.mediascraper.repository.MediaScraperRepository;
import com.example.mediascraper.scraper.HtmlScraper;

@Service
public class ScrapingService {
    private final HtmlScraper scraper;
    private final MediaScraperRepository repository;

    public ScrapingService(HtmlScraper scraper, MediaScraperRepository repository) {
        this.scraper = scraper;
        this.repository = repository;
    }

    @Transactional
    public void scrapeAndSave(List<String> urls) {
        for (String url : urls) {
            try {
                List<Media> mediaList = scraper.scrape(url);
                repository.saveAll(mediaList);
            } catch (IOException e) {
                throw new ScrapingException("Unable to scrape media from URL: " + url, e);
            }
        }
    }
}
