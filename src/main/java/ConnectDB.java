import java.sql.*;

public class ConnectDB {

    private static final String driverName = "org.sqlite.JDBC";
    private static final String connectionString = "jdbc:sqlite:testDB.db";
    private static Statement statmt;
    private static ResultSet resultSet;
    private static Connection connection;

    public static void connectingtoDB() throws ClassNotFoundException, SQLException {

        Class.forName(driverName);
        connection = DriverManager.getConnection(connectionString);//ToDo
        System.out.println("--------------------------------------------------\nConnection complete!");
    }

    public static void findingUserOnFamily(String userSearchByFirstName, String newFirstName) throws SQLException {
        statmt = connection.createStatement();
//        resultSet = statmt.executeQuery("SELECT * FROM User WHERE FirstName = " + userSearchByFirstName);
        resultSet =statmt.executeQuery("UPDATE User SET FirstName = " +  newFirstName + " WHERE FirstName = " + userSearchByFirstName);
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
        statmt.close();
        resultSet.close();
        connection.close();
        System.out.println("Connection closed!");
    }

}
