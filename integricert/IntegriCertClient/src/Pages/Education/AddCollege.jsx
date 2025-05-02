import React, { useState } from "react";
import { Header } from "../../Component/Header";
import { toast } from 'react-toastify';
import { getData, postData, deleteData } from '../../Helper/Axios';

export const AddCollege = () => {
  const [college, setCollege] = useState({
    name: "",
    location: "",
    registrationId: "",
    establishedYear: 2025,
    email: "",
    mobile: "",
  });

  const onToast = (s) => {
    if ('Login Successfull!!' === s) {
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

  const handleSinup = async (e) => {
    e.preventDefault();
    if (college.name === '') {
      onToast("Please enter the Name!!")
    }    
    if (college.location === '') {
      onToast("Please enter the Location!!")
    }
    if (college.mobile === '') {
      onToast("Please enter the Mobile Number!!")
    }
    if (college.email === '') {
      onToast("Please enter the Email!!")
    }
    if (college.registrationId === '') {
      onToast("Please enter the RegistrationId!!")
    }
    if (college.establishedYear === '') {
      onToast("Please enter the Established Year!!")
    }
    
    if (college.name && college.location && college.mobile && college.email && college.registrationId && college.establishedYear) {
      const response = await postData({
        "url": "college/save",
        "data": {
          ...college,
        },
        "isJson": true
      });
      const data = await response.json();

      if (response.status === 200) {
        onToast("College Successfull!!")
        setTimeout(() => {
          window.location.href = "/education/colleges";
        }, 2000);
      } else {
        onToast("Something went wrong!!")
      }
    } else {
      onToast("Missing details!!")
    }
  };

  return (
    <>
      <Header />
      <div className="d-flex flex-column justify-content-center" id="login-box">
        <div className="login-box-header">
          <h4
            style={{
              color: "rgb(139,139,139)",
              marginBottom: 0,
              fontWeight: 400,
              fontSize: 27,
            }}
          >
            Create College
          </h4>
        </div>

        <div className="d-flex flex-row align-items-center login-box-seperator-container">
          <div className="login-box-seperator" />
        </div>
        <div className="email-login" style={{ backgroundColor: "#ffffff" }}>
          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Name"
            name="name"
            onChange={(e) => setCollege({ ...college, name: e.target.value })}
            value={college.name}
            minLength={6}
            required
          />
          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Location"
            name="location"
            onChange={(e) => setCollege({ ...college, location: e.target.value })}
            value={college.location}
            minLength={6}
            required
          />
          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Registration Id"
            name="registrationId"
            onChange={(e) => setCollege({ ...college, registrationId: e.target.value })}
            value={college.registrationId}
            minLength={6}
            required
          />
          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Established Year"
            name="establishedYear"
            onChange={(e) => {
              const value = e.target.value;
              setCollege({ ...college, establishedYear: value });
            }}
            value={college.establishedYear}
            maxLength={4}
            pattern="^\d{4}$"
            required
          />
          <input
            type="email"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Email"
            name="email"
            onChange={(e) => {
              const value = e.target.value.trim();
              setCollege({ ...college, email: value });
            }}
            value={college.email}
            minLength={6}
            maxLength={100}
            pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" 
            required
          />
          <input
            type="tel"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Mobile"
            name="mobile"
            onChange={(e) => {
              const value = e.target.value.trim(); 
              if (value.length <= 10) {
                setCollege({ ...college, mobile: value });
              }
            }}
            value={college.mobile}
            maxLength={10} 
            pattern="^\d{10}$"
            required
          />

        </div>
        <div className="submit-row" style={{ marginBottom: 0, paddingTop: 0 }}>
          <button
            className="btn btn-primary d-block box-shadow w-100"
            id="submit-id-submit"
            type="submit"
            onClick={(e) => handleSinup(e)}
          >
            Register
          </button>
        </div>
      </div>

    </>
  );
};
