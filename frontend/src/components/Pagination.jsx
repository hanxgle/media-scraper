export default function Pagination({ page, totalPages, onChange }) {
  return (
    <div style={{ marginTop: 16 }}>
      <button onClick={() => onChange(page - 1)} disabled={page === 0}>
        Prev
      </button>

      <span style={{ margin: "0 8px" }}>
        Page {page + 1} of {totalPages}
      </span>

      <button
        onClick={() => onChange(page + 1)}
        disabled={page + 1 >= totalPages}
      >
        Next
      </button>
    </div>
  );
}