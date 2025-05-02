import React, { useEffect, useState } from "react";
import { getData, postData, deleteData } from '../../Helper/Axios';
import { ListUsers } from "../../Component/Admin/ListUsers";

export const Users = () => {
  useEffect(() => { window.scrollTo(0, 0) }, []);

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(9);

  const fatchData = async () => {
    const response = await getData({
      "url": "user/all"
    });
    const data = await response.json()
    setData(data);
  };

  useEffect(() => {
    fatchData();
  }, [loading]);

  return (
    <>
      <ListUsers data={data} setLoading={setLoading} />
    </>
  )
}
