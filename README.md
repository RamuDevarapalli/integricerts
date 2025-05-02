# IntegriCert – Certificate Verification using Blockchain

## 🚀 Overview
IntegriCert is a secure, decentralized certificate verification system built using **Ethereum blockchain**. It allows educational institutions or certifying bodies to issue tamper-proof certificates, and lets anyone verify their authenticity in real-time using a unique **QR code** or **barcode**.

This project was developed as part of the Blockchain course (CPSC 597) at California State University, Fullerton.

## 📌 Features
- 🎓 Issuance of digital certificates via Smart Contracts
- 🔐 Immutable and transparent verification on Ethereum
- 📸 QR/Barcode generation and scanning for instant validation
- 🌐 Web-based user interface built with React
- 📡 Backend API with Node.js & Express
- 🧾 MongoDB used for certificate metadata storage

## ⚙️ Tech Stack
- **Frontend:** React.js, Tailwind CSS
- **Backend:** Node.js, Express.js
- **Blockchain:** Solidity (Ethereum Smart Contracts), Hardhat
- **Database:** MongoDB (Mongoose)
- **Other Tools:** Web3.js, IPFS (optional), QR code generator library

## 🔗 Smart Contract
The smart contract is written in Solidity and deployed to a testnet (e.g., Goerli or Sepolia).

### Sample functions:
- `issueCertificate(address recipient, string memory certHash)`
- `verifyCertificate(string memory certHash)`

> View Smart Contract Code: [contracts/Certificate.sol](./contracts/Certificate.sol)



## 🧪 Installation & Setup

### Prerequisites
- Node.js
- MongoDB
- MetaMask wallet (configured to testnet)
- Hardhat

### Steps

```bash
git clone https://github.com/yourusername/IntegriCert.git
cd IntegriCert

# Install backend dependencies
cd backend
npm install

# Install frontend dependencies
cd ../frontend
npm install

# Run MongoDB and backend
cd ../backend
npm start

# Run frontend
cd ../frontend
npm start
