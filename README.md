# 🎯 Online Examination System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-007396?style=for-the-badge&logo=java&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)

> A comprehensive Java Swing-based desktop application for conducting online examinations with MCQ questions, timer functionality, automatic scoring, and result calculation.

---

## 📋 Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Screenshots](#screenshots)
- [Technologies Used](#technologies-used)
- [Installation & Setup](#installation--setup)
- [How to Run](#how-to-run)
- [Login Credentials](#login-credentials)
- [Project Structure](#project-structure)
- [Question Bank](#question-bank)
- [Future Enhancements](#future-enhancements)
- [Developer](#developer)
---

## 📖 Project Overview

The **Online Examination System (OES)** is a desktop application designed to facilitate electronic examinations. This project aims to develop a secure, efficient, and user-friendly OES using Java programming language.

The system allows students to login, attempt exams within a specified timeframe, and view their results instantly. It features automatic scoring, real-time feedback, and result analysis.

### Purpose
The purpose of this system is to eliminate the flaws in the manual system of conducting exams. The manual procedure is time-consuming and error-prone due to human limitations. This system provides:
- Quick and immediate way to appear for exams
- Automatic marks calculation
- Time-bound examination
- Instant result generation

---

## ✨ Features

### Core Features
| Feature | Description |
|---------|-------------|
| 🔐 **Secure Login** | Authentication with username and password |
| 📝 **Instructions Page** | Detailed exam guidelines before starting |
| ❓ **10 MCQ Questions** | Java programming concepts |
| ⏰ **Timer** | 10-minute countdown with visual warnings |
| 🏷️ **Mark for Review** | Flag questions to revisit later |
| 🧭 **Easy Navigation** | Next button to move between questions |
| 📊 **Instant Results** | Score, percentage, and performance feedback |
| 🎨 **Modern UI** | Professional dark blue theme with gold accents |

### Technical Features
- Event-driven programming
- Real-time timer implementation
- Automatic answer saving
- Score calculation without database

---

## 📸 Screenshots

### Login Page
![Login Page]<img width="1917" height="1010" alt="login" src="https://github.com/user-attachments/assets/4e7bc2c0-93a5-4c45-b824-8b6202ee1640" />


### Instructions Page
![Instructions]<img width="1919" height="1030" alt="instructions" src="https://github.com/user-attachments/assets/d74b55ef-6106-437c-a07f-578be4e810a4" />


### Examination Page
![Exam Page]<img width="1919" height="1000" alt="qs" src="https://github.com/user-attachments/assets/5ffff03b-1cc9-4bea-8dea-644fd1307d30" />


### Result Page
![Result Page]<img width="1919" height="991" alt="result" src="https://github.com/user-attachments/assets/026da302-76c0-46e4-a852-301e675426b3" />


> **Note:** Replace placeholder images with actual screenshots from your running application.

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| **Java SE** | Core programming language |
| **Java Swing** | GUI framework for desktop application |
| **Java AWT** | Event handling and window management |
| **JFrame** | Main window container |
| **JPanel** | Layout management |
| **Timer Class** | Countdown timer implementation |
| **ActionListener** | Event handling for buttons |

---

## 💻 Installation & Setup

### Prerequisites

| Software | Version | Download Link |
|----------|---------|---------------|
| Java JDK | 8 or higher | [Download](https://www.oracle.com/java/technologies/downloads/) |
| VS Code / Eclipse | Any | [VS Code](https://code.visualstudio.com/) |
| Git (optional) | Latest | [Download](https://git-scm.com/) |

### Step 1: Clone the Repository

```bash
git clone
https://github.com/shrutim2804/Online-Examination-System.git
Step 2: Navigate to Project Folder
bash
cd Online-Examination-System
Step 3: Compile All Java Files
bash
javac src/*.java
Step 4: Run the Application
bash
java -cp src Login
🚀 How to Run
Method 1: Using Command Line
bash
cd src
java Login
Method 2: Using Batch File (Windows)
Double-click run_exam.bat file

Method 3: Using VS Code
Open folder in VS Code

Open src/Login.java

Click Run button (▶️)

Method 4: Create Executable JAR
bash
jar cvfe OnlineExamSystem.jar Login -C src .
java -jar OnlineExamSystem.jar
🔑 Login Credentials
Only One Valid Account
Username	Password
shruti	11111
⚠️ Note: Only the above credentials will work. Any other username or password will show an error message.

📁 Project Structure
text
Online-Examination-System/
│
├── src/
│   ├── Login.java              # Authentication and login screen
│   ├── Inst.java               # Instructions page
│   ├── QuestionPaper.java      # Main exam interface with timer
│   └── Result1.java            # Result display screen
│
├── screenshots/                 # Application screenshots
├── run_exam.bat                 # Windows batch file to run
├── OnlineExamSystem.jar         # Executable JAR file
└── README.md                    # Project documentation
File Descriptions
File	Description	Key Components
Login.java	User authentication	JFrame, ActionListener, Password validation
Inst.java	Exam instructions	HTML formatting, JLabel, Navigation
QuestionPaper.java	Main exam logic	Timer, Radio buttons, Score calculation
Result1.java	Result display	Score percentage, Feedback messages
📚 Question Bank
The exam contains 10 MCQ questions on Java programming:

Q#	Question	Correct Answer
1	Which one among these is NOT a primitive datatype?	Float
2	Which class is available to all classes automatically?	Object
3	Which package is directly available without importing it?	lang
4	String class is defined in which package?	lang
5	Which one among these is NOT a keyword in Java?	get
6	Which one among these is NOT a class?	ActionPerformed
7	Which one is NOT a function of Object class?	getDocumentBase()
8	Which function is NOT present in Applet class?	main()
9	Which one is NOT a valid Swing component?	JButtonGroup
10	What is the default value of int variable in Java?	0
How to Modify Questions
To add or modify questions, edit the arrays in QuestionPaper.java:

java
// Questions array
private String[] questions = {
    "Your new question 1",
    "Your new question 2"
};

// Options array (4 options per question)
private String[][] optionsData = {
    {"Option A1", "Option B1", "Option C1", "Option D1"},
    {"Option A2", "Option B2", "Option C2", "Option D2"}
};

// Correct answers (0=A, 1=B, 2=C, 3=D)
private int[] correctAnswers = {0, 1};
🔮 Future Enhancements
Database connectivity (MySQL) for storing user data

Multiple subjects and categories

Question randomization

Admin panel to add/edit questions

Detailed analytics and reports

Export results to PDF/Excel

Online exam mode (web-based)

Email notifications for results

Leaderboard system

Negative marking option

Password recovery feature

👩‍💻 Developer
Shruti Mishra


📝 License
This project is developed for educational purposes as part of the Bachelor of Technology (Computer Science and Engineering) curriculum.


⭐ Show Your Support
If you found this project helpful, please give it a ⭐ on GitHub!

🎯 Developed with ❤️ by Shruti Mishra
