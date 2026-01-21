package com.example.mediascraper.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.mediascraper.entity.Media;

import lombok.Getter;


@Getter
public class MediaResponse {
    private List<Media> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public MediaResponse(Page<Media> page) {
        this.content = page.getContent();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}
