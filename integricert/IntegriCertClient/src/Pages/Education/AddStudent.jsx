import React, { useEffect, useState } from "react";
import { Header } from "../../Component/Header";
import { toast } from 'react-toastify';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { getData, postData, deleteData } from '../../Helper/Axios';

export const AddStudent = () => {
  const [student, setStudent] = useState({
    name: "",
    gender: "",
    college: "",
    course: "",
    email: "",
    mobile: "",
    address: "",
    academicYear: "2025-2028",
    courseResult: "",
    degree: "",
    dob: new Date(),
  });

  const [colleges, setColleges] = useState([]);

  const fatchData = async () => {
      const response = await getData({
        "url": "college/all"
      });
      const data = await response.json()
      setColleges(data);
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
    if (student.name === '') {
      onToast("Please enter the Name!!")
    }    
    if (student.gender === '') {
      onToast("Please enter the Gender!!")
    }
    if (student.collegeName === '') {
      onToast("Please enter the College Name!!")
    }
    if (student.course === '') {
      onToast("Please enter the Course!!")
    }
    if (student.address === '') {
      onToast("Please enter the Address!!")
    }
    if (student.mobile === '') {
      onToast("Please enter the Mobile Number!!")
    }
    if (student.email === '') {
      onToast("Please enter the Email!!")
    }
    if (student.academicYear === '') {
      onToast("Please enter the Academic Year!!")
    }
    if (student.courseResult === '') {
      onToast("Please enter the Course Result!!")
    }
    if (student.degree === '') {
      onToast("Please enter the Degree!!")
    }
    if (student.dob === '') {
      onToast("Please enter the DOB!!")
    }

    if (student.name && student.gender && student.mobile && student.email && student.collegeName && student.course
      && student.address && student.academicYear && student.courseResult && student.degree && student.dob
    ) {
      const response = await postData({
        "url": "student/save",
        "data": {
          ...student,
        },
        "isJson": true
      });
      const data = await response.json();

      if (response.status === 200) {
        onToast("Student Successfull!!")
        setTimeout(() => {
          window.location.href = "/education/students";
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
            Create Student
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
            onChange={(e) => setStudent({ ...student, name: e.target.value })}
            value={student.name}
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
            onChange={(e) => setStudent({ ...student, gender: e.target.value })}
            value={student.gender}
            required
          />
          <datalist id="genderOptions">
            <option value="Male" />
            <option value="Female" />
            <option value="Other" />
          </datalist>

          <input
            type="text"
            list="collegeOptions"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Select College"
            name="collegeName"
            onChange={(e) => setStudent({ ...student, collegeName: e.target.value })}
            value={student.collegeName}
            required
          />
          <datalist id="collegeOptions">
            {colleges.map((college, index) => (
              <option key={index} value={college.name} />
            ))}
          </datalist>

          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Course"
            name="course"
            onChange={(e) => setStudent({ ...student, course: e.target.value })}
            value={student.course}
            minLength={6}
            required
          />
          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Address"
            name="address"
            onChange={(e) => setStudent({ ...student, address: e.target.value })}
            value={student.address}
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
            onChange={(e) => setStudent({ ...student, email: e.target.value })}
            value={student.email}
            minLength={6}
          />
          <input
            type="mobile"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Mobile"
            name="mobile"
            onChange={(e) => setStudent({ ...student, mobile: e.target.value })}
            value={student.mobile}
            minLength={10}
          />
          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Academic Year"
            name="academicYear"
            onChange={(e) => setStudent({ ...student, academicYear: e.target.value })}
            value={student.academicYear}
            minLength={6}
            required
          />
           <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Course esult"
            name="courseResult"
            onChange={(e) => setStudent({ ...student, courseResult: e.target.value })}
            value={student.courseResult}
            minLength={6}
            required
          />
           <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            placeholder="Degree"
            name="degree"
            onChange={(e) => setStudent({ ...student, degree: e.target.value })}
            value={student.degree}
            minLength={6}
            required
          />

          <DatePicker
            selected={student.dob}
            onChange={(e) => setStudent({ ...student, dob: e})}
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
