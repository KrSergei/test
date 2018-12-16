import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        ConnectDB.connectingtoDB();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        String userSearchByFirstName, newFirstName;
        userSearchByFirstName = reader.readLine();
        ConnectDB.searchUserOnFamily(userSearchByFirstName);
        newFirstName = reader.readLine();
        ConnectDB.updateUserFamily(userSearchByFirstName, newFirstName);
        ConnectDB.closeDB();
    }


}
