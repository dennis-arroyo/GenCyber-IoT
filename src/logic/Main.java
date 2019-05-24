package de.vogella.mysql.first.test;

import de.vogella.mysql.first.MySQLAccess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        MySQLAccess dao = new MySQLAccess();
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the ip of the rasberry pi: ");
        String url = input.nextLine();

        dao.setUrl(url);


        System.out.println("Enter your username: ");
        String email = input.nextLine();
        dao.setEmail(email);


        dao.connect();
    }

}

