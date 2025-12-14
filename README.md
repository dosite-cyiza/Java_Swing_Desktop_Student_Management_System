# Student Management System - Java Swing CRUD Application

---

##  Group Members

| Full Name | Registration Number |
|-----------|-------------------|
| [Dosite Iradukunda Cyiza] | [24RP07784] |
| [Happiness Nyampinga] | [24rp05477] |

---

## Project Title

**Student Management System - Desktop CRUD Application using Java Swing, JDBC, and MySQL**

---

##  Short Description

The Student Management System is a desktop application built using Java Swing that allows users to perform complete CRUD (Create, Read, Update, Delete) operations on student records. The application connects to a MySQL database using JDBC and implements data validation using Regular Expressions (RegEx). It demonstrates Object-Oriented Programming principles including Encapsulation, Abstraction, and Interface implementation with a clean separation of concerns through a multi-layered package structure.

**Technologies Used:**
- Java (JDK 8+)
- Java Swing (GUI)
- JDBC (Database Connectivity)
- MySQL (Database)
- RegEx (Data Validation)

---

##  Features

### Core Functionality
- ✅ **Add Student** - Insert new student records with complete validation
- ✅ **View Students** - Display all student records in an interactive JTable
- ✅ **Update Student** - Modify existing student information
- ✅ **Delete Student** - Remove student records with confirmation dialog
- ✅ **Clear Form** - Reset all input fields
- ✅ **Load/Reload Data** - Fetch and refresh data from database

### Technical Features
- ✅ **RegEx Validation** - Real-time input validation for:
  - Name (letters only, 2-50 characters)
  - Email (standard email format: user@domain.com)
  - Phone (10-digit format: 555-123-4567)
  - GPA (0.0 to 4.0 range)
  - Enrollment Year (valid year range)
- ✅ **OOP Principles** - Encapsulation, Abstraction, Interface implementation
- ✅ **JDBC Integration** - PreparedStatement for SQL injection prevention
- ✅ **Exception Handling** - Comprehensive error handling with user-friendly messages
- ✅ **Interactive UI** - Click table rows to auto-populate form for editing
- ✅ **Color-Coded Buttons** - Visual distinction for different operations
- ✅ **Swing Components** - JFrame, JPanel, JLabel, JTextField, JButton, JComboBox, JTable, JScrollPane

### Package Structure
```
src/
├── model/          - Student entity class (Encapsulation)
├── db/             - Database connection management
├── service/        - Business logic (Interface & Implementation)
├── util/           - RegEx validation utilities
└── ui/             - Swing GUI components
```

---

##  Instructions for Running the Application

### Prerequisites
1. **Java Development Kit (JDK)** - Version 8 or higher
2. **MySQL Server** - Installed via XAMPP or standalone
3. **MySQL JDBC Driver** - mysql-connector-j-9.1.0.jar or later
4. **IDE** - Eclipse, IntelliJ IDEA, or NetBeans

---

### Step 1: Setup Database

#### 1.1 Start MySQL Service
- **If using XAMPP:**
  - Open XAMPP Control Panel
  - Click "Start" next to MySQL
  - Wait for green "Running" status

- **If using standalone MySQL:**
  - Ensure MySQL service is running
  - Default port: 3306

#### 1.2 Create Database and Table
1. Open phpMyAdmin: `http://localhost/phpmyadmin`
2. Click "SQL" tab
3. Execute the following SQL script:

```sql
-- Create database
CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

-- Create students table
CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL,
    department VARCHAR(50) NOT NULL,
    enrollment_year INT NOT NULL,
    gpa DECIMAL(3,2) CHECK (gpa >= 0.0 AND gpa <= 4.0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data for testing
INSERT INTO students (first_name, last_name, email, phone, department, enrollment_year, gpa) 
VALUES 
    ('John', 'Doe', 'john.doe@university.edu', '555-123-4567', 'Computer Science', 2022, 3.75),
    ('Jane', 'Smith', 'jane.smith@university.edu', '555-234-5678', 'Engineering', 2021, 3.90),
    ('Michael', 'Johnson', 'michael.j@university.edu', '555-345-6789', 'Business', 2023, 3.45),
    ('Emily', 'Brown', 'emily.brown@university.edu', '555-456-7890', 'Mathematics', 2022, 3.88),
    ('David', 'Wilson', 'david.wilson@university.edu', '555-567-8901', 'Physics', 2021, 3.62);
```

4. Verify: Check that 5 sample records appear in the `students` table

---

### Step 2: Setup Java Project

#### 2.1 Import Project in IDE (Eclipse Example)
1. Open Eclipse
2. **File → Import → Existing Projects into Workspace**
3. Select the project folder
4. Click "Finish"

#### 2.2 Add MySQL JDBC Driver
1. Right-click on project name
2. Select **Build Path → Configure Build Path**
3. Click **Libraries** tab
4. Click **Add External JARs...**
5. Navigate to and select `mysql-connector-j-9.1.0.jar`
6. Click **Apply and Close**
7. Verify: JAR file appears under "Referenced Libraries"

