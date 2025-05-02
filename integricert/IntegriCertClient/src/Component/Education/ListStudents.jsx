import axios from "axios";
import { useNavigate } from "react-router-dom";
import React, { useEffect, useState } from "react";
import DataTable from 'react-data-table-component';
import { ToastContainer, toast } from 'react-toastify';

import { Footer } from "../Footer";
import { Header } from "../Header";
import FilterData from "../../Helper/FilterData";
import { getData, postData, deleteData } from '../../Helper/Axios';

import '../../basic.css';

export const ListStudents = ({ data, setLoading }) => {
  const [isSignedIn, setIsSignedIn] = useState(
    localStorage.getItem("token") || false
  );
  const [islogin, setislogin] = useState(localStorage.getItem("token"));
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [count, setCount] = useState();
  const navigate = useNavigate();

  const [pdata, setData] = useState(data);
  const [updatedRows, setUpdatedRows] = useState([]);

  const handleInputChange = (e, id, key) => {
    const newData = [...data];
    const index = newData.findIndex(item => item.id == id);
    newData[index][key] = e.target.value;
    setData(newData);

    const updatedRow = { id, [key]: e.target.value };
    if (!updatedRows.some(row => row.id == id)) {
      setUpdatedRows([...updatedRows, updatedRow]);
    } else {
      const updatedRowsCopy = [...updatedRows];
      const updatedRowIndex = updatedRowsCopy.findIndex(row => row.id == id);
      updatedRowsCopy[updatedRowIndex][key] = e.target.value;
      setUpdatedRows(updatedRowsCopy);
    }
  };

  const onToast = (s) => {
    if ('Delete Student Successfull!!' == s || 'Update Student Successfull!!' == s) {
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

  const handleDeleteRow = async (row) => {
    const response = await deleteData({
      "url": `student/delete/${row.id}`
    });
    const data = await response.json();

    if (response.status == 200) {
      onToast("Delete Student Successfull!!")
      window.location.reload();
    } else {
      onToast("Something went wrong!!")
    }
  }

  const handleEditRow = async (row) => {
    const response = await postData({
      "url": "student/update",
      "data": row,
      "isJson": true
    });
    const data = await response.json();

    if (response.status == 200) {
      onToast("Update Student Successfull!!")
      window.location.reload();
    } else {
      onToast("Something went wrong!!")
    }
  }
  
  const handleGenerateRow = async (row) => {
    const response = await postData({
      "url": "student/generate/certificate",
      "data": row,
      "isJson": true
    });
    const data = await response.json();

    if (response.status == 200) {
      onToast("Generate Certificate Successfull!!")
      window.location.reload();
    } else {
      onToast("Something went wrong!!")
    }
  }

  const handleDownloadRow = async (row) => {
    const response = await postData({
      "url": "student/download/certificate",
      "data": row,
      "isJson": true
    });

    if (response.status == 200) {
      // Convert response to Blob
      const blob = await response.blob();
      const url = URL.createObjectURL(blob);

      // Create a download link
      const a = document.createElement("a");
      a.href = url;
      a.download = `${row.name}-certificate.pdf`; // Set file name
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);

      // Clean up the URL
      URL.revokeObjectURL(url);

      onToast("Downaload Certificate Successfull!!")
      //window.location.reload();
    } else {
      onToast("Something went wrong!!")
    }
  }

  const ExpandedComponent = ({ data }) =>
    <div className="list-container">
      <div className="footer-brand">
        <li className="footer-item logo">
          <span className="span">Name</span> : <span>{data.name}</span>
        </li>
        <li className="footer-item logo">
          <span className="span">Gender</span> : <span>{data.gender}</span>
        </li>
        <li className="footer-item logo">
          <span className="span">College</span> : <span>{data.college.name}</span>
        </li>
        <li className="footer-item logo">
          <span className="span">Course</span> : <span>{data.course}</span>
        </li>
        <li className="footer-item logo">
          <span className="span">Email</span> : <span>{data.email}</span>
        </li>
        <li className="footer-item logo">
          <span className="span">Mobile</span> : <span>{data.mobile}</span>
        </li>        
        <li className="footer-item logo">
          <span className="span">Academic Year</span> : <span>{data.academicYear}</span>
        </li>
      </div>
    </div>
    ;


  const columns = [
    {
      name: 'ID',
      selector: row => row.id,
      sortable: true,
    },
    {
      name: 'NAME',
      selector: row => row.name,
      sortable: true,
    },
    {
      name: 'GENDER',
      selector: row => row.gender,
      sortable: true,
    },
    {
      name: 'COLLEGE',
      selector: row => row.college.name,
      sortable: true,
    },
    {
      name: 'COURSE',
      selector: row => row.course,
      sortable: true,
    },
    {
      name: 'EMAIL',
      selector: row => row.email,
      sortable: true,
    },
    {
      name: 'MOBILE',
      selector: row => row.mobile,
      sortable: true,
      cell: row => <input value={row.mobile} onChange={e => handleInputChange(e, row.id, 'mobile')} />,
    },
    {
      name: 'ACEDAMIC YEAR',
      selector: row => row.academicYear,
      sortable: true,
    },
    {
      name: 'GENERATE',
      cell: row => <button onClick={() => handleGenerateRow(row)}>Generate</button>,
      ignoreRowClick: true,
      allowOverflow: true,
      button: true,
    },
    {
      name: 'DOWNLOAD',
      cell: row => <button onClick={() => handleDownloadRow(row)}>Download</button>,
      ignoreRowClick: true,
      allowOverflow: true,
      button: true,
    },
    {
      name: 'EDIT',
      cell: row => <button onClick={() => handleEditRow(row)}>Edit</button>,
      ignoreRowClick: true,
      allowOverflow: true,
      button: true,
    },
    {
      name: 'DELETE',
      cell: row => <button onClick={() => handleDeleteRow(row)}>Delete</button>,
      ignoreRowClick: true,
      allowOverflow: true,
      button: true,
    },
  ];

  const [filterText, setFilterText] = React.useState("");
  const [resetPaginationToggle, setResetPaginationToggle] = React.useState(
    false
  );

  const filteredItems = data.filter(
    item =>
      JSON.stringify(item)
        .toLowerCase()
        .indexOf(filterText.toLowerCase()) !== -1
  );

  const subHeaderComponent = React.useMemo(() => {
    const handleClear = () => {
      if (filterText) {
        setResetPaginationToggle(!resetPaginationToggle);
        setFilterText("");
      }
    };

    return (
      <FilterData
        onFilter={e => setFilterText(e.target.value)}
        onClear={handleClear}
        filterText={filterText}
      />
    );
  }, [filterText, resetPaginationToggle]);


  const paginationComponentOptions = {
    rowsPerPageText: 'Filas por página',
    rangeSeparatorText: 'de',
    selectAllRowsItem: true,
    selectAllRowsItemText: 'Todos',
  };



  return (
    <>
      <Header />
      <div className="container-fluid mt-5 mb-5">
        <div className="row justify-content-center">
          <div className="col-md-11">
            <div className="card">
              <div className="card-body p-0">
                <div className="table-filter-info"></div>
                <DataTable
                  title="STUDENTS"
                  columns={columns}
                  data={filteredItems}
                  defaultSortField="id"
                  striped
                  pagination
                  subHeader
                  subHeaderComponent={subHeaderComponent}
                  highlightOnHover
                  paginationComponentOptions={paginationComponentOptions}
                  expandableRows
                  expandableRowsComponent={ExpandedComponent}
                />
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};