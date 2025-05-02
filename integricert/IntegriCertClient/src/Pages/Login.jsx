import React, { useState } from 'react'
import { useNavigate } from "react-router-dom";
import { Header } from '../Component/Header'
import { toast } from 'react-toastify';
import { getData, postData, deleteData } from '../Helper/Axios';

export const Login = () => {
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

  const [user, setUser] = useState({
    email: "",
    password: "",
  });

  const handleLogin = async (e) => {
    e.preventDefault();
    if (user.email === '') {
      onToast("Please enter the Email!!")
    }
    if (user.password === '') {
      onToast("Please enter the Password!!")
    }
    if (user.email && user.password) {
      try {
        const response = await postData({
          "url": "auth/signin",
          "data": {
            ...user,
          },
          "isJson": true
        });
        const data = await response.json();
        if (response.status === 200) {
          onToast('Login Successfull!!');
          const timeout = 86400000
          const issueTime = new Date().getTime();
          const validTime = new Date(Date.now() + timeout).getTime();
          localStorage.setItem("token", data.token);
          localStorage.setItem("issueTime", issueTime);
          localStorage.setItem("validTime", validTime);
          localStorage.setItem("role", data.user.role);

          localStorage.setItem("token", data.token);
          localStorage.setItem("issueTime", issueTime);
          localStorage.setItem("validTime", validTime);
          localStorage.setItem("role", data.user.role);
          
          window.location.href = "/";
        } else {
          onToast("Invalid Credentials");
        }
      } catch (error) {
        console.error('Error Login user:', error);
        alert('Server is under maintance. Please try again.');
      }
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
              fontSize: 27
            }}
          >
            Login
          </h4>
        </div>

        <div className="d-flex flex-row align-items-center login-box-seperator-container">
          <div className="login-box-seperator" />
        </div>
        <div className="email-login" style={{ backgroundColor: "#ffffff" }}>
          <input
            type="email"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Email"
            name="email"
            onChange={(e) => setUser({ ...user, email: e.target.value })}
            value={user.email}
            minLength={6}
          />
          <input
            type="password"
            className="password-input form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Password"
            name="password"
            onChange={(e) => setUser({ ...user, password: e.target.value })}
            value={user.password}
            minLength={6}
          />
        </div>
        <div className="submit-row" style={{ marginBottom: 8, paddingTop: 0 }}>
          <button
            className="btn btn-primary d-block box-shadow w-100"
            id="submit-id-submit"
            type="submit"
            onClick={(e) => handleLogin(e)}
          >
            Login
          </button>
          <div className="d-flex justify-content-between">
            <div className="form-check form-check-inline" id="form-check-rememberMe">
            </div>
            <a id="forgot-password-link" href="#">
              Forgot Password?
            </a>
          </div>
        </div>
        <div
          id="login-box-footer"
          style={{ padding: "10px 20px", paddingBottom: 23, paddingTop: 18 }}
        >
          <p style={{ marginBottom: 0 }}>
            Don't you have an account?
            <a id="register-link" href="singup">
              Sign Up!
            </a>
          </p>
        </div>
      </div>
    </>
  )
}
