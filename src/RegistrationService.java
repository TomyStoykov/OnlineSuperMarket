import ValidateUser.InvalidInputException;
import ValidateUser.ValidateEmail;
import ValidateUser.ValidatePassword;
import ValidateUser.ValidateUsername;

import java.util.Scanner;
public class RegistrationService{
    private DatabaseManager databaseManager;
    private UserEmailCheck userEmailCheck;

    public RegistrationService(DatabaseManager databaseManager,UserEmailCheck userEmailCheck) {
        this.databaseManager = databaseManager;
        this.userEmailCheck = userEmailCheck;
    }

    Scanner scanner = new Scanner(System.in);

    public boolean registerUser() {
        try {
            System.out.println("Enter your username: ");
            String username = scanner.nextLine();
            ValidateUsername.isValidUsername(username);

            if (userEmailCheck.isUsernameInUse(username)) {
                System.out.println("Username is already in use.");
                return false;
            }

            System.out.println("Enter your password: ");
            String password = scanner.nextLine();
            ValidatePassword.isValidPassword(password);
            byte[] salt = PasswordHasher.generateSalt();
            String hashedPassword = PasswordHasher.hashPassword(password, salt);
            System.out.println("Enter your email: ");
            String email = scanner.nextLine();
            ValidateEmail.isValidEmail(email);

            if (userEmailCheck.isEmailInUse(email)) {
                System.out.println("Email is already in use.");
                return false;
             }
            databaseManager.insertUser(username, password, salt, email);
            System.out.println("Registration successful.");
            databaseManager.closeConnection();
            return true;
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }
}