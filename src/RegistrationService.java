import ValidateUser.InvalidInputException;
import ValidateUser.ValidateEmail;
import ValidateUser.ValidatePassword;
import ValidateUser.ValidateUsername;

import java.util.Scanner;
public class RegistrationService {
    private DatabaseManager databaseManager;

    public RegistrationService(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }
    Scanner scanner = new Scanner(System.in);

    public boolean registerUser() {
        try {
            System.out.println("Enter your username: ");
            String username = scanner.nextLine();
            ValidateUsername.isValidUsername(username);

            if (isUsernameInUse(username)) {
                System.out.println("Username is already in use.");
                return false;
            }

            System.out.println("Enter your password: ");
            String password = scanner.nextLine();
            ValidatePassword.isValidPassword(password);
            byte[] salt = PasswordHasher.generateSalt();
            String hashedPassword = PasswordHasher.hashPassword(password,salt);
            System.out.println("Enter your email: ");
            String email = scanner.nextLine();
            ValidateEmail.isValidEmail(email);

            if (isEmailInUse(email)) {
                System.out.println("Email is already in use.");
                return false;
            }
            DatabaseManager databaseManager = new DatabaseManager();
            databaseManager.insertUser(username,password,salt,email);

            System.out.println("Registration successful.");
            return true;
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    private boolean isUsernameInUse(String username) {
        // TODO: Implement database query to check if username is in use
        // Return true if username is found in the database, false otherwise
        return false; // Placeholder
    }

    private boolean isEmailInUse(String email) {
        // TODO: Implement database query to check if email is in use
        // Return true if email is found in the database, false otherwise
        return false; // Placeholder
    }
}