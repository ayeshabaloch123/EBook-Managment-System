

//Author: Eduard Le Roux
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class library extends JFrame {

    TextField text;

    library() {

        libraryBackEnd.database();
        int frameWidth = 850;
        int frameHeight = 700;

        // Initializing variables: buttons, labels, mainframe and textfields
        JFrame mainFrame = new JFrame();
        JButton search = new JButton("Search");
        JButton delete = new JButton("Delete");
        JButton update = new JButton("Update");
        JButton add = new JButton("Add");
        JButton viewAll = new JButton("View All");
        JButton viewItem = new JButton("Re-Insert");
        JButton clearAll = new JButton("Clear");
        JButton exit = new JButton("Exit");

        final JTextField idField = new JTextField();
        final JTextField titleField = new JTextField();
        final JTextField authorField = new JTextField();
        final JTextField qtyField = new JTextField();

        final JLabel headLabel = new JLabel("Electronic Bookstore");
        headLabel.setFont(new Font("Serif", Font.BOLD, 25));

        final JLabel idLabel = new JLabel("ID");
        final JLabel titleLabel = new JLabel("Title");
        final JLabel authorLabel = new JLabel("Author");
        final JLabel qtyLabel = new JLabel("Year");

        // Setting up of the List of books and adding the scrollPane to it
        JScrollPane scrollPane = new JScrollPane();
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        JList<String> listOfBooks = new JList<String>(listModel);
        scrollPane.setViewportView(listOfBooks);
        scrollPane.setBounds(40, 250, 600, 350);
        mainFrame.add(scrollPane);

        /*
         * Setting the bounds for all the buttons, fields and labels
         * This is where they are placed in the mainframe
         *
         */

        headLabel.setBounds(300, 5, 400, 40);

        // Top 4 labels
        idLabel.setBounds(30, 50, 100, 40);
        titleLabel.setBounds(400, 50, 100, 40);
        authorLabel.setBounds(400, 150, 100, 40);
        qtyLabel.setBounds(30, 150, 100, 40);

        idField.setBounds(100, 50, 220, 40);
        titleField.setBounds(450, 50, 350, 40);
        authorField.setBounds(450, 150, 350, 40);
        qtyField.setBounds(100, 150, 220, 40);

        // buttons on the right
        exit.setBounds(700, 15, 100, 20);
        clearAll.setBounds(700, 200, 100, 40);
        viewItem.setBounds(700, 260, 100, 40);
        viewAll.setBounds(700, 320, 100, 40);
        add.setBounds(700, 380, 100, 40);
        update.setBounds(700, 440, 100, 40);
        delete.setBounds(700, 500, 100, 40);
        search.setBounds(700, 560, 100, 40);
// Actionlisteners for when the user clicks on any of the buttons

        /*
         *
         * Where clearAll is clicked it removes the current text on display and replaces
         * it with an empty string
         */
        clearAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                authorField.setText("");
                titleField.setText("");
                qtyField.setText("");

            }
        });

        /*
         *
         * When the search button is clicked we call the search function from the
         * backend class
         * which searches for the target ID and adds it a list and then returns the list
         * afterwards it replaces the current list with the setModel method and prints
         * out
         * all the books with selected ID.
         *
         *
         */
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    listOfBooks.setModel(libraryBackEnd.search(Integer.parseInt(idField.getText()),
                            authorField.getText(), titleField.getText(), Integer.parseInt(qtyField.getText())));
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
//                    e1.printStackTrace();
                    System.err.println(e1);
                }
            }
        });

        /*
         * When the viewBook button is clicked it splits the item in the list up into an
         * array
         * and inserts the text separately back into the textfield so that the user can
         * make updates
         * and or see the specifications of the entry.
         *
         *
         */
        viewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listOfBooks.getSelectedValue() != null) {
                    String selected = listOfBooks.getSelectedValue();
                    String[] stringArray = new String[selected.length()];
                    stringArray = selected.split(", ");
                    idField.setText(stringArray[0]);
                    authorField.setText(stringArray[1]);
                    titleField.setText(stringArray[2]);
                    qtyField.setText(stringArray[3]);

                }

            }
        });

        /*
         *
         * When the delete button is pressed the item gets split up into an array
         * the delete function is called from the backend class which takes the separate
         * values
         * and removes the entry with those parameters.
         *
         * This method will also remove duplicates, if there are in the table.
         *
         *
         */

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String delete = listOfBooks.getSelectedValue();
                String[] stringArray = new String[delete.length()];
                stringArray = delete.split(", ");
                try {
                    libraryBackEnd.delete(Integer.parseInt(stringArray[0]), stringArray[1], stringArray[2],
                            Integer.parseInt(stringArray[3]));
                    listOfBooks.setModel(libraryBackEnd.viewAll());
                } catch (NumberFormatException e1) {

                    e1.printStackTrace();
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
            }
        });

        /*
         *
         * The update button requires the user to first click on the view book button to
         * insert
         * the values of the book into the textfield. Then the user can proceed to make
         * changes
         * and then press update which calls the update method from the backend class,
         * gets the values
         * and updates the entry in the database.
         *
         *
         */

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    libraryBackEnd.update(Integer.parseInt(idField.getText()), authorField.getText(),
                            titleField.getText(), Integer.parseInt(qtyField.getText()),
                            Integer.parseInt(idField.getText()));
                    listOfBooks.setModel(libraryBackEnd.viewAll());

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        /*
         * This button adds a new book into the database. The method returns a list and
         * then the list gets updated
         * and gets print out onto the textarea in the mainscreen.
         */

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    libraryBackEnd.add(Integer.parseInt(idField.getText()), authorField.getText(), titleField.getText(),
                            Integer.parseInt(qtyField.getText()));
                    listOfBooks.setModel(libraryBackEnd.viewAll());
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
            }
        });

        /*
         * When the viewAll button is pressed it adds each item in the database
         * into a list and then gets printed in the textarea.
         *
         *
         */
        viewAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    listOfBooks.setModel(libraryBackEnd.viewAll());
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
            }
        });

        /*
         * Exits the program
         *
         */

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /*
         *
         * Here we add all the labels, buttons and textfields to the mainframe of the
         * programam
         *
         *
         */

        mainFrame.add(headLabel);
        mainFrame.add(exit);

        mainFrame.add(clearAll);
        mainFrame.add(idField);
        mainFrame.add(titleField);
        mainFrame.add(authorField);
        mainFrame.add(qtyField);

        mainFrame.add(idLabel);
        mainFrame.add(titleLabel);
        mainFrame.add(authorLabel);
        mainFrame.add(qtyLabel);

        mainFrame.add(search);
        mainFrame.add(delete);
        mainFrame.add(update);
        mainFrame.add(add);
        mainFrame.add(viewAll);
        mainFrame.add(viewItem);

        mainFrame.setSize(frameWidth, frameHeight);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        library lib = new library();

        try {

            System.out.println("Establishing connection...");
            Class.forName("com.mysql.jdbc.Driver");
            Connection database = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            System.out.println("Establishing statement...");
            Statement stateMent = database.createStatement();
            System.out.println("Creating database...");
            String sqlCreate = "create database if not exists library_db;";
            stateMent.executeUpdate(sqlCreate);
            System.out.println("using library database...");
            sqlCreate = "use library_db";
            stateMent.executeUpdate(sqlCreate);
            System.out.println("Creating table books for database...");
            sqlCreate = "create table books (id int, author varchar(100), title varchar(100), qty int)";
            stateMent.executeUpdate(sqlCreate);
            System.out.println("Generated database called Library with table books...");
            libraryBackEnd.add(3001, "Charles Dickens", "A Tale of Two Cities", 30);
            libraryBackEnd.add(3002, "J.K. Rowling", "Harry Potter and the Philospoher's stone", 40);
            libraryBackEnd.add(3003, "C.S Lewis", "The Lion The Witch and the Wardrobe", 25);
            libraryBackEnd.add(3004, "J.R.R Tolkien", "The Lord of the Rings", 37);
            libraryBackEnd.add(3005, "Lewis Carroll", "Alice in Wonderland", 12);

        } catch (Exception e) {
            e.toString();
        }
    }

}
