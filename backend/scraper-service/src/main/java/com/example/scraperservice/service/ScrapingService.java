package com.example.scraperservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.scraperservice.exception.ScrapingException;
import com.example.scraperservice.scraper.HtmlScraper;

import com.example.scraperservice.dto.MediaDto;
import com.example.common.events.MediaEvent;

@Service
public class ScrapingService {
    private final HtmlScraper scraper;
    private final MediaProducer mediaProducer;

    public ScrapingService(HtmlScraper scraper, MediaProducer mediaProducer) {
        this.scraper = scraper;
        this.mediaProducer = mediaProducer;
    }

    public void scrapeAndSave(List<String> urls) {
        for (String url : urls) {
            try {
                List<MediaDto> mediaList = scraper.scrape(url);
                mediaList.forEach(
                    media -> mediaProducer.send(new MediaEvent(
                        media.getPageUrl(), 
                        media.getMediaUrl(), 
                        media.getType(),
                        media.getCreatedAt()
                    )));
            } catch (IOException e) {
                throw new ScrapingException("Unable to scrape media from URL: " + url, e);
            }
        }
    }
}
