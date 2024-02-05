# Hospital Management System (HMS) Project

## Description
This Java-based Hospital Management System is designed to streamline various tasks related to patient management, doctor information, and appointment scheduling within a hospital setting.

## Functionalities
1. **Add Patient**: Allows users to add new patient records to the system.
2. **View Patients**: Displays a list of all registered patients.
3. **View Doctors**: Provides information about the doctors available in the system.
4. **Book Appointment**: Enables users to schedule appointments with doctors.
5. **View All Appointments**: Shows a list of all scheduled appointments.
6. **View Appointments by Patient**: Allows users to view appointments associated with a specific patient.

## Technologies Used
- Java
- MySQL

## How to Run the Project
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/HospitalManagementSystem.git
Database Setup:

Install MySQL on your system.
Create a new database named hospital_management.
Import the SQL script (hospital_management.sql) provided in the repository to create the necessary tables.
Configure Database Connection:

Open DatabaseConnection.java file.
Update the DB_URL, DB_USER, and DB_PASSWORD variables with your MySQL database connection details.
Compile and Run

Database Connection
In the DatabaseConnection.java file, the project is connected to the MySQL database using JDBC (Java Database Connectivity). The connection details are provided in the following variables:

java
private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_management";
private static final String DB_USER = "your_mysql_username";
private static final String DB_PASSWORD = "your_mysql_password";
Replace your_mysql_username and your_mysql_password with your MySQL username and password.

License
This project is licensed under the MIT License - see the LICENSE file for details.



