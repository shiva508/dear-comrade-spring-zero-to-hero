import React, { useState } from "react";
import "./UploadFile.css";
const UploadFile = () => {
  const [file, setFile] = useState(null);

  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const handleUploadClick = () => {
    if (!file) {
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch("/api/blob/uploadMultipart", {
      method: "POST",
      body: formData,
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("File uploaded successfully:", data);
      })
      .catch((error) => {
        console.error("Error uploading file:", error);
      });
  };
  return (
    <div>
      <div className="button">
        <input type="file" onChange={handleFileChange} />
      </div>

      <button onClick={handleUploadClick}>Upload</button>
    </div>
  );
};

export default UploadFile;
