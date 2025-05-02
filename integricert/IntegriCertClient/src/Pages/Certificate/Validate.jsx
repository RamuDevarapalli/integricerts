import React, { useEffect, useState } from 'react';
import { Header } from '../../Component/Header';
import { Footer } from '../../Component/Footer';
import { toast } from 'react-toastify';
import { postData } from '../../Helper/Axios';

import '../../App.css';


export const Validate = () => {
  const [file, setFile] = useState(null);
  const [text, setText] = useState('');

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const onToast = (s) => {
    if ('Valid Certificate' === s) {
      toast.success(s, {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
    } else {
      toast.error(s, {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
    }
  }

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleTextChange = (e) => {
    setText(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!file && !text) {
      onToast("Please upload a file or enter text!");
      return;
    }

    const formData = new FormData();
    if (file) {
      formData.append("file", file);
    }
    if (text) {
      formData.append("text", text);
    }

    try {
      const response = await postData({
        url: "certificate/validate",
        data: formData,
        isJson: false, // Sending FormData, not JSON
      });

      if (response.status === 200) {
        const data = await response.text();
        onToast(data);
        // Wait for a moment to display the toast message
        await new Promise((resolve) => setTimeout(resolve, 1000));
        //window.location.reload();
      } else {
        onToast("Upload failed!");
      }
    } catch (error) {
      onToast("Error uploading!");
    }
  };

  return (
    <>
      <Header />
      <div className="container d-flex flex-column justify-content-center" id="login-box">
      <div className="login-box-header">
          <h4
            style={{
              color: "rgb(139,139,139)",
              marginBottom: 0,
              fontWeight: 400,
              fontSize: 27
            }}
          >
            Validate the certificate by Upload File or Enter Text
          </h4>
        </div>

        <div className="d-flex flex-row align-items-center login-box-seperator-container">
          <div className="login-box-seperator" />
        </div>

        <form onSubmit={handleSubmit} className="border p-3 rounded">
          {/* File Upload */}
          <div className="mb-3">
            <label className="form-label fw-semibold">Choose a file (PDF or Image):</label>
            <input
              type="file"
              className="form-control"
              accept=".pdf, .jpg, .jpeg, .png"
              onChange={handleFileChange}
            />
          </div>
          

          {/* Text Input */}
          <div className="mb-3">
            <label className="form-label">Or enter text:</label>
            <textarea
              className="form-control"
              rows="3"
              placeholder="Enter some text..."
              value={text}
              onChange={handleTextChange}
            ></textarea>
          </div>

          {/* Submit Button */}
          <button type="submit" className="btn btn-primary w-100">Upload</button>
        </form>

        <div className="d-flex flex-row align-items-center login-box-seperator-container">
          <div className="login-box-seperator" />
        </div>
        
      </div>
      <Footer />
    </>
  );
};
