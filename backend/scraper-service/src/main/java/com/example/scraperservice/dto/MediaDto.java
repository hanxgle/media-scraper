package com.example.scraperservice.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaDto {

    private String pageUrl;
    private String mediaUrl;
    private String type;
    private LocalDateTime createdAt;
}
