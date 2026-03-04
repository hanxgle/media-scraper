const SCRAPER_API = "http://localhost:8080/scrape";
const MEDIA_API = "http://localhost:8080/media";

export async function scrapeUrls(urls) {
  const response = await fetch(`${SCRAPER_API}/scrape`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ urls }),
  });

  if (!response.ok) {
    throw new Error("Scraping failed");
  }
}

export async function fetchMedia({ page, size, type, search }) {
  const params = new URLSearchParams();

  params.append("page", page);
  params.append("size", size);

  if (type) params.append("type", type);
  if (search) params.append("search", search);

  const response = await fetch(`${MEDIA_API}/media?${params}`);

  if (!response.ok) {
    throw new Error("Failed to fetch media");
  }

  return response.json();
}