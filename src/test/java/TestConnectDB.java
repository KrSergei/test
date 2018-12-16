import org.junit.*;
import java.sql.*;

public class TestConnectDB {
    private static final String driverName = "org.sqlite.JDBC";
    private static final String connectionString = "jdbc:sqlite:testDB.db";
    private static Connection connection;
    private static PreparedStatement pstmt;
    private static ResultSet resultSet;


    @BeforeClass
    public static void startUP() throws SQLException, ClassNotFoundException {

        //Создание соединения с БД
        connection = null;
        Class.forName(driverName);
        connection = DriverManager.getConnection(connectionString);
        System.out.println("Connect complite");

        //Создание тестовой таблицы в БД
        pstmt = connection.prepareStatement("CREATE TABLE if not exists 'Users' ('ID' INTEGER PRIMARY KEY AUTOINCREMENT, 'FirstName' STRING(100) NOT NULL, 'LastName' STRING(100));");
        pstmt.execute();

        //Заполенине таблицы данными
        pstmt = connection.prepareStatement(" INSERT INTO 'Users' ('FirstName' , 'LastName') VALUES ('Ivanov','Ivan'), ('Petrov','Petr'), ('Semenov','Semen');");
        pstmt.execute();
    }

    @AfterClass
    public static void afterTest() throws SQLException, ClassNotFoundException {

        //удаление таблицы из базы данных
        System.out.println("afterTest");
        pstmt = connection.prepareStatement("DROP TABLE IF EXISTS 'Users'");
        pstmt.execute();
        System.out.println("Table is deleting");

        //Закрытие всех соединений
        pstmt.close();
        connection.close();
        System.out.println("Connect is closing");
    }

    @Test
    public void testSearchUserOnFisrName_getUserOnFirstName() throws SQLException {

        System.out.println("testSearchUserOnFisrName_getUserOnFirstName");

        //выполение запроса поиска в БД по фамилии
        pstmt = connection.prepareStatement("SELECT * FROM Users WHERE FirstName = 'Ivanov'");
        resultSet = pstmt.executeQuery();

        //Сравнение ошидаемого результата с пролученным
        Assert.assertEquals("ID=1, FirstName=Ivanov, LastName=Ivan", String.format(
                "ID=%d, FirstName=%s, LastName=%s",
                resultSet.getInt("ID"),
                resultSet.getString("FirstName"),
                resultSet.getString("LastName")));

        //Вывод результата запроса в консооль
        while (resultSet.next()) {
            System.out.println(
                    String.format(
                            "ID=%d, FirstName=%s, LastName=%s",
                            resultSet.getInt("ID"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName")));
        }
    }

    @Test
    public void testUpdateUserOnNewFirsName_getUserWithNewFirstName() throws SQLException {

        System.out.println("testUpdateUserOnNewFirsName_getUserWithNewFirstName");

        //выполение запроса изменения  указанной фамилии в БД
        pstmt = connection.prepareStatement("UPDATE Users SET FirstName = ? " + " WHERE FirstName = ?");
        pstmt.setString(1, "Sidorov");
        pstmt.setString(2, "Ivanov");
        pstmt.executeUpdate();

        //выполение запроса поиска в БД по новой фамилии
        pstmt = connection.prepareStatement("SELECT * FROM Users WHERE FirstName = 'Sidorov'");
        resultSet = pstmt.executeQuery();

        //Сравнение ошидаемого результата с пролученным
        Assert.assertEquals("ID=1, FirstName=Sidorov, LastName=Ivan", String.format(
                "ID=%d, FirstName=%s, LastName=%s",
                resultSet.getInt("ID"),
                resultSet.getString("FirstName"),
                resultSet.getString("LastName")));
        resultSet = pstmt.executeQuery();

        //Вывод результата запроса в консооль
          while (resultSet.next()) {
            System.out.println(
                    String.format(
                            "ID=%d, FirstName=%s, LastName=%s",
                            resultSet.getInt("ID"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName")));
        }
    }
}