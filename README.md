# Media Scraper Application

This is a simple full-stack web application built as a technical assignment.

The application accepts a list of web page URLs, scrapes image and video URLs from those pages, stores the results in a SQL database, and displays them in a web interface with pagination and filtering.

---

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Jsoup (HTML parsing)
- Gradle

### Frontend
- React
- JavaScript
- Fetch API
- Nginx

### Infrastructure
- Docker
- Docker Compose

---

## Features

- Submit multiple web page URLs for scraping
- Extract image and video URLs from HTML pages
- Store scraped media in a SQL database
- REST API with pagination and filtering
- Frontend UI to:
  - Submit URLs
  - View images and videos
  - Filter by media type (IMAGE / VIDEO)
  - Search by media URL
  - Navigate pages
- Fully containerized setup using Docker Compose

---

## Architecture Overview

frontend (React + Nginx) <br>
&emsp;&emsp;| <br>
&emsp;&emsp;| HTTP (REST) <br>
&emsp;&emsp;| <br>
backend (Spring Boot API) <br>
&emsp;&emsp;| <br>
&emsp;&emsp;| JPA <br>
&emsp;&emsp;| <br>
database (PostgreSQL)

---

## API Endpoints

### Scrape Media

**POST** `/api/scrape`

Request body:

```json
{
  "urls": [
    "https://example.com",
    "https://another-site.com"
  ]
}
```
Response:
200 OK if the request is accepted

### Get Media (Paginated)

**GET** `/api/media`

Query parameters:

- page (default: 0)
- size (default: 10)
- type (optional: IMAGE or VIDEO)
- search (optional text search)

Example response:
```json
{
    "page": 0,
    "content": [
        {
            "id": 25,
            "pageUrl": "https://example.com",
            "mediaUrl": "https://example.com/video.mp4",
            "type": "VIDEO",
            "createdAt": "2026-01-21T14:53:30.488733"
        }
    ],
    "size": 10,
    "totalElements": 1,
    "totalPages": 1
}
```

## Frontend Usage

- Enter one URL per line in the input box
- Click Scrape
- Use filters and search to narrow results
- Use pagination to navigate through media
- Images are displayed inline and videos are playable

## Running the application:

Prerequisites:

- Docker
- Docker Compose

No need to install Java, Gradle, Node.js, or PostgreSQL locally.

Steps:

```
git clone https://github.com/hanxgle/media-scraper.git
cd media-scraper
docker compose up --build
```

## Access:
- Frontend: http://localhost:3000
- Backend: http://localhost:8080

## Testing:
- A basic Spring Boot context load test is included
- No load testing or concurrency testing is implemented

The assignment mentions handling a high number of concurrent scraping requests. This project focuses on implementing the core functionality and architecture. With more time, async processing and load testing tools could be added.

## Limitations

- Scraping works only for static HTML content
- JavaScript-rendered pages may not be fully supported
- Scraping is performed synchronously
- No advanced rate limiting or retry logic

## Possible Improvements

- Asynchronous scraping
- Background job processing
- Improved error handling
- Better frontend UI and styling
- Load and stress testing
- Authentication and authorization

## License

This project is provided for evaluation purposes only.