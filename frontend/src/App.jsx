import { useEffect, useState } from "react";
import { fetchMedia } from "./api/mediaApi";
import ScrapeForm from "./components/ScrapeForm";
import MediaGrid from "./components/MediaGrid";
import Pagination from "./components/Pagination";

export default function App() {
  const [media, setMedia] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [type, setType] = useState("");
  const [search, setSearch] = useState("");

  const loadData = async (pageNumber = page) => {
    const data = await fetchMedia({
      page: pageNumber,
      size: 10,
      type,
      search,
    });
    setMedia(data.content);
    setTotalPages(data.totalPages);
    setPage(data.page);
  };

  useEffect(() => {
    loadData(0);
  }, [type, search]);

  return (
    <div style={{ padding: 24 }}>
      <h2>Media Scraper</h2>

      <ScrapeForm onSuccess={() => loadData(0)} />

      <hr />

      <div>
        <select value={type} onChange={e => setType(e.target.value)}>
          <option value="">All</option>
          <option value="IMAGE">Images</option>
          <option value="VIDEO">Videos</option>
        </select>

        <input
          placeholder="Search"
          value={search}
          onChange={e => setSearch(e.target.value)}
          style={{ marginLeft: 8 }}
        />
      </div>

      <MediaGrid media={media} />

      <Pagination
        page={page}
        totalPages={totalPages}
        onChange={loadData}
      />
    </div>
  );
}