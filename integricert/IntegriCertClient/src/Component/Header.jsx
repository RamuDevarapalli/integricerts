import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getData, postData, deleteData } from '../Helper/Axios';

export const Header = () => {
  const [role, setRole] = useState(localStorage.getItem("role"));
  const [islogin, setislogin] = useState(localStorage.getItem("token"));
  const [count, setCount] = useState();
  const navigate = useNavigate();
  const handalRedirect = () => {
    if (islogin) {
      navigate(`/cart`);
    } else {
      navigate(`/login`);
    }
  };

  const cartData = async () => {
    // get cart item
    try {
      const response = await getData({
        "url": "cart/user"
      });
      const data = await response.json()

      if (data !== "") {
        setCount(data.count);
      } else {
        setCount(0);
      }
    } catch (error) {
      setCount(0);
    }
  };

  const handalLogout = () => {
    // localStorage.removeItem("token");
    localStorage.clear();
    setislogin(false)
    navigate(`/`);
  };

  useEffect(() => {
    cartData();
    //setCount(cartCount());
  }, []);



  return (
    <header className="header" data-header="">

      <div className="nav-wrapper">
      <div className="container" style={{justifyContent : "flex-end"}}>
          <div class="btn-group" style={{marginRight : "auto"}} >
            <h1 className="h1">
              <a href="/" className="logo">
              Integri<span className="span">Cert</span>
              </a>
            </h1>
            <button
              className="nav-open-btn"
              aria-label="Open Menu"
              data-nav-open-btn=""
            >
              <ion-icon name="menu-outline" />
            </button>
          </div>

          {islogin ? (
            <div class="btn-group">
              <button type="button" class="btn btn-secondary">
                <a href="/" className="navbar-link">Home</a>
              </button>
            </div>
          ) : (<></>)}
   

          {islogin && role === 'ADMIN' ? (
            <div class="btn-group">
              <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Admin
              </button>
              <div class="dropdown-menu dropdown-menu-right">
                <button class="dropdown-item" type="button">
                  <a href="/admin/users" className="navbar-link">Users</a>
                </button>
              </div>
            </div>
          ) : (<></>)}


          {islogin && role === 'ADMIN' ? (
            <div class="btn-group">
              <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Education
              </button>
              <div class="dropdown-menu dropdown-menu-right">
                <button class="dropdown-item" type="button">
                  <a href="/education/students" className="navbar-link">Students</a>
                </button>
                <button class="dropdown-item" type="button">
                  <a href="/education/colleges" className="navbar-link">Colleges</a>
                </button>
                <button class="dropdown-item" type="button">
                  <a href="/education/addStudent" className="navbar-link">Add Student</a>
                </button>
                <button class="dropdown-item" type="button">
                  <a href="/education/addCollege" className="navbar-link">Add College</a>
                </button>
              </div>
            </div>
          ) : (<></>)}

          {islogin && role === 'ADMIN' ? (
            <div class="btn-group">
              <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Certificate
              </button>
              <div class="dropdown-menu dropdown-menu-right">
                <button class="dropdown-item" type="button">
                  <a href="/certificate/certificates" className="navbar-link">Certificates</a>
                </button>
                <button class="dropdown-item" type="button">
                  <a href="/certificate/validate" className="navbar-link">Validate</a>
                </button>
              </div>
            </div>
          ) : (<></>)}


        {islogin && role === 'ADMIN' ? (
            <div class="btn-group">
              <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Bussiness
              </button>
              <div class="dropdown-menu dropdown-menu-right">
                <button class="dropdown-item" type="button">
                  <a href="/bussiness/employees" className="navbar-link">Employees</a>
                </button>
                <button class="dropdown-item" type="button">
                  <a href="/bussiness/companies" className="navbar-link">Companies</a>
                </button>
                <button class="dropdown-item" type="button">
                  <a href="/bussiness/addEmployee" className="navbar-link">Add Employee</a>
                </button>
                <button class="dropdown-item" type="button">
                  <a href="/bussiness/addCompany" className="navbar-link">Add Company</a>
                </button>
              </div>
            </div>
          ) : (<></>)}



          {islogin && role === 'USER' ? (
            <div class="btn-group">
              <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Education
              </button>
              <div class="dropdown-menu dropdown-menu-right">
                <button class="dropdown-item" type="button">
                  <a href="/education/students" className="navbar-link">Students</a>
                </button>
                <button class="dropdown-item" type="button">
                  <a href="/education/colleges" className="navbar-link">Colleges</a>
                </button>
              </div>
            </div>
          ) : (<></>)}

          {islogin && role === 'USER' ? (
            <div class="btn-group">
              <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Certificate
              </button>
              <div class="dropdown-menu dropdown-menu-right">                
                <button class="dropdown-item" type="button">
                  <a href="/certificate/validate" className="navbar-link">Validate</a>
                </button>
              </div>
            </div>
          ) : (<></>)}


        {islogin && role === 'USER' ? (
            <div class="btn-group">
              <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Bussiness
              </button>
              <div class="dropdown-menu dropdown-menu-right">
                <button class="dropdown-item" type="button">
                  <a href="/bussiness/employees" className="navbar-link">Employees</a>
                </button>
                <button class="dropdown-item" type="button">
                  <a href="/bussiness/companies" className="navbar-link">Companies</a>
                </button>
              </div>
            </div>
          ) : (<></>)}




          <div class="btn-group">
            {islogin ?
              <button
                className="header-action-btn"
                aria-label="Open shopping cart"
                data-panel-btn="cart"
                onClick={() => handalLogout()}
              >
                <ion-icon name="log-out-outline"></ion-icon>
              </button>
              : <button
                className="header-action-btn"
                aria-label="Open shopping cart"
                data-panel-btn="cart"
                onClick={handalRedirect}
              >
                <ion-icon name="person-circle-outline"></ion-icon>
              </button>}
          </div>

        </div>
      </div>

    </header>
  );
};
