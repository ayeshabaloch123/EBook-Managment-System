import java.sql.*;

import javax.swing.*;

public class libraryBackEnd {
    static String password = "root";
    static String userName = "root";

    public static void database() {

    }

    /**
     *
     * Method that shows all the entries in the database currently
     *
     * @throws SQLException
     */
    public static DefaultListModel<String> viewAll() throws SQLException {
        Connection database = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook",
                userName, password);
        Statement stateMent = database.createStatement();
        DefaultListModel<String> listOfBooks = new DefaultListModel<String>();
        String sqlSelect = "select * from books";
        System.out.println("The SQL query is: " + sqlSelect);
        ResultSet rset = stateMent.executeQuery(sqlSelect);

        while (rset.next()) {

            String str = rset.getInt("id") + ", " + rset.getString("author") + ", " + rset.getString("title")
                    + ", " + rset.getInt("qty");
            listOfBooks.addElement(str);
        }
        return listOfBooks;

    }


    /**Veiw Books

     */

    public static DefaultListModel<String> viewBook() throws SQLException {
        Connection database = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook",
                userName, password);
        Statement stateMent = database.createStatement();
        DefaultListModel<String> listOfBooks = new DefaultListModel<String>();
        String sqlSelect = "select title from books";
        System.out.println("The SQL query is: " + sqlSelect);
        ResultSet rset = stateMent.executeQuery(sqlSelect);

        while (rset.next()) {

            String str =  rset.getString("title");

            listOfBooks.addElement(str);
        }
        return listOfBooks;

    }

    /**
     * Method that adds a new book to the database
     *
     * @throws SQLException
     */
    public static void add(int id, String author, String title, int qty) throws SQLIntegrityConstraintViolationException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection database = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook",
                    userName, password);
            Statement stateMent = database.createStatement();
            PreparedStatement prepareStatement = database.prepareStatement("insert into books values (?,?,?,?)");
            prepareStatement.setInt(1, id);
            prepareStatement.setString(2, author);
            prepareStatement.setString(3, title);
            prepareStatement.setInt(4, qty);

            prepareStatement.executeUpdate();


            System.out.println(prepareStatement + " records inserted.\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null, "Duplicate entry not allowed!");


        }
    }

    /**
     *
     * Method that updates any existing books entered into the library
     *
     * @throws SQLException
     */

    public static void update(int id, String author, String title, int qty, int id2) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection database = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook",
                    userName, password);
            Statement stateMent = database.createStatement();
            PreparedStatement prepareStatement = database
                    .prepareStatement("update books set id = ?, author = ?, title = ?, qty = ? where id = ?");
            prepareStatement.setInt(1, id);
            prepareStatement.setString(2, author);
            prepareStatement.setString(3, title);
            prepareStatement.setInt(4, qty);
            prepareStatement.setInt(5, id2);

                viewAll();


            prepareStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * This method deletes an entry, the user simple clicks on the book they want to
     * delete and the function will
     * remove that item from the database. Duplicates will also be removed.
     *
     * @throws SQLException
     */
    public static void delete(int id, String author, String title, int qty) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection database = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook",
                    userName, password);
            Statement stateMent = database.createStatement();
            PreparedStatement prepareStatement = database
                    .prepareStatement("delete from books where id = ? and author = ? and title = ? and qty = ? ");
            prepareStatement.setInt(1, id);
            prepareStatement.setString(2, author);
            prepareStatement.setString(3, title);
            prepareStatement.setInt(4, qty);
            prepareStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    /**
     *
     * This Method searches for a book in the database
     *
     * @throws SQLException
     */



    /**
     *
     * This Method searches for a book in the database
     *
     * @throws SQLException
     */

    public static DefaultListModel<String> search(int id, String author, String title, int qty) throws SQLException {
        Connection database = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook", "root", "");
        Statement stateMent = database.createStatement();
        DefaultListModel<String> listOfBooks = new DefaultListModel<String>();
        PreparedStatement prepareStatement = database
                .prepareStatement("select * from books where id = ? or author = ? or title = ? or qty = ? ");
        System.out.println("Selecting from table...");
        prepareStatement.setInt(1, id);
        prepareStatement.setString(2, author);
        prepareStatement.setString(3, title);
        prepareStatement.setInt(4, qty);
        ResultSet resultSet = prepareStatement.executeQuery();

        while (resultSet.next()) {

            String str = resultSet.getInt("id") + ", " + resultSet.getString("author") + ", "
                    + resultSet.getString("title")
                    + ", " + resultSet.getInt("qty");
            listOfBooks.addElement(str);

        }
        System.out.println("Selected...");
        return listOfBooks;

    }

}
