import React, { useEffect, useState } from "react";
import { getData, postData, deleteData } from './Axios';

export function getCart() {
  const response = getData({
    "url": "cart/user"
  });
  const data = response.json();
  return data;
}
