import java.sql.*;

public class ConnectDB {

    private static final String driverName = "org.sqlite.JDBC";
    private static final String connectionString = "jdbc:sqlite:testDB.db";
    private static Statement statmt;
    private static ResultSet resSet;
    private static Connection connection;

    public static void connectingtoDB() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:testDB.db");//ToDo
        System.out.println("--------------------------------------------------\nConnection complete!");
    }
    public static void findingUserOnFamily(String userSearchByFirstName) throws SQLException {
        statmt = connection.createStatement();
        ResultSet resultSet = statmt.executeQuery("SELECT * FROM User WHERE FirstName = " + userSearchByFirstName);
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
        connection.close();
//        resSet.close();

        System.out.println("Connection closed!");
    }

//
//            System.out.println("--------------------------------------------------\nConnection complete");
//        } catch (SQLException e) {
//            System.out.println("Can't get connection. Incorrect URL");
//            e.printStackTrace();
//            return;
//        }
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Can't close connection");
//            e.printStackTrace();
//        }
//    }
//    public static void findingUserFamily (String findingFamily){
//        statmt =connection.createStatement();
//    }


}
