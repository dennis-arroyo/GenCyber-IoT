package logic;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        MySQLAccess dao = new MySQLAccess();
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the ip of the rasberry pi: ");
        String url = input.next();

        dao.setURL(url);
        dao.readDataBase();
    }

}

