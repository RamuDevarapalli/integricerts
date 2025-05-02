import React, { useEffect, useState } from "react";
import { getData, postData, deleteData } from '../../Helper/Axios';
import { ListStudents } from '../../Component/Education/ListStudents'

export const Students = () => {
  useEffect(() => { window.scrollTo(0, 0) }, []);

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(9);

  const fatchData = async () => {
    const response = await getData({
      "url": "student/all"
    });
    const data = await response.json()
    setData(data);
  };

  useEffect(() => {
    fatchData();
  }, [loading]);

  return (
    <>
    <ListStudents data={data} setLoading={setLoading} />
    </>
  )
}
