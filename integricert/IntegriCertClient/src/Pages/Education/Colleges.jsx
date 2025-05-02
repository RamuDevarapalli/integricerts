import React, { useEffect, useState } from "react";
import { getData, postData, deleteData } from '../../Helper/Axios';
import { ListColleges } from '../../Component/Education/ListColleges'

export const Colleges = () => {
  useEffect(() => { window.scrollTo(0, 0) }, []);

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(9);

  const fatchData = async () => {
    const response = await getData({
      "url": "college/all"
    });
    const data = await response.json()
    setData(data);
  };

  useEffect(() => {
    fatchData();
  }, [loading]);

  return (
    <>
    <ListColleges data={data} setLoading={setLoading} />
    </>
  )
}
