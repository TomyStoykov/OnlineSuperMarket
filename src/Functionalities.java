import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Functionalities {
    private DatabaseManager databaseManager;
    private User user;

    public Functionalities(DatabaseManager databaseManager, User user) {
        this.databaseManager = databaseManager;
        this.user = user;
    }

    public boolean removeProduct(int productId) {
        if (user.isAdminOrAdministrator()) {
            try {
                Connection connection = databaseManager.getConnection();
                String deleteProductSQL = "DELETE FROM Products WHERE ProductID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(deleteProductSQL);
                preparedStatement.setInt(1, productId);
                int rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();

                if (rowsAffected > 0) {
                    System.out.println("Product removed successfully.");
                    return true;
                } else {
                    System.out.println("Product not found or unable to delete.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error removing product.");
            } finally {
                databaseManager.closeConnection();
            }
        } else {
            System.out.println("Permission denied. User does not have sufficient privileges.");
        }

        return false;
    }
}