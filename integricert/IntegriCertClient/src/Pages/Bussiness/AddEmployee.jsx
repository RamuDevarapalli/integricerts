import React, { useEffect, useState } from "react";
import { Header } from "../../Component/Header";
import { toast } from 'react-toastify';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { getData, postData, deleteData } from '../../Helper/Axios';

export const AddEmployee = () => {
  const [employee, setEmployee] = useState({
    name: "",
    gender: "",
    companyName: "",
    email: "",
    mobile: "",
    address: "",
    joiningDate: new Date(),
    designation: "",
    department: "",
    dob: new Date(),
  });

  const [companies, setCompanies] = useState([]);

  const fatchData = async () => {
      const response = await getData({
        "url": "company/all"
      });
      const data = await response.json()
      setCompanies(data);
    };
  
    useEffect(() => {
      fatchData();
    }, []);
  

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
    if (employee.name === '') {
      onToast("Please enter the Name!!")
    }    
    if (employee.gender === '') {
      onToast("Please enter the Gender!!")
    }
    if (employee.companyName === '') {
      onToast("Please enter the Company Name!!")
    }
    if (employee.joiningDate === '') {
      onToast("Please enter the Joining Date!!")
    }
    if (employee.address === '') {
      onToast("Please enter the Address!!")
    }
    if (employee.mobile === '') {
      onToast("Please enter the Mobile Number!!")
    }
    if (employee.email === '') {
      onToast("Please enter the Email!!")
    }
    if (employee.designation === '') {
      onToast("Please enter the Designation!!")
    }
    if (employee.department === '') {
      onToast("Please enter the Department!!")
    }
    if (employee.dob === '') {
      onToast("Please enter the DOB!!")
    }

    if (employee.name && employee.gender && employee.mobile && employee.email && employee.companyName && employee.joiningDate
      && employee.address && employee.designation && employee.department && employee.dob
    ) {
      const response = await postData({
        "url": "employee/save",
        "data": {
          ...employee,
        },
        "isJson": true
      });
      const data = await response.json();

      if (response.status === 200) {
        onToast("Employee Successfull!!")
        setTimeout(() => {
          window.location.href = "/bussiness/employees";
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
            Create Employee
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
            onChange={(e) => setEmployee({ ...employee, name: e.target.value })}
            value={employee.name}
            minLength={6}
            required
          />
          
          <input
            type="text"
            list="genderOptions"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Select Gender"
            name="gender"
            onChange={(e) => setEmployee({ ...employee, gender: e.target.value })}
            value={employee.gender}
            required
          />
          <datalist id="genderOptions">
            <option value="Male" />
            <option value="Female" />
            <option value="Other" />
          </datalist>

          <input
            type="text"
            list="companyOptions"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Select Company"
            name="companyName"
            onChange={(e) => setEmployee({ ...employee, companyName: e.target.value })}
            value={employee.companyName}
            required
          />
          <datalist id="companyOptions">
            {companies.map((company, index) => (
              <option key={index} value={company.name} />
            ))}
          </datalist>


          <DatePicker
            selected={employee.joiningDate}
            onChange={(e) => setEmployee({ ...employee, joiningDate: e})}
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholderText="Joining Date"
            name="joiningDate"
            dateFormat="dd-MM-yyyy" // Format the date as "01-12-2024"
            required
          />

          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Address"
            name="address"
            onChange={(e) => setEmployee({ ...employee, address: e.target.value })}
            value={employee.address}
            minLength={6}
            required
          />
          <input
            type="email"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Email"
            name="email"
            onChange={(e) => setEmployee({ ...employee, email: e.target.value })}
            value={employee.email}
            minLength={6}
          />
          <input
            type="mobile"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Mobile"
            name="mobile"
            onChange={(e) => setEmployee({ ...employee, mobile: e.target.value })}
            value={employee.mobile}
            minLength={10}
          />
          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Designation"
            name="designation"
            onChange={(e) => setEmployee({ ...employee, designation: e.target.value })}
            value={employee.designation}
            minLength={6}
            required
          />
           <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Department"
            name="department"
            onChange={(e) => setEmployee({ ...employee, department: e.target.value })}
            value={employee.department}
            minLength={6}
            required
          />          

          <DatePicker
            selected={employee.dob}
            onChange={(e) => setEmployee({ ...employee, dob: e})}
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholderText="DOB"
            name="dob"
            dateFormat="dd-MM-yyyy" // Format the date as "01-12-2024"
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
