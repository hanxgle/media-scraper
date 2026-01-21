package com.example.mediascraper.entity;

import java.time.LocalDateTime;

import com.example.mediascraper.enums.MediaType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "media")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pageUrl;

    @Column(length = 1000)
    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    private MediaType type; // IMAGE or VIDEO

    private LocalDateTime createdAt;
}
