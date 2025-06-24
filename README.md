# Car Rental System

A Java-based desktop application for managing a car rental service. This project demonstrates core **Object-Oriented Programming (OOP)** principles and integrates with a **PostgreSQL** database to manage customers, cars, and rental transactions.

## 🔧 Features

### 👤 User Features
- View available cars
- Book a car for a specified number of hours
- Return rented cars with status tracking (e.g., Delayed, Completed)
- View personal rental history
- Update personal profile
- Change password

### 🛠️ Admin Features
- View all registered users
- View all rental records
- Manage car inventory (add, update, delete cars)
- Manage user accounts

## 🧱 Technologies Used

- **Java (Core)**: console-based interface using OOP concepts like inheritance, polymorphism, encapsulation, and abstraction.
- **PostgreSQL**: Used as the backend database to store:
    - User details
    - Car details
    - Rental transactions
- **JDBC**: Java Database Connectivity used to interact with the PostgreSQL database.
- **{GUI-(Soon....! under process)}***
## 🏗️ Project Structure
- **Controller/ # Contains all controller classes for user/admin operations**
- **Model/ # Contains model classes for User, Car, Rent, etc.**
- **Main.java # Entry point of the application**


## 📂 Database Schema Overview

**Tables**:
- `carRental` — Stores user information (clients and admins)
- `cars` — Stores available car details
- `rents` — Stores all rental transactions

> Use double quotes for column names in PostgreSQL if mixed-case was used while creating them (e.g., `"FirstName"`, `"Email"`).

## ⚙️ How to Run

1. Clone this repository
2. Set up PostgreSQL and create the required tables
3. Update database connection credentials in the `Database` class
4. Compile and run `Main.java`

## ✅ Highlights

- Input validation and exception handling for robustness
- Modular and scalable structure for easy maintenance
- Designed for clarity and separation of concerns using MVC principles (without GUI)

## 📌 Future Improvements

- Add a GUI using JavaFX or Swing
- Add reporting/analytics module
- Export rental history to PDF or Excel

---

> Developed as a demonstration of OOP design and database integration using Java and PostgreSQL.
