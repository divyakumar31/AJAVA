import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author DIVYA
 */

public class DbOperations {

    static Connection con;

    public static Connection createConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/dbname";
            String userName = "username";
            String password = "password";

            con = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    static boolean createDatabase() {
        try {
            String tableName = "Books";
            String sql = "Create table " + tableName
                    + " (BookTitle varchar(50), Authorname varchar(20), Publisher varchar(50), Price int);";
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);

            statement.close();
            System.out.println(tableName + " has been created successfully");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addBook(String name, String author, String publisher, int price) {
        boolean flag = false;
        try {
            String query = "INSERT INTO Books (BookTitle, Authorname, Publisher, Price) VALUES (?, ?, ?, ?);";

            // preparedStatement
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setString(2, author);
            pstmt.setString(3, publisher);
            pstmt.setInt(4, price);

            pstmt.executeUpdate();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean deleteBook(String name) {
        boolean flag = false;
        try {
            String query = "DELETE FROM Books WHERE BookTitle=(?);";
            // preparedStatement
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean searchBook(String name) {
        boolean flag = false;
        try {
            String query = "SELECT * FROM Books WHERE BookTitle=(?);";

            // preparedStatement
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                System.out.println("No books named " + name + " in database.");
            } else {
                while (rs.next()) {
                    System.out.println("Book Name: " + rs.getString("BookTitle") + "\nBook Author:"
                            + rs.getString("Authorname") + "\nBook Publisher:" + rs.getString("Publisher")
                            + "\nBook Price:" + rs.getInt("Price"));
                }
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void updateBookPrice(String name, int price) {
        try {

            String query = "UPDATE Books SET Price=(?) WHERE BookTitle=(?);";

            // preparedStatement
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, price);
            pstmt.setString(2, name);
            pstmt.executeUpdate();

            System.out.println("Price Updated Successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There is some issue please try again later.");
        }
    }

}