#### 2.3 Configure Database Connection
Open `db/DatabaseConnection.java` and verify settings:

```java
private static final String URL = "jdbc:mysql://localhost:3306/student_management";
private static final String USERNAME = "root";
private static final String PASSWORD = ""; // Empty for XAMPP; update if you set a password
```

**Note:** If using standalone MySQL with a password, update the `PASSWORD` field accordingly.

---

### Step 3: Run the Application

1. **Ensure MySQL is running** (check XAMPP Control Panel or services)

2. **In Eclipse:**
   - Navigate to `ui` package
   - Right-click on `StudentManagementGUI.java`
   - Select **Run As → Java Application**

3. **Application window opens** - You should see:
   - Form fields at the top
   - Student records table in the middle
   - Action buttons at the bottom
   - 5 sample student records displayed

---

### Step 4: Using the Application

#### Adding a New Student
1. Fill in all form fields following validation rules:
   - **First Name:** Letters only (e.g., "John")
   - **Last Name:** Letters only (e.g., "Smith")
   - **Email:** Valid format (e.g., "student@university.edu")
   - **Phone:** Format XXX-XXX-XXXX (e.g., "555-123-4567")
   - **Department:** Select from dropdown
   - **Enrollment Year:** Valid year (e.g., 2023)
   - **GPA:** 0.0 to 4.0 (e.g., 3.50)
2. Click **"Add Student"** button
3. Success message appears
4. New student appears in table

#### Updating a Student
1. **Click on any row** in the table to select a student
2. Form fields auto-populate with student data
3. Modify the desired fields
4. Click **"Update Student"** button
5. Confirm the action
6. Table updates with new information

#### Deleting a Student
1. **Click on a row** in the table to select a student
2. Click **"Delete Student"** button
3. Confirm deletion in the dialog box
4. Student is removed from database and table

#### Other Operations
- **Clear Fields:** Resets all form inputs and deselects table row
- **Reload Data:** Refreshes table from database

---

### Validation Rules

| Field | Format | Valid Example | Invalid Example |
|-------|--------|---------------|-----------------|
| First/Last Name | Letters and spaces (2-50 chars) | John Doe | J0hn, 123 |
| Email | user@domain.extension | john@university.edu | invalid.email |
| Phone | XXX-XXX-XXXX (10 digits) | 555-123-4567 | 5551234567 |
| GPA | 0.0 to 4.0 | 3.75 | 5.0, -1.0 |
| Year | 1900 to current+1 | 2023 | 1800, 3000 |

---


## 🛠️ Troubleshooting

### Common Issues and Solutions

**Issue:** Application won't start
- **Solution:** Verify JDBC JAR is added to Build Path in Eclipse

**Issue:** "Communications link failure"
- **Solution:** Ensure MySQL service is running (check XAMPP or system services)

**Issue:** "Unknown database 'student_management'"
- **Solution:** Run the SQL script in phpMyAdmin to create the database

**Issue:** "Access denied for user 'root'"
- **Solution:** Check password in `DatabaseConnection.java` (default is empty for XAMPP)

**Issue:** Invalid phone format error
- **Solution:** Use exact format XXX-XXX-XXXX (e.g., 555-123-4567) with dashes

**Issue:** Table not updating after adding student
- **Solution:** Click "Reload Data" button or check console for error messages

---

##  Project Requirements Met

✅ **Swing Components Used (5+ required):**
- JFrame, JPanel, JLabel, JTextField, JButton, JComboBox, JTable, JScrollPane

✅ **JDBC Integration:**
- Database connection using JDBC
- PreparedStatement for all SQL queries
- Proper exception handling

✅ **CRUD Operations:**
- CREATE (Add Student)
- READ (View/Load Students)
- UPDATE (Modify Student)
- DELETE (Remove Student)

✅ **OOP Principles:**
- Encapsulation (Student class with private fields and getters/setters)
- Abstraction (StudentService interface)
- Interface Implementation (StudentServiceImpl)
- Clear separation of concerns (5-package structure)

✅ **RegEx Validation:**
- Email pattern validation
- Phone format validation (10-digit)
- Name validation (letters only)
- GPA and year range validation

✅ **Database:**
- MySQL database with proper schema
- Primary key with auto-increment
- Unique constraint on email
- Sample data for testing

---

## Conclusion

This Student Management System successfully demonstrates a complete CRUD application using Java Swing, JDBC, and MySQL with proper OOP principles, RegEx validation, and professional software development practices. The application provides an intuitive user interface for managing student records with comprehensive error handling and data validation.

---

**Date:** December 2025  
**Course:** [OBJECT ORIENTED PROGRAMMING USING JAVA]  
**Assignment:** Java Swing Desktop CRUD Application

---
