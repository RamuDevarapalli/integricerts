import React, { useEffect, useState } from "react";
import { getData, postData, deleteData } from '../../Helper/Axios';
import { ListCompanies } from '../../Component/Bussiness/ListCompanies'

export const Companies = () => {
  useEffect(() => { window.scrollTo(0, 0) }, []);

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(9);

  const fatchData = async () => {
    const response = await getData({
      "url": "company/all"
    });
    const data = await response.json()
    setData(data);
  };

  useEffect(() => {
    fatchData();
  }, [loading]);

  return (
    <>
    <ListCompanies data={data} setLoading={setLoading} />
    </>
  )
}
