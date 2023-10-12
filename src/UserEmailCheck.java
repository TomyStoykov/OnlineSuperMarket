import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEmailCheck {
    private DatabaseManager databaseManager;
        public UserEmailCheck(DatabaseManager databaseManager) {
            this.databaseManager = databaseManager;
        }
    public boolean isUsernameInUse(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseManager.getConnection();

            String sql = "SELECT COUNT(*) FROM User WHERE Username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
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
        }
    }
    public boolean isEmailInUse(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseManager.getConnection();

            String sql = "SELECT COUNT(*) FROM User WHERE Email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(5, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
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
        }
    }
}
