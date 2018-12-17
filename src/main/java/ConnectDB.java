import java.sql.*;

public class ConnectDB {

    /*
    указание полей класса
     */
    private static final String driverName = "org.sqlite.JDBC";//ToDo перенести в кофигурационный файл
    private static final String connectionString = "jdbc:sqlite:testDB.db";//ToDo перенести в кофигурационный файл
    private static PreparedStatement pstmt;
    private static ResultSet resultSet;
    private static Connection connection;


    /*
    метод для создания подключений к базе данных
     */


    protected static void connectingtoDB() throws ClassNotFoundException, SQLException {

        Class.forName(driverName);
        connection = DriverManager.getConnection(connectionString);
        System.out.println("--------------------------------------------------\nConnection complete!");
    }

    /*
    метод для поиска пользователя по фамилии
     */

    protected static void searchUserByFirstName(String userSearchByFirstName) throws SQLException {

        //формирование запросапо поиску фамилии пользователя с одним параметром (отмечен знаком "?")
        String sqlQuery = "SELECT * FROM Users WHERE FirstName = ?";
        pstmt = connection.prepareStatement(sqlQuery);

        //указание параметра для запроса: переменная userSearchByFirstName
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

    /*
    метод для замены фамилии пользователя
    */
    protected static void updateUserFirstName(String userSearchByFirstName, String newFirstName) throws SQLException {

        //формирование запроса по замене фамилии пользователя с двумя параметрами (отмечены знаком "?")
        String sqlQuery = "UPDATE Users SET FirstName = ? " + " WHERE FirstName = ?";
        pstmt = connection.prepareStatement(sqlQuery);

        //указание параметров для запроса: для инждекса 1 - переменная newFirstName, для индекса 2 -  переменная userSearchByFirstName
        pstmt.setString(1, newFirstName);
        pstmt.setString(2, userSearchByFirstName);
        pstmt.executeUpdate();

        /*
        вывод результата
        */
        ///формирование запроса по поиску пользователя  по новой фамилии с одним параметром (отмечен знаком "?")
        String sqlQuery2 = "SELECT * FROM Users WHERE FirstName = ?";
        pstmt = connection.prepareStatement(sqlQuery2);

        //указание параметра для запроса: переменная newFirstName
        pstmt.setString(1, newFirstName);
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

    /*
    метод закрытия всех соединений
    */

    protected static void closeDB() throws SQLException {
        pstmt.close();
        resultSet.close();
        connection.close();
        System.out.println("--------------------------------------------------\nConnections closed!");
    }
}