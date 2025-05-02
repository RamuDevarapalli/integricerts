import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import { Home } from "../Pages/Home";
import { Login } from "../Pages/Login";
import { Singup } from "../Pages/Singup";
import { AboutUs } from "../Pages/AboutUs";
import { ContactUs } from "../Pages/ContactUs";
import { Protected } from "../Component/Protected";

import { Users } from "../Pages/Admin/Users";

import { AddStudent } from "../Pages/Education/AddStudent";
import { AddCollege } from "../Pages/Education/AddCollege";
import { Colleges } from "../Pages/Education/Colleges";
import { Students } from "../Pages/Education/Students";

import { AddEmployee } from "../Pages/Bussiness/AddEmployee";
import { AddCompany } from "../Pages/Bussiness/AddCompany";
import { Companies } from "../Pages/Bussiness/Companies";
import { Employees } from "../Pages/Bussiness/Employees";

import { Certificates } from "../Pages/Certificate/Certificates";
import { Validate } from "../Pages/Certificate/Validate";

export const Router = () => {
  const [isSignedIn, setIsSignedIn] = useState(
    localStorage.getItem("token") || false
  );

  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/singup" element={<Singup />} />
        <Route path="/about" element={<AboutUs />} />
        <Route path="/contact" element={<ContactUs />} />

        <Route
          path="/admin/users"
          element={
            <Protected isSignedIn={isSignedIn}>
              <Users />
            </Protected>
          }
        />

        <Route path="/certificate/validate" element={<Validate />} />   

        <Route
          path="/certificate/certificates"
          element={
            <Protected isSignedIn={isSignedIn}>
              <Certificates />
            </Protected>
          }
        />

        <Route path="/education/addStudent" element={<AddStudent />} />
        <Route path="/education/addCollege" element={<AddCollege />} />

        <Route
          path="/education/students"
          element={
            <Protected isSignedIn={isSignedIn}>
              <Students />
            </Protected>
          }
        />

        <Route
          path="/education/colleges"
          element={
            <Protected isSignedIn={isSignedIn}>
              <Colleges />
            </Protected>
          }
        />

        <Route path="/bussiness/addEmployee" element={<AddEmployee />} />
        <Route path="/bussiness/addCompany" element={<AddCompany />} />

        <Route
          path="/bussiness/employees"
          element={
            <Protected isSignedIn={isSignedIn}>
              <Employees />
            </Protected>
          }
        />

        <Route
          path="/bussiness/companies"
          element={
            <Protected isSignedIn={isSignedIn}>
              <Companies />
            </Protected>
          }
        />

      </Routes>
    </>
  );
};
