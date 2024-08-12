package BankSystem;
import java.io.*;
import java.util.*;

public class Database {

    private static HashMap<String, User> users = new HashMap<>();
    private static HashMap<String, HashMap<String, List<String>>> userTransactionHistory = new HashMap<>();
    private static User currentUser;
    private static Admin admin;

    private static final String USER_FILE = "users.txt";
    private static final String ADMIN_FILE = "admin.txt";
    private static final String HISTORY_FILE = "history.txt";

    static {
        // Load user and admin data from files
        loadUserData();
        loadAdminData();
        loadTransactionHistory();
    }

    public static void registerUser(User user) {
        users.put(user.getEmail(), user);
        saveUserData();  // Save the updated user data to file
    }

    public static boolean checkLogin(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public static boolean adminLogin_clk(String username, String password) {
        return admin != null && admin.getUsername().equals(username) && admin.getPassword().equals(password);
    }

    public static HashMap<String, User> getUsers() {
        return users;
    }

    public static void deleteUser(String email) {
        users.remove(email);
        userTransactionHistory.remove(email);
        saveUserData();  // Save the updated user data to file
        saveTransactionHistory();  // Save the updated transaction history to file
    }

    public static void updateUser(String email, String newName, String newEmail, String newPassword) {
        User user = users.get(email);
        if (user != null) {
            user.setName(newName);
            user.setEmail(newEmail);
            user.setPassword(newPassword);
            users.put(newEmail, user);
            if (!email.equals(newEmail)) {
                users.remove(email);
                userTransactionHistory.put(newEmail, userTransactionHistory.remove(email));
            }
            saveUserData();  // Save the updated user data to file
            saveTransactionHistory();  // Save the updated transaction history to file
        }
    }

    public static double getBalance() {
        return currentUser.getBalance();
    }

    public static boolean transferMoney(String recipientEmail, double amount) {
        User recipient = users.get(recipientEmail);
        if (recipient != null && currentUser.getBalance() >= amount) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);

            // Record transaction history for both sender and recipient
            recordTransaction(currentUser.getEmail(), "Transfer to " + recipientEmail, amount);
            recordTransaction(recipientEmail, "Transfer from " + currentUser.getEmail(), amount);

            saveUserData();  // Save the updated user data to file
            saveTransactionHistory();  // Save the updated transaction history to file
            return true;
        }
        return false;
    }

    public static String generateStatement(String month) {
        HashMap<String, List<String>> userHistory = userTransactionHistory.get(currentUser.getEmail());
        if (userHistory == null) {
            return "No history for selected month";
        }
        
        List<String> history = userHistory.get(month);
        if (history == null || history.isEmpty()) {
            return "No history for selected month";
        } else {
            StringBuilder statement = new StringBuilder();
            for (String record : history) {
                statement.append(record).append("\n");
            }
            return statement.toString();
        }
    }

    public static boolean depositCheck(String checkNumber, double amount) {
        currentUser.setBalance(currentUser.getBalance() + amount);

        // Record transaction history for the current user
        recordTransaction(currentUser.getEmail(), "Check Deposit - " + checkNumber, amount);

        saveUserData();  // Save the updated user data to file
        saveTransactionHistory();  // Save the updated transaction history to file
        return true;
    }

    private static void recordTransaction(String userEmail, String description, double amount) {
        String month = getCurrentMonth();
        String record = description + ": $" + amount;

        userTransactionHistory
            .computeIfAbsent(userEmail, k -> new HashMap<>())
            .computeIfAbsent(month, k -> new ArrayList<>())
            .add(record);
    }

    private static String getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return new java.text.SimpleDateFormat("MMMM").format(calendar.getTime());
    }

    private static void loadUserData() {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name = data[0];
                    String email = data[1];
                    String password = data[2];
                    double balance = Double.parseDouble(data[3]);
                    users.put(email, new User(name, email, password, balance));
                }
            }
        } catch (IOException e) {
            System.out.println("User data file not found. A new one will be created upon saving data.");
        }
    }

    private static void saveUserData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (User user : users.values()) {
                bw.write(user.getName() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getBalance());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving user data: " + e.getMessage());
        }
    }

    private static void loadAdminData() {
        try (BufferedReader br = new BufferedReader(new FileReader(ADMIN_FILE))) {
            String line = br.readLine();
            if (line != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    admin = new Admin(data[0], data[1]);
                }
            }
        } catch (IOException e) {
            // If the admin file doesn't exist, we'll create a default admin later
            System.out.println("Admin data file not found. A new one will be created with default admin.");
            admin = new Admin("admin", "admin123"); // Default admin account
            saveAdminData(); // Save the default admin account
        }
    }

    private static void saveAdminData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ADMIN_FILE))) {
            bw.write(admin.getUsername() + "," + admin.getPassword());
        } catch (IOException e) {
            System.err.println("Error saving admin data: " + e.getMessage());
        }
    }

    private static void loadTransactionHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":", 3);
                if (parts.length == 3) {
                    String userEmail = parts[0];
                    String month = parts[1];
                    String transaction = parts[2];

                    userTransactionHistory
                        .computeIfAbsent(userEmail, k -> new HashMap<>())
                        .computeIfAbsent(month, k -> new ArrayList<>())
                        .add(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("Transaction history file not found. A new one will be created upon saving data.");
        }
    }

    private static void saveTransactionHistory() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORY_FILE))) {
            for (Map.Entry<String, HashMap<String, List<String>>> userEntry : userTransactionHistory.entrySet()) {
                String userEmail = userEntry.getKey();
                for (Map.Entry<String, List<String>> monthEntry : userEntry.getValue().entrySet()) {
                    String month = monthEntry.getKey();
                    for (String record : monthEntry.getValue()) {
                        bw.write(userEmail + ":" + month + ":" + record);
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving transaction history: " + e.getMessage());
        }
    }
        
}

