import React, { useEffect, useState } from "react";
import { getData, postData, deleteData } from '../../Helper/Axios';
import { ListEmployees } from '../../Component/Bussiness/ListEmployees'

export const Employees = () => {
  useEffect(() => { window.scrollTo(0, 0) }, []);

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(9);

  const fatchData = async () => {
    const response = await getData({
      "url": "employee/all"
    });
    const data = await response.json()
    setData(data);
  };

  useEffect(() => {
    fatchData();
  }, [loading]);

  return (
    <>
    <ListEmployees data={data} setLoading={setLoading} />
    </>
  )
}
