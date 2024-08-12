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
        
    }

    public static HashMap<String, User> getUsers() {
        return users;
    }

    public static void deleteUser(String email) {
        
    }

    public static void updateUser(String email, String newName, String newEmail, String newPassword) {
        
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
        
    }

    public static boolean depositCheck(String checkNumber, double amount) {
        
    }

    private static void recordTransaction(String userEmail, String description, double amount) {
        
    }

    private static String getCurrentMonth() {
        
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
        
    }

    private static void saveAdminData() {
        
    }

    private static void loadTransactionHistory() {
        
    }

    private static void saveTransactionHistory() {

    }
        
}

