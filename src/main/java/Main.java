import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectDB.connectingtoDB();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        String userSearchByFirstName, newLastName;
        userSearchByFirstName = sc.nextLine();
//        DBProcessor.findingUserOnFamily();
        ConnectDB.findingUserOnFamily(userSearchByFirstName);

        ConnectDB.closeDB();
    }


}
