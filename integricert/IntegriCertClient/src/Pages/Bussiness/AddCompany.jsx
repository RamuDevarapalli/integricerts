import React, { useState } from "react";
import { Header } from "../../Component/Header";
import { toast } from 'react-toastify';
import { getData, postData, deleteData } from '../../Helper/Axios';

export const AddCompany = () => {
  const [company, setCompany] = useState({
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
    if (company.name === '') {
      onToast("Please enter the Name!!")
    }    
    if (company.location === '') {
      onToast("Please enter the Location!!")
    }
    if (company.mobile === '') {
      onToast("Please enter the Mobile Number!!")
    }
    if (company.email === '') {
      onToast("Please enter the Email!!")
    }
    if (company.registrationId === '') {
      onToast("Please enter the RegistrationId!!")
    }
    if (company.establishedYear === '') {
      onToast("Please enter the Established Year!!")
    }
    
    if (company.name && company.location && company.mobile && company.email && company.registrationId && company.establishedYear) {
      const response = await postData({
        "url": "company/save",
        "data": {
          ...company,
        },
        "isJson": true
      });
      const data = await response.json();

      if (response.status === 200) {
        onToast("Company Successfull!!")
        setTimeout(() => {
          window.location.href = "/bussiness/companies";
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
            Create Company
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
            onChange={(e) => setCompany({ ...company, name: e.target.value })}
            value={company.name}
            minLength={6}
            required
          />
          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Location"
            name="location"
            onChange={(e) => setCompany({ ...company, location: e.target.value })}
            value={company.location}
            minLength={6}
            required
          />
          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Registration Id"
            name="registrationId"
            onChange={(e) => setCompany({ ...company, registrationId: e.target.value })}
            value={company.registrationId}
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
              if (/^\d{0,4}$/.test(value)) {
                setCompany({ ...company, establishedYear: value });
              }
            }}
            value={company.establishedYear}
            maxLength={4}
            pattern="^\d{4}$"
            required
          />

          <input
            type="email"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Email"
            name="email"
            onChange={(e) => setCompany({ ...company, email: e.target.value })}
            value={company.email}
            minLength={6}
          />
          <input
            type="mobile"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Mobile"
            name="mobile"
            onChange={(e) => setCompany({ ...company, mobile: e.target.value })}
            value={company.mobile}
            minLength={10}
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
