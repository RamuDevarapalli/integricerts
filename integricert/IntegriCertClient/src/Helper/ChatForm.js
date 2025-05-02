import React, { Component } from 'react';
import ChatBot from 'react-simple-chatbot';
import Display from './Display';

class ChatForm extends Component {
  render() {
    return (
      <ChatBot
        headerTitle="Speech Recognition"
        recognitionEnable={true}
        steps={[
          {
            id: '1',
            message: 'What is your name?',
            trigger: 'name',
          },
          {
            id: 'name',
            user: true,
            trigger: '2',
          },
          {
            id: '2',
            message: 'Hi {previousValue}! What is your gender?',
            trigger: 'gender',
          },
          {
            id: 'gender',
            options: [
              { value: 'male', label: 'Male', trigger: '3' },
              { value: 'female', label: 'Female', trigger: '3' },
            ],
          },
          {
            id: '3',
            message: 'How old are you?',
            trigger: 'age',
          },
          {
            id: 'age',
            user: true,
            trigger: '4',
            validator: (value) => {
              if (isNaN(value)) {
                return 'value must be a number';
              } else if (value < 0) {
                return 'value must be positive';
              } else if (value > 120) {
                return `${value}? Come on!`;
              }
              return true;
            },
          },
          {
            id: '4',
            message: 'What are you looking for?',
            trigger: 'lookingfor',
          },
          {
            id: 'lookingfor',
            options: [
              { value: 'Nutrion Data', label: 'Nutrion Data', trigger: '5' },
              { value: 'Diet Plans', label: 'Diet Plans', trigger: '5' },
              { value: 'Wellness Tips', label: 'Wellness Tips', trigger: '5' },
              { value: 'Cart', label: 'Cart', trigger: '5' },
              { value: 'Shop', label: 'Shop', trigger: '5' },
              { value: 'Orders', label: 'Orders', trigger: '5' },
              { value: 'Book Appointment', label: 'Book Appointment', trigger: '5' },
              { value: 'View Appointment', label: 'View Appointment', trigger: '5' },
            ],
          },
          {
            id: '5',
            message: 'Great! Check out your summary',
            trigger: 'display',
          },
          {
            id: 'display',
            component: <Display />,
            asMessage: true,
            trigger: 'update',
          },
          {
            id: '6',
            message: 'Great! Check out your summary',
            trigger: 'review',
          },
          {
            id: 'review',
            component: <Display />,
            asMessage: true,
            trigger: 'update',
          },
          {
            id: 'update',
            message: 'Would you like to update some field?',
            trigger: 'update-question',
          },
          {
            id: 'update-question',
            options: [
              { value: 'yes', label: 'Yes', trigger: 'update-yes' },
              { value: 'no', label: 'No', trigger: 'end-message' },
            ],
          },
          {
            id: 'update-yes',
            message: 'What field would you like to update?',
            trigger: 'update-fields',
          },
          {
            id: 'update-fields',
            options: [
              { value: 'name', label: 'Name', trigger: 'update-name' },
              { value: 'gender', label: 'Gender', trigger: 'update-gender' },
              { value: 'age', label: 'Age', trigger: 'update-age' },
              { value: 'lookingfor', label: 'Lookig For', trigger: 'update-lookingfor' },
            ],
          },
          {
            id: 'update-name',
            update: 'name',
            trigger: '5',
          },
          {
            id: 'update-gender',
            update: 'gender',
            trigger: '5',
          },
          {
            id: 'update-age',
            update: 'age',
            trigger: '5',
          },
          {
            id: 'update-lookingfor',
            update: 'lookingfor',
            trigger: '5',
          },
          {
            id: 'end-message',
            message: 'Thanks! Your data was submitted successfully!',
            end: true,
          },
        ]}
      />
    );
  }
}

export default ChatForm;