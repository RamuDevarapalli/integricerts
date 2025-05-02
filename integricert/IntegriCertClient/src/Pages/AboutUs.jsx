import React, { useEffect, useState } from "react";
import { Footer } from "../Component/Footer";
import { Header } from "../Component/Header";

export const AboutUs = () => {
  return (
    <>
      <Header />
      <div className="shopping-cart">
        <div className="px-4 px-lg-0">
          <div className="pb-5">
            <div className="container">
              <p className="section-subtitle">About Our IntegriCert Application</p>
              <div>
                <p>
                We are dedicated to providing cutting-edge solutions that ensure trust, transparency, and security in academic and professional credential verification.
                </p>
                <h2>Our Mission</h2>
                <p>
                Our mission is to revolutionize credential verification using blockchain technology, making academic and corporate certifications more secure, tamper-proof, and easily verifiable.
                </p>
                <h2>What We Offer</h2>
                <p>
                Our blockchain-based verification platform provides a robust suite of features for educational institutions, companies, and individuals, including:
                </p>
                <ul>
                  <li>Immutable Credential Storage – Securely store and verify degrees, certifications, and work experience on the blockchain.</li>
                  <li>Instant Verification – Organizations can authenticate academic and corporate credentials in real time.</li>
                  <li>Fraud Prevention – Eliminate fake certificates and misrepresentation with blockchain-backed proof.</li>
                  <li>Decentralized Access – Individuals have lifetime access to their verified records without intermediaries.</li>
                  <li>Secure Collaboration – Universities and companies can seamlessly share verified records with authorized entities.</li>
                </ul>
                <h2>Our Team</h2>
                <p>
                  Meet the passionate individuals behind our IntegriCert application:
                </p>
                <ul>
                  <li>Admin - Developer</li>
                  <li>Admin - Developer</li>
                </ul>
                <h2>Contact Us</h2>
                <p>
                  Have questions or feedback? We'd love to hear from you! Reach out to us at <a href="mailto:info@integricert.com">info@integricert.com</a>.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};
