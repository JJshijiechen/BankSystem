# Mobile Online Banking System
# Overview
This project is a Mobile Online Banking System that allows users to manage their banking accounts, including checking balances, transferring money, tracking income and expenses, generating statements, and depositing checks remotely. The system also provides an admin interface for managing user accounts.

Features
User Registration and Login
New users can register and create an account using their name, email, and password.
Registered users can log in to access the system's features.
Balance Display
Displays the current balance by calculating the difference between total income and total expenses.
Income and Expense Tracking
Allows users to record and track their income and expenses.
Money Transfer
Users can transfer money to other users' accounts.
Bank Statement Generation
Generates detailed account statements for a selected month.
Mobile Check Deposit
Enables users to deposit checks remotely by inputting check information.
Admin Login and Account Management
Admins can log in to manage and delete customer accounts.
Technical Approach
Programming Language: Java
IDE: Eclipse with WindowBuilder plugin
UI Framework: JFrame
Project Structure
The project is organized into several Java classes corresponding to the different GUI screens and functionalities:

LoginSelectionGUI: Allows users to choose between user login and admin login.
RegisterGUI: Handles user registration.
LoginGUI: Manages user login.
AdminLoginGUI: Handles admin login.
UserMainGUI: The main dashboard for users after login.
AdminMainGUI: The main dashboard for admins.
TransferMoneyGUI: GUI for transferring money between accounts.
GenerateStatementGUI: GUI for generating account statements.
DepositCheckGUI: GUI for depositing checks.
Database: Manages user data, transaction history, and admin data.
Usage
User Registration: Start by registering a new account.
Login: Log in with your credentials.
Dashboard: Access various features such as checking your balance, transferring money, and generating statements.
Admin Access: Admins can log in to manage user accounts.
Installation
Clone the repository:
bash
Copy code
git clone https://github.com/JJshijiechen/BankSystem.git
Import the project into Eclipse.
Run the Main class to start the application.
Contributors
Shijie Chen
Yinghui Du
Elaine Lyu
License
This project is licensed under the MIT License - see the LICENSE file for details.
