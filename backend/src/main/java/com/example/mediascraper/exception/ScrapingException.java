package com.example.mediascraper.exception;

public class ScrapingException extends RuntimeException {

    public ScrapingException(String message, Throwable cause) {
        super(message, cause);
    }
}