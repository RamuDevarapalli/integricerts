import React, { useState, useEffect } from 'react';

const SearchFilter = ({ data }) => {
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredData, setFilteredData] = useState(data);

  useEffect(() => {
    // Function to filter data based on search term
    const filterData = () => {
      const filtered = data.filter(item =>
        item.toLowerCase().includes(searchTerm.toLowerCase())
      );
      setFilteredData(filtered);
    };

    // Call the filterData function after a delay to debounce the search
    const debounceTimer = setTimeout(filterData, 300);

    // Clean up the timer on component unmount or when the search term changes
    return () => clearTimeout(debounceTimer);
  }, [searchTerm, data]);

  const handleSearchChange = event => {
    setSearchTerm(event.target.value);
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Search..."
        value={searchTerm}
        onChange={handleSearchChange}
      />

      <ul>
        {filteredData.map((item, index) => (
          <li key={index}>{item}</li>
        ))}
      </ul>
    </div>
  );
};

export default SearchFilter;
