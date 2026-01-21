package com.example.mediascraper.scraper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.example.mediascraper.entity.Media;
import com.example.mediascraper.enums.MediaType;

@Component
public class HtmlScraper {
    public List<Media> scrape(String pageUrl) throws IOException {
        List<Media> results = new ArrayList<>();

        Document doc = Jsoup.connect(pageUrl).get();

        // Images
        for (Element img : doc.select("img[src]")) {
            results.add(buildMedia(
                pageUrl,
                img.absUrl("src"),
                MediaType.IMAGE
            ));
        }

        // Videos
        for (Element video : doc.select("video source[src]")) {
            results.add(buildMedia(
                pageUrl,
                video.absUrl("src"),
                MediaType.VIDEO
            ));
        }

        return results;
    }

    private Media buildMedia(String pageUrl, String mediaUrl, MediaType type) {
        Media media = new Media();
        media.setPageUrl(pageUrl);
        media.setMediaUrl(mediaUrl);
        media.setType(type);
        media.setCreatedAt(LocalDateTime.now());
        return media;
    }
}
