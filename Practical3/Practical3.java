/**
 * 
 * @author DIVYA
 */
/*
@desc: To develop JDBC application. 
 * 1. Create a table in database named Books (BookTitle, Authorname, Publisher, Price). 
 * 2. Enter 20 records in this table. 
 * 3. Search and update the price of a given book. 
 * 4. Delete the record of a given book.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practical3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DbOperations.createConnection();
        while (true) {
            System.out.println(
                    "Press 1 for add book\nPress 2 for Search book\nPress 3 for Update book price\nPress 4 for Delete book\nPress 5 for exit.");
            int ch = Integer.parseInt(br.readLine());

            if (ch == 1) {
                // Add book
                System.out.println("Enter book name:");
                String name = br.readLine();

                System.out.println("Enter author name:");
                String author = br.readLine();

                System.out.println("Enter publisher name:");
                String publisher = br.readLine();

                System.out.println("Enter book price:");
                int price = Integer.parseInt(br.readLine());

                boolean ans = DbOperations.addBook(name, author, publisher, price);
                if (ans) {
                    System.out.println("Book added successfully.");
                } else {
                    System.out.println("There is some issue please try again later.");
                }

            } else if (ch == 2) {
                // Search book
                System.out.println("Enter book name: ");
                String name = br.readLine();

                boolean ans = DbOperations.searchBook(name);
                if (!ans) {
                    System.out.println("There is some issue please try again later.");
                } 

            } else if (ch == 3) {
                // Update book price
                System.out.println("Enter book name: ");
                String name = br.readLine();

                boolean ans = DbOperations.searchBook(name);
                if (ans) {
                    System.out.println("Enter new price: ");
                    int price = Integer.parseInt(br.readLine());

                    DbOperations.updateBookPrice(name, price);
                } else {
                    System.out.println("There is some issue please try again later.");
                }

            } else if (ch == 4) {
                // Delete book
                System.out.println("Enter book name you want to delete: ");
                String name = br.readLine();

                boolean ans = DbOperations.deleteBook(name);
                if (ans) {
                    System.out.println("Book deleted successfully.");
                } else {
                    System.out.println("No books named " + name + " in database.");
                }

            } else {
                // exit
                System.out.println("Bye...");
                break;
            }
        }
    }
}
