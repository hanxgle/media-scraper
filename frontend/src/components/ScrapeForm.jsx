import { useState } from "react";
import { scrapeUrls } from "../api/mediaApi";

export default function ScrapeForm({ onSuccess }) {
  const [text, setText] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async () => {
    const urls = text
      .split("\n")
      .map(u => u.trim())
      .filter(Boolean);

    if (urls.length === 0) return;

    setLoading(true);
    try {
      await scrapeUrls(urls);
      setText("");
      onSuccess();
    } catch (e) {
      alert(e.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h3>Scrape URLs</h3>
      <textarea
        rows="4"
        placeholder="Enter one URL per line"
        value={text}
        onChange={e => setText(e.target.value)}
      />
      <br />
      <button onClick={handleSubmit} disabled={loading}>
        {loading ? "Scraping..." : "Scrape"}
      </button>
    </div>
  );
}