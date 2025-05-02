import React, { useEffect } from 'react'
import { Header } from '../Component/Header'
import { Footer } from '../Component/Footer'

export const Error = () => {
  useEffect(() => { window.scrollTo(0, 0) }, []);

  return (
    <>
      <Header />
      <div className="shopping-cart">
        <div className="px-4 px-lg-0">
          <div className="pb-5">
            <div className="container">
              Server is down .. please try later
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  )
}
