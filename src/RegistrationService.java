import ValidateUser.InvalidInputException;
import ValidateUser.ValidateEmail;
import ValidateUser.ValidatePassword;
import ValidateUser.ValidateUsername;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public boolean logIn(String usernameOrEmail, String password){
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
        try{
            DatabaseManager databaseManager = new DatabaseManager();
            connection = databaseManager.getConnection();

            String sql = "SELECT COUNT(*) FROM User  WHERE user = ? OR email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2,usernameOrEmail);
            preparedStatement.setString(5,usernameOrEmail);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String storedPassword = resultSet.getString("Password");
                byte[] salt = resultSet.getBytes("Salt");
                return PasswordHasher.verifyPassword(password,storedPassword,salt);
            }

        }catch (SQLException e){
            e.printStackTrace();
    } finally {
            databaseManager.closeConnection();
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }return false;
  }
}