package com.example.scraperservice.exception;

public class ScrapingException extends RuntimeException {

    public ScrapingException(String message, Throwable cause) {
        super(message, cause);
    }
}