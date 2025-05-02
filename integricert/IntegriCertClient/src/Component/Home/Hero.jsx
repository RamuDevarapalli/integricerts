import React from 'react'

export const Hero = () => {
  return (
    <>
     <section className="hero">
        <div className="container">
          <div className="hero-content">
            <p className="hero-title">Blockchain Verification System</p>
            <h2 className="hero-subtitle">
            Blockchain verification is a secure way to confirm the authenticity of credentials using blockchain technology. 
            </h2>
            <p className="hero-text">
            Blockchain verification is like a public, secure stamp of approval for digital information.
            </p>
            <a href="/certificate/validate" className="btn btn-primary">
              <span className="span">Verify Now</span>
              <ion-icon name="chevron-forward" aria-hidden="true" />
            </a>
          </div>
          <figure className="hero-banner">
            <img
              src="./img/blockchain-verification.png"
              width={603}
              height={634}
              loading="lazy"
              alt="Blockchain Verification System"
              className="w-100"
            />
          </figure>
        </div>
      </section>
    </>
  )
}
