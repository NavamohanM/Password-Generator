# Password-Generator
# Java Password Generator

A feature-rich, secure, and customizable **Password Generator** built entirely with Java Swing. The project includes a modern GUI interface and options to generate pronounceable, strong, and customizable passwords. Passwords are automatically saved to a history file for reference.

## Features

  - Generate passwords with options:
  - Uppercase, lowercase, digits, symbols
  - Exclude similar characters (e.g., `l`, `1`, `O`, `0`)
  - Pronounceable password mode
- Auto-save password history to `password_history.txt`
-  Password history stored silently (not shown in GUI)
-  Show/Hide password toggle
-  Modern UI with clear layout and emoji-enhanced interface
-  Copy to clipboard functionality

##  Technologies Used

- Java 17+
- Swing (Java GUI Toolkit)

##  Project Structure

PasswordGeneratorProject/

├── src/

│ ├── PasswordGenerator.java

│ └── PasswordGeneratorGUI.java

├── password_history.txt

└── README.md


## How to Run

###  Prerequisites:
- Java JDK installed and added to PATH.

###  Compile:
```bash
javac PasswordGenerator.java PasswordGeneratorGUI.java

**RUN**
java PasswordGeneratorGUI


