import java.sql.*;

public class ConnectDB {

    private static final String driverName = "org.sqlite.JDBC";//ToDo
    private static final String connectionString = "jdbc:sqlite:testDB.db";//ToDo
    private static PreparedStatement pstmt;
    private static ResultSet resultSet;
    private static Connection connection;

    public static void connectingtoDB() throws ClassNotFoundException, SQLException {

        Class.forName(driverName);
        connection = DriverManager.getConnection(connectionString);
        System.out.println("--------------------------------------------------\nConnection complete!");
    }


    public static void searchUserOnFamily(String userSearchByFirstName) throws SQLException {
        String sqlQuery = "SELECT * FROM User WHERE FirstName = ?";
        pstmt = connection.prepareStatement(sqlQuery);
        pstmt.setString(1, userSearchByFirstName);
        resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            System.out.println(
                    String.format(
                            "ID=%d, FirstName=%s, LastName=%s",
                            resultSet.getInt("ID"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName")));
        }
    }

    public static void updateUserFamily(String userSearchByFirstName, String newFirstName) throws SQLException {
        String sqlQuery = "UPDATE User SET FirstName = ? " + " WHERE FirstName = ?";
        pstmt = connection.prepareStatement(sqlQuery);
        pstmt.setString(1, newFirstName);
        pstmt.setString(2, userSearchByFirstName);
        pstmt.executeUpdate();
//        resultSet = pstmt.executeQuery();
//
//
//
//        resultSet = pstmt.executeQuery("SELECT * FROM User WHERE FirstName = ?" + newFirstName);
//        pstmt.setString(1, newFirstName);
//        resultSet = pstmt.executeQuery();
//        while (resultSet.next()) {
//            System.out.println(
//                    String.format(
//                            "ID=%d, FirstName=%s, LastName=%s",
//                            resultSet.getInt("ID"),
//                            resultSet.getString("FirstName"),
//                            resultSet.getString("LastName")));
//        }
    }

    public static void closeDB() throws SQLException {
        pstmt.close();
        resultSet.close();
        connection.close();
        System.out.println("--------------------------------------------------\nConnections closed!");
    }

}
