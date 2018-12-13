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

    public static void findingUserOnFamily(String userSearchByFirstName, String newFirstName) throws SQLException {
//        String sqlQuery = "SELECT * FROM User WHERE FirstName = ?";
//        pstmt  = connection.prepareStatement(sqlQuery);
//        pstmt.setString(1, userSearchByFirstName);
//        resultSet = pstmt.executeQuery();





        String sqlQuery = "UPDATE User SET FirstName = ?" + " WHERE FirstName = ?";
        pstmt  = connection.prepareStatement(sqlQuery);
        pstmt.setString(1, userSearchByFirstName);
        pstmt.setString(2, newFirstName);
        resultSet = pstmt.executeQuery();



//        resultSet = statmt.executeQuery("SELECT * FROM User WHERE FirstName = " + userSearchByFirstName);
//        resultSet =statmt.executeQuery("UPDATE User SET FirstName = " + newFirstName +" WHERE FirstName = " + userSearchByFirstName);
        while (resultSet.next()) {
            System.out.println(
                    String.format(
                            "ID=%d, FirstName=%s, LastName=%s",
                            resultSet.getInt("ID"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName")));
        }
    }

    public static void closeDB() throws SQLException {
        pstmt.close();
        resultSet.close();
        connection.close();
        System.out.println("--------------------------------------------------\nConnections closed!");
    }

}
