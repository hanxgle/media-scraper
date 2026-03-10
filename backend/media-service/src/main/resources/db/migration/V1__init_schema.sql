CREATE TABLE media (
    id BIGSERIAL PRIMARY KEY,
    page_url TEXT,
    media_url TEXT,
    type VARCHAR(20),
    created_at TIMESTAMP
);