import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {

        try {
            ConnectDB.connectingtoDB();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String userSearchByFirstName, newFirstName;
            System.out.println("Enter user's name: ");
            userSearchByFirstName = reader.readLine();
            ConnectDB.searchUserByFirstName(userSearchByFirstName);
            System.out.println("Enter new user's name: ");
            newFirstName = reader.readLine();
            ConnectDB.updateUserFirstName(userSearchByFirstName, newFirstName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectDB.closeDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
