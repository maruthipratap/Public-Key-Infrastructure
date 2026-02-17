# Public Key Infrastructure (PKI) – Certificate Authority System

This project implements a working **Public Key Infrastructure (PKI)** model that simulates how HTTPS websites establish and revoke trust using digital certificates.

The system acts as a mini Certificate Authority (CA) that issues, validates, and revokes digital certificates using asymmetric cryptography.

---

## 🔐 Features

- RSA-2048 public and private key generation
- Digital certificate issuance by Certificate Authority
- Certificate viewing (owner, issuer, validity period)
- Certificate verification using CA public key
- Tamper detection using digital signatures
- Certificate Revocation List (CRL) support
- Web interface deployed on Apache Tomcat
- Full certificate lifecycle demonstration

---

## 🧠 Concept

In open networks like the Internet, users cannot trust each other's identity.  
PKI solves this by introducing a trusted authority that signs public keys and validates identities.

This project demonstrates:

1. Creating trust (certificate issuance)
2. Using trust (verification)
3. Breaking trust (key compromise)
4. Removing trust (revocation)

This is the same mechanism used by HTTPS browsers to validate secure websites.

---

## 🏗 System Architecture

Entities involved:

- **Certificate Authority (CA)** – Issues and signs certificates
- **Certificate Holder (User)** – Owns a public/private key pair
- **Verifier** – Validates certificate authenticity

Workflow:

1. User key pair is generated
2. CA signs the user public key
3. Certificate is issued
4. Verifier checks certificate signature
5. If compromised → CA revokes certificate using CRL

---

## ⚙️ Technologies Used

- Java
- Java Cryptography Architecture (JCA)
- RSA-2048
- SHA-256
- Java Servlets
- Apache Tomcat 9
- HTML

---

## 📁 Project Structure
```
Public-Key-Infrastructure/
│
├── src/ # Java source files
│ ├── CertificateAuthority.java
│ ├── DigitalCertificate.java
│ ├── CRLManager.java
│ ├── IssueCertificateServlet.java
│ ├── VerifyCertificateServlet.java
│ ├── ViewCertificateServlet.java
│ └── RevokeCertificateServlet.java
│
├── data/
│ ├── keys/ # CA public/private keys
│ └── certificates/ # Issued certificates
│
├── deploy.bat # One-click deployment script
└── README.md
```

---

## ▶️ How to Run

### 1. Install
- Install Java (JDK 8+)
- Install Apache Tomcat 9

### 2. Deploy

From project folder:

.\deploy

This will:
- Compile the project
- Copy classes to Tomcat
- Clear cache
- Restart server

### 3. Open in Browser

http://localhost:8080/pki

---

## 🧪 Demonstration Steps

1. Click **Issue Certificate**
2. Click **View Certificate**
3. Click **Verify Certificate** → VALID
4. Click **Revoke Certificate**
5. Click **Verify Certificate again** → REVOKED

This simulates how browsers reject compromised HTTPS certificates.

---

## 🔎 Security Concepts Demonstrated

- Authentication
- Data Integrity
- Non-Repudiation
- Trust Management

---

## 📌 Real-World Applications

- HTTPS (SSL/TLS) websites
- Online banking
- Secure email (S/MIME)
- VPN authentication
- Software code signing
- Digital document signing

---

## 👨‍💻 Author

Maruthi Pratap  
B.Tech – Computer Science / Cybersecurity

---

## 📜 License
This project is developed for academic and educational purposes.
