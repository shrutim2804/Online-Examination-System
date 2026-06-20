# 🎯 Online Examination System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-007396?style=for-the-badge&logo=java&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)

> A Java Swing desktop application for conducting a timed MCQ exam — login, instructions, timed questions, and instant scoring.

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

This is a desktop Java Swing app that simulates a short, timed MCQ exam. A user logs in, reads the instructions, answers 10 Java-programming questions within a 10-minute timer, and gets an instant score with percentage and feedback — all without a database, running entirely in memory.

It's built as a single-session quiz tool rather than a full multi-user exam platform — no question bank management, no persistent storage, no admin panel (see [Future Enhancements](#future-enhancements) for where this could go).

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| 🔐 **Login Screen** | Username/password authentication |
| 📝 **Instructions Page** | Exam rules shown before starting, scrollable if content overflows |
| ❓ **10 MCQ Questions** | Core Java programming concepts |
| ⏰ **Countdown Timer** | 10 minutes, with color warnings as time runs low |
| 🏷️ **Mark for Review** | Flag a question and jump back to it later |
| 🧭 **Navigation** | Move through questions with Next, auto-saves each answer |
| 📊 **Instant Results** | Score, percentage, and a performance message |
| 🎨 **Consistent UI Theme** | Gradient navy/purple background, rounded cards, shared across all screens |

---

## 📸 Screenshots

### Login Page
<img width="1162" height="782" alt="Screenshot 2026-06-20 171156" src="https://github.com/user-attachments/assets/b6c03ee2-00d9-4b05-8bfa-0f98d14392db" />

### Instructions Page
<img width="1222" height="880" alt="Screenshot 2026-06-20 171215" src="https://github.com/user-attachments/assets/7b3a80bf-7cf3-4c69-928a-36e6da9b4441" />

### Examination Page
<img width="1475" height="848" alt="Screenshot 2026-06-20 171330" src="https://github.com/user-attachments/assets/e805d239-cfa6-4794-94f5-21f78914ca02" />

### Result Page
<img width="790" height="736" alt="Screenshot 2026-06-20 171347" src="https://github.com/user-attachments/assets/83416415-f0a4-4cb1-9d46-da689ce89eab" />

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| **Java SE** | Core language |
| **Java Swing** | GUI components (JFrame, JPanel, JButton, JRadioButton, etc.) |
| **Java AWT** | Custom painting (gradients, rounded shapes) and event handling |
| **javax.swing.Timer** | Countdown timer |
| **ActionListener** | Button and timer event handling |

---

## 💻 Installation & Setup

### Prerequisites

| Software | Version | Download Link |
|----------|---------|---------------|
| Java JDK | 8 or higher | [Download](https://www.oracle.com/java/technologies/downloads/) |
| VS Code / Eclipse (optional) | Any | [VS Code](https://code.visualstudio.com/) |
| Git (optional) | Latest | [Download](https://git-scm.com/) |

### Clone the repository

```bash
git clone https://github.com/shrutim2804/Online-Examination-System.git
cd Online-Examination-System
```

### Compile

```bash
cd src
javac *.java
```

---

## 🚀 How to Run

### Option 1: Command line

```bash
cd src
java Login
```

### Option 2: VS Code

1. Open the project folder in VS Code
2. Open `src/Login.java`
3. Click **Run** (▶️) above the `main` method

---

## 🔑 Login Credentials

| Username | Password |
|----------|----------|
| `shruti` | `11111` |

⚠️ This is the only valid login — any other combination shows an error.

---

## 📁 Project Structure

```
Online-Examination-System/
│
├── src/
│   ├── Login.java          # Login screen
│   ├── Inst.java            # Instructions page
│   ├── QuestionPaper.java   # Exam interface: questions, timer, navigation
│   └── Result1.java         # Result screen: score, percentage, feedback
│
└── README.md
```

| File | Responsibility | Key Components |
|------|-----------------|-----------------|
| `Login.java` | Authentication | `JFrame`, `ActionListener`, credential check |
| `Inst.java` | Exam instructions | Scrollable rules list, fixed footer buttons |
| `QuestionPaper.java` | Exam logic | `Timer`, `JRadioButton` group, answer tracking, scoring |
| `Result1.java` | Result display | Score ring, percentage, feedback message |

---

## 📚 Question Bank

10 MCQ questions on core Java concepts:

| Q# | Question | Correct Answer |
|----|-----------|-----------------|
| 1 | Which one among these is NOT a primitive datatype? | Float |
| 2 | Which class is available to all classes automatically? | Object |
| 3 | Which package is directly available without importing it? | lang |
| 4 | String class is defined in which package? | lang |
| 5 | Which one among these is NOT a keyword in Java? | get |
| 6 | Which one among these is NOT a class? | ActionPerformed |
| 7 | Which one is NOT a function of Object class? | getDocumentBase() |
| 8 | Which function is NOT present in Applet class? | main() |
| 9 | Which one is NOT a valid Swing component? | JButtonGroup |
| 10 | What is the default value of int variable in Java? | 0 |

### Modifying questions

Edit the arrays in `QuestionPaper.java`:

```java
private String[] questions = {
    "Your new question 1",
    "Your new question 2"
};

private String[][] optionsData = {
    {"Option A1", "Option B1", "Option C1", "Option D1"},
    {"Option A2", "Option B2", "Option C2", "Option D2"}
};

// 0 = A, 1 = B, 2 = C, 3 = D
private int[] correctAnswers = {0, 1};
```

---

## 🔮 Future Enhancements

- Database connectivity (MySQL) for users and results
- Multiple subjects/categories
- Question randomization
- Admin panel to add/edit questions
- Exportable result reports (PDF/Excel)
- Negative marking option
- Password recovery

---

## 👩‍💻 Developer

**Shruti Mishra**
https://github.com/shrutim2804

---

## 📝 License

Developed for educational purposes as part of a Bachelor of Technology (Computer Science and Engineering) curriculum.

---

⭐ If this project was helpful, consider giving it a star on GitHub.
