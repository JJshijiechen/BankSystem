# Mobile Online Banking System

## Overview
This project is a **Mobile Online Banking System** that allows users to manage their banking accounts, including checking balances, transferring money, tracking income and expenses, generating statements, and depositing checks remotely. The system also provides an admin interface for managing user accounts.

## Features
- **User Registration and Login**
  - New users can register and create an account using their name, email, and password.
  - Registered users can log in to access the system's features.
  
- **Balance Display**
  - Displays the current balance by calculating the difference between total income and total expenses.
  
- **Income and Expense Tracking**
  - Allows users to record and track their income and expenses.
  
- **Money Transfer**
  - Users can transfer money to other users' accounts.
  
- **Bank Statement Generation**
  - Generates detailed account statements for a selected month.
  
- **Mobile Check Deposit**
  - Enables users to deposit checks remotely by inputting check information.
  
- **Admin Login and Account Management**
  - Admins can log in to manage and delete customer accounts.

## Technical Approach
- **Programming Language**: Java
- **IDE**: Eclipse with WindowBuilder plugin
- **UI Framework**: JFrame

## Project Structure
The project is organized into several Java classes corresponding to the different GUI screens and functionalities:

1. **LoginSelectionGUI**: Allows users to choose between user login and admin login.
2. **RegisterGUI**: Handles user registration.
3. **LoginGUI**: Manages user login.
4. **AdminLoginGUI**: Handles admin login.
5. **UserMainGUI**: The main menu for users after login.
6. **AdminMainGUI**: The main menu for admins.
7. **TransferMoneyGUI**: GUI for transferring money between accounts.
8. **GenerateStatementGUI**: GUI for generating account statements.
9. **DepositCheckGUI**: GUI for depositing checks.
10. **Database**: Manages user data, transaction history, and admin data.

## Usage
1. **User Registration**: Start by registering a new account.
2. **Login**: Log in with your credentials.
3. **Menu**: Access various features such as checking your balance, transferring money, and generating statements.
4. **Admin Access**: Admins can log in to manage user accounts.

## Installation
1. HTTPS Link: https://github.com/JJshijiechen/BankSystem.git
   or Clone the repository: gh repo clone JJshijiechen/BankSystem
2. Import the project into Eclipse.
3. Run the `Main` class to start the application.

## Contributors
- Shijie Chen
- Yinghui Du
- Elaine Lyu

