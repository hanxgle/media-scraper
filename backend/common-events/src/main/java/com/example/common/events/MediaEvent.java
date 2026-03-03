package com.example.common.events;

import java.time.LocalDateTime;

public record MediaEvent(
    String pageUrl,
    String mediaUrl,
    String type,
    LocalDateTime createdAt
) {}
