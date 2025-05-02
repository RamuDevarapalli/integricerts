import React from 'react'

export const Footer = () => {
  return (
    <>
      <footer id='contact' className="footer">
        <div className="footer-top">
          <div className="container">
            <div className="footer-brand">
              <a href="/" className="logo">
                Integri<span className="span">Cert</span>
              </a>
              <p className="footer-text">
              Blockchain verification is a secure way to confirm the authenticity of credentials using blockchain technology.
              </p>
              <ul className="social-list">
                <li>
                  <a href="#" className="social-link">
                    <ion-icon name="logo-facebook" />
                  </a>
                </li>
                <li>
                  <a href="#" className="social-link">
                    <ion-icon name="logo-twitter" />
                  </a>
                </li>
                <li>
                  <a href="#" className="social-link">
                    <ion-icon name="logo-skype" />
                  </a>
                </li>
                <li>
                  <a href="#" className="social-link">
                    <ion-icon name="logo-linkedin" />
                  </a>
                </li>
              </ul>
            </div>
            <ul className="footer-list">
              <li>
                <p className="footer-list-title">Company</p>
              </li>
              <li>
                <a href="/about" className="footer-link">
                  About Us
                </a>
              </li>
              <li>
                <a href="/contact" className="footer-link">
                  Contact Us
                </a>
              </li>
            </ul>
            <ul className="footer-list">
              <li>
                <p className="footer-list-title">Contact</p>
              </li>
              <li className="footer-item">
                <div className="contact-icon">
                  <ion-icon name="location-outline" />
                </div>
                <address className="contact-link">
                  US
                </address>
              </li>
              <li className="footer-item">
                <div className="contact-icon">
                  <ion-icon name="call-outline" />
                </div>
                <a href="tel:+1 (123) 456-7890" className="contact-link">
                +1 (123) 456-7890
                </a>
              </li>
              <li className="footer-item">
                <div className="contact-icon">
                  <ion-icon name="mail-outline" />
                </div>
                <a href="mailto:info@integricert.com" className="contact-link">
                info@integricert.com
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div className="footer-bottom">
          <div className="container">
            <p className="copyright">
              © 2025{" "}
              <a href="#" className="copyright-link">
                integricert system
              </a>
              . All Rights Reserved.
            </p>
            <ul className="footer-bottom-list">
              <li>
                <a href="#" className="footer-bottom-link">
                  Term and Service
                </a>
              </li>
              <li>
                <a href="#" className="footer-bottom-link">
                  Privacy Policy
                </a>
              </li>
            </ul>
          </div>
        </div>
      </footer>
    </>
  )
}
