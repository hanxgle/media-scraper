package com.example.scraperservice.scraper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.example.scraperservice.dto.MediaDto;

@Component
public class HtmlScraper {
    public List<MediaDto> scrape(String pageUrl) throws IOException {
        List<MediaDto> results = new ArrayList<>();

        Document doc = Jsoup.connect(pageUrl).get();

        // Images
        for (Element img : doc.select("img[src]")) {
            results.add(buildMedia(
                pageUrl,
                img.absUrl("src"),
                "IMAGE"
            ));
        }

        // Videos
        for (Element video : doc.select("video source[src]")) {
            results.add(buildMedia(
                pageUrl,
                video.absUrl("src"),
                "VIDEO"
            ));
        }

        return results;
    }

    private MediaDto buildMedia(String pageUrl, String mediaUrl, String type) {
        MediaDto media = new MediaDto();
        media.setPageUrl(pageUrl);
        media.setMediaUrl(mediaUrl);
        media.setType(type);
        media.setCreatedAt(LocalDateTime.now());
        return media;
    }
}
