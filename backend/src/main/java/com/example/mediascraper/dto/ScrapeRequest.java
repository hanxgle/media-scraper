package com.example.mediascraper.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScrapeRequest {

    private List<String> urls;
}
