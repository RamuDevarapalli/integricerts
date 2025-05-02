import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';

const Display = (props) => {
  const [state, setState] = useState({ name: '', gender: '', age: '', lookingfor: '' });

  useEffect(() => {
    const { steps } = props;
    const { name, gender, age, lookingfor } = steps;
    setState({ name, gender, age, lookingfor });
  }, [props])

  const { name, gender, age, lookingfor } = state;

  const baseUrl = (lookingfor) => {
    var url = ""
    if (lookingfor === 'View Appointment') {
      url = '/appointments';
    } else if (lookingfor === 'Book Appointment') {
      url = '/bookappointment';
    } else if (lookingfor === 'Orders') {
      url = '/history';
    } else if (lookingfor === 'Validate') {
      url = '/validate';
    } else if (lookingfor === 'Cart') {
      url = '/cart';
    } else if (lookingfor === 'Wellness Tips') {
      url = '/wellnesstips';
    } else if (lookingfor === 'Diet Plans') {
      url = '/dietplans';
    } else if (lookingfor === 'Nutrion Data') {
      url = '/nutritiondata';
    }
    return url;
  }

  return (
    <div style={{ width: '100%' }}>
      <h3>Summary</h3>
      <table>
        <tbody>
          <tr>
            <td className='tdclass1'>Name</td>
            <td>:</td>
            <td className='tdclass2'>{name.value}</td>
          </tr>
          <tr>
            <td className='tdclass1'>Gender</td>
            <td>:</td>
            <td className='tdclass2'>{gender.value}</td>
          </tr>
          <tr>
            <td className='tdclass1'>Age</td>
            <td>:</td>
            <td className='tdclass2'>{age.value}</td>
          </tr>
          <tr>
            <td className='tdclass1'>Looking for</td>
            <td>:</td>
            <td className='tdclass2'>
              <a href={baseUrl(lookingfor.value)} className="navbar-link" target="_blank">{lookingfor.value}</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}

Display.propTypes = {
  steps: PropTypes.object,
};

Display.defaultProps = {
  steps: undefined,
};

export default Display;