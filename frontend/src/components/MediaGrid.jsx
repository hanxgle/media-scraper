export default function MediaGrid({ media }) {
  return (
    <div style={{ display: "grid", gridTemplateColumns: "repeat(4, 1fr)", gap: 16 }}>
      {media.map(item => (
        <div key={item.id}>
          {item.type === "IMAGE" ? (
            <img
              src={item.mediaUrl}
              alt=""
              style={{ width: "100%", height: 150, objectFit: "cover" }}
            />
          ) : (
            <video
              src={item.mediaUrl}
              controls
              style={{ width: "100%", height: 150 }}
            />
          )}
        </div>
      ))}
    </div>
  );
}