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

export const ListCertificates = ({ data, setLoading }) => {
  const [isSignedIn, setIsSignedIn] = useState(
    localStorage.getItem("token") || false
  );
  const [islogin, setislogin] = useState(localStorage.getItem("token"));
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [count, setCount] = useState();
  const navigate = useNavigate();

  // Enum for CertificateType
  const CertificateType = {
    STUDENT: 'STUDENT',
    EMPLOYEE: 'EMPLOYEE'
  };

  const [pdata, setData] = useState(data);
  const [updatedRows, setUpdatedRows] = useState([]);

  const handleInputChange = (e, id, key) => {
    const newData = [...data];
    const index = newData.findIndex(item => item.id === id);
    newData[index][key] = e.target.value;
    setData(newData);

    const updatedRow = { id, [key]: e.target.value };
    if (!updatedRows.some(row => row.id === id)) {
      setUpdatedRows([...updatedRows, updatedRow]);
    } else {
      const updatedRowsCopy = [...updatedRows];
      const updatedRowIndex = updatedRowsCopy.findIndex(row => row.id === id);
      updatedRowsCopy[updatedRowIndex][key] = e.target.value;
      setUpdatedRows(updatedRowsCopy);
    }
  };

  const onToast = (s) => {
    if ('Delete Certificate Successfull!!' === s || 'Update Certificate Successfull!!' === s) {
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
      "url": `certificate/delete/${row.id}`
    });
    const data = await response.json();

    if (response.status === 200) {
      onToast("Delete Certificate Successfull!!")
      window.location.reload();
    } else {
      onToast("Something went wrong!!")
    }
  }

  const handleEditRow = async (row) => {
    const response = await postData({
      "url": "certificate/update",
      "data": row,
      "isJson": true
    });
    const data = await response.json();

    if (response.status === 200) {
      onToast("Update Certificate Successfull!!")
      window.location.reload();
    } else {
      onToast("Something went wrong!!")
    }
  }

  const handleDownloadRow = async (row) => {
    try {
       // Decode Base64 string
      const byteCharacters = atob(row.data);
      const byteNumbers = Array.from(byteCharacters, (char) => char.charCodeAt(0));
      const byteArray = new Uint8Array(byteNumbers);
      const blob = new Blob([byteArray], { type: "application/pdf" });
  
      // Create a temporary URL for the Blob
      const url = URL.createObjectURL(blob);
  
      // Create a download link
      const a = document.createElement("a");
      a.href = url;
      a.download = data.certificateType === "STUDENT" ? `${data.student.name}-certificate.pdf`
        : data.certificateType === "EMPLOYEE" ? `${data.employee.name}-certificate.pdf`
        : "General Certificate";
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
  
      // Cleanup the temporary URL
      URL.revokeObjectURL(url);
  
      onToast("Download Certificate Successful!");
    } catch (error) {
      console.error("Error downloading certificate:", error);
      onToast("Something went wrong!");
    }
  }

  const ExpandedComponent = ({ data }) =>
    <div className="list-container">
      <div className="footer-brand">
        <li className="footer-item logo">
          <span className="span">Name</span> : <span>
            {data.certificateType === "STUDENT" ? data.student.name
              : data.certificateType === "EMPLOYEE" ? data.employee.name
              : "General Certificate"}</span>
        </li>
        <li className="footer-item logo">
          <span className="span">Certificate</span> : <span>{data.value}</span>
        </li>
        <li className="footer-item logo">
          <span className="span">Certificate Type</span> : <span>{data.certificateType}</span>
        </li>
        <li className="footer-item logo">
          <span className="span">Created Time</span> : <span>{data.createdAt}</span>
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
      name: 'CERTIFICATE',
      selector: row => row.value,
      sortable: true,
    },    
    {
      name: 'PDF',
      cell: row => <button onClick={() => handleDownloadRow(row)}>Download</button>,
      ignoreRowClick: true,
      allowOverflow: true,
      button: true,
    },
    {
      name: 'CERTIFICATE TYPE',
      selector: row => row.certificateType,
      sortable: true,
    },
    {
      name: 'CREATED TIME',
      selector: row => row.createdAt,
      sortable: true,     
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
                  title="CERTIFICATES"
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