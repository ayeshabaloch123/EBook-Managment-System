import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class library extends JFrame {
    String username= "root";
    String Password = "root";

    TextField text;

    library() {

        libraryBackEnd.database();
        int frameWidth = 850;
        int frameHeight = 800;

        // Initializing variables: buttons, labels, mainframe and textfields
        JFrame mainFrame = new JFrame();
        mainFrame.getContentPane().setBackground(Color.gray);
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
        final JTextField toSearch = new JTextField();


        final JLabel headLabel = new JLabel("Electronic Bookstore");
        headLabel.setFont(new Font("Serif", Font.BOLD, 40));


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

        headLabel.setBounds(230, 5, 400, 40);

        // Top 4 labels
        idLabel.setBounds(30, 80, 100, 40);
        idLabel.setBackground(Color.black);
        idLabel.setFont(new Font("serif", Font.BOLD, 18));
        titleLabel.setBounds(400, 80, 100, 40);
        titleLabel.setFont(new Font("serif", Font.BOLD, 18));
        authorLabel.setBounds(380, 180, 100, 40);
        authorLabel.setFont(new Font("serif", Font.BOLD, 18));
        qtyLabel.setBounds(30, 180, 100, 40);
        qtyLabel.setFont(new Font("serif", Font.BOLD, 18));


        idField.setBounds(100, 80, 220, 40);
        titleField.setBounds(450, 80, 350, 40);
        authorField.setBounds(450, 180, 350, 40);
        qtyField.setBounds(100, 180, 220, 40);
        toSearch.setBounds(100 , 650 , 250, 40 );


        // buttons on the right
        exit.setBounds(700, 15, 100, 20);
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        clearAll.setBounds(700, 250, 100, 40);
        clearAll.setForeground(Color.white);
        clearAll.setBackground(Color.black);
        viewItem.setBounds(700, 310, 100, 40);
        viewItem.setForeground(Color.white);
        viewItem.setBackground(Color.black);
        viewAll.setBackground(Color.black);
        viewAll.setForeground(Color.white);
        viewAll.setBounds(700, 370, 100, 40);

        add.setBounds(700, 430, 100, 40);
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        update.setBounds(700, 490, 100, 40);
        update.setForeground(Color.white);
        update.setBackground(Color.black);
        delete.setBounds(700, 550, 100, 40);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        search.setBounds(330, 650, 100, 40);
        search.setForeground(Color.white);
        search.setBackground(Color.black);

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
                String s =toSearch.getText();
                String Author="";
                Boolean Check= true;
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook", username, Password);
                    Statement st = c.createStatement();
                    ResultSet set=st.executeQuery("Select * from books where id ="+Integer.parseInt(s));
                    System.out.println(s);

                    while(set.next()){
                        int id = set.getInt("id");
                        Author= set.getString("Author");
                        int Year= set.getInt("qty");
                        String title = set.getString("Title");
                        String totaldata="ID :"+id+"\nAuthor :"+Author+" \nPublishing Year :"+Year+"\nTitle :"+title;
                        Check=false;
                        System.out.println(s);
                        JOptionPane.showMessageDialog(null,totaldata);

                        System.out.println(totaldata);
                    }
                    if(Check){
                        JOptionPane.showMessageDialog(null,"Record not Found ","Error",JOptionPane.ERROR_MESSAGE);
                    }

                }
                catch (Exception a){
                    System.out.println(a);
                    JOptionPane.showMessageDialog(null,"Please Enter Valid  ","Error",JOptionPane.WARNING_MESSAGE);

                }


//                try {
//                    listOfBooks.setModel(libraryBackEnd.search(Integer.parseInt(idField.getText()),
//                            authorField.getText(), titleField.getText(), Integer.parseInt(qtyField.getText())));
//                } catch (NumberFormatException e1) {
//                    e1.printStackTrace();
//                } catch (SQLException e1) {
////                    e1.printStackTrace();
//                    System.err.println(e1);
//                }
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
        mainFrame.add(toSearch);

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
            //  Class.forName("com.mysql.jdbc.Driver");
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
