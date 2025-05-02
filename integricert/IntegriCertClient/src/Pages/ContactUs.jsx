import React, { useEffect, useState } from "react";
import { Footer } from "../Component/Footer";
import { Header } from "../Component/Header";

export const ContactUs = () => {
  return (
    <>
      <Header />
      <div className="shopping-cart">
        <div className="px-4 px-lg-0">
          <div className="pb-5">
            <div className="container">
              <p className="section-subtitle">CONTACT US</p>
              <div>
                <p>
                  We value your feedback and inquiries. Please feel free to reach out to us using the contact information below.
                </p>
                <h2>Contact Information</h2>
                <ul>
                  <li>
                    <strong>Email:</strong> <a href="mailto:info@integricert.com">info@integricert.com</a>
                  </li>
                  <li>
                    <strong>Phone:</strong> +1 (123) 456-7890
                  </li>
                  <li>
                    <strong>Address:</strong> *****, ****, State, Country
                  </li>
                </ul>
                <h2>Send us a Message</h2>
                <form>
                  <div>
                    <label htmlFor="name">Name:</label>
                    <input type="text" id="name" name="name" required />
                  </div>
                  <div>
                    <label htmlFor="email">Email:</label>
                    <input type="email" id="email" name="email" required />
                  </div>
                  <div>
                    <label htmlFor="message">Message:</label>
                    <textarea id="message" name="message" rows="5" required></textarea>
                  </div>
                  <button type="submit">Send Message</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};
