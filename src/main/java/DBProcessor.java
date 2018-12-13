import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBProcessor {
    private static ResultSet resSet;
    private static Connection connection;
    private static Statement statmt;

    public static void findingUserOnFamily (/* String userSearchByLastName*/ ) throws ClassNotFoundException, SQLException {
//        ConnectDB.connectingtoDB();

//        statmt.execute("SELECT"  + userSearchByLastName + " FROM FirsName");
//        while(resSet.next())
//        {
//            int chatId = resSet.getInt("FirsName");
//            System.out.println( "Отправка сообщения пользователю с chatId = " + userSearchByLastName );
//            System.out.println();
// + userSearchByLastName
//        }
        ResultSet resultSet = statmt.executeQuery("SELECT * FROM User");

        while (resultSet.next()) {
            System.out.println(
                    String.format(
                            "ID=%d, FirstName=%s, LastName=%s",
                            resultSet.getInt("ID"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("Lastname")));
        }
    }

}
