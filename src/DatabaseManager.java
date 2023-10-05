import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/OnlineSuperMarketDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123QWEasd";


    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with connecting to the database");
        }
    }

        public Connection getConnection() {
            return connection;
        }

        public void closeConnection() {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Problem with closing the database connection");
            }
    }

    public void insertUser(String username,String hashedPassword,byte[] salt,String email){
        try {

            String insertUserSQL = "INSERT INTO User (Username, Password, Salt, Email, Balance, RoleID, ShoppingCartID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,hashedPassword);
            preparedStatement.setBytes(3,salt);
            preparedStatement.setString(4,email);
            preparedStatement.setDouble(5,0);
            preparedStatement.setInt(6,0);
            preparedStatement.setInt(7,0);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("User inserted successfully.");
        }catch (SQLException e){
            System.out.println("Error inserting user into the database");
        }
    }
}
