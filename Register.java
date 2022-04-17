import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Register implements ActionListener{
    JCheckBox cb; JButton b;
    static String password = "root";
    static String userName = "root";
    public void actionPerformed(ActionEvent e){





    }

    Register() {
        JFrame f = new JFrame("Registration Form");
        f.setSize(800, 1000);
        f.setResizable(false);
        f.getContentPane().setBackground(Color.GRAY);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        ImageIcon i = new ImageIcon("imge.png");
        JLabel l = new JLabel(i);
        l.setBounds(80, 00, 350, 300);
        f.getContentPane().add(l);
        JLabel l2 = new JLabel("Username: ");
        l2.setBounds(200, 300, 100, 30);
        l2.setFont(new Font("DialogInput", Font.PLAIN, 18));
        l2.setForeground(Color.white);
        f.getContentPane().add(l2);
        JTextField t = new JTextField();
        t.setBounds(350, 300, 150, 30);
        f.getContentPane().add(t);
        JLabel l3 = new JLabel("Password: ");
        l3.setBounds(200, 350, 120, 30);
        l3.setFont(new Font("DialogInput", Font.PLAIN, 18));
        l3.setForeground(Color.WHITE);
        f.getContentPane().add(l3);
        JPasswordField p = new JPasswordField();
        p.setBounds(350, 350, 150, 30);
        f.getContentPane().add(p);
        JLabel l4 = new JLabel("Gender: ");
        l4.setBounds(200, 400, 100, 30);
        l4.setFont(new Font("DialogInput", Font.PLAIN, 18));
        l4.setForeground(Color.WHITE);
        f.getContentPane().add(l4);
        JRadioButton r = new JRadioButton("Male");
        r.setBounds(350, 400, 100, 30);
        JRadioButton r1 = new JRadioButton("Female");
        r1.setBounds(450, 400, 100, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r);
        bg.add(r1);
        f.getContentPane().add(r);
        f.getContentPane().add(r1);
        JLabel l5 = new JLabel("City: ");
        l5.setBounds(200, 450, 100, 30);
        l5.setFont(new Font("DialogInput", Font.PLAIN, 18));
        l5.setForeground(Color.WHITE);
        f.getContentPane().add(l5);
        String Choice[] = {"Hyderabad", "Jamshoro", "Karachi", "Dadu", "Lahore", "Islamabad", "Quetta"};
        JComboBox c = new JComboBox(Choice);
        c.setBounds(350, 450, 100, 30);
        f.getContentPane().add(c);
        JLabel l6 = new JLabel("Email: ");
        l6.setBounds(200, 500, 100, 30);
        l6.setFont(new Font("DialogInput", Font.PLAIN, 18));
        l6.setForeground(Color.WHITE);
        f.getContentPane().add(l6);
        JTextField t1 = new JTextField();
        t1.setBounds(350, 500, 150, 30);
        f.getContentPane().add(t1);


        b = new JButton("Register");
        b.setBounds(350, 650, 100, 30);
        b.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String Name = t.getText(); //Store username entered by the user in the variable "username"
                                    String password1 = p.getText(); //Store password entered by the user in the variable "password"
                                    // String Email = tf.getText();
                                    String email = t1.getText();
                                    String value = c.getSelectedItem().toString();
                                    String gender = "Male";

                                    boolean actualValue = r.isSelected();
                                    boolean Value = r1.isSelected();
                                    int i = 0;


                                    if (Name.equals("")) //If username is null
                                    {
                                        JOptionPane.showMessageDialog(null, "Please enter username", "Error Message", JOptionPane.ERROR_MESSAGE); //Display dialog box with the message
                                    } else if (password1.equals("")) //If password is null
                                    {
                                        JOptionPane.showMessageDialog(null, "Please enter password", "Error Message", JOptionPane.ERROR_MESSAGE); //Display dialog box with the message
                                    } else if (email.equals("")) {
                                        JOptionPane.showMessageDialog(null, "Please enter Email", "Error Message", JOptionPane.ERROR_MESSAGE);
                                    } else if (email.equals(i)) {
                                        JOptionPane.showMessageDialog(null, "Characters Required");
                                    } else if (e.getSource() == r) {

                                    } else if (e.getSource() == r1) {

                                    } else if (actualValue == false && Value == false) {
                                        JOptionPane.showMessageDialog(null, "Select Gender");

                                    } else if (!email.contains("@")) {

                                        JOptionPane.showMessageDialog(null, "Invalid Email");


                                    } else {

                                        if (r.isSelected())
                                            gender = "Male";
                                        else if (r1.isSelected())
                                            gender = "Female";


                                        try {
                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                            System.out.println("ok");
                                            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook",
                                                    userName, password);

                                            if (connect != null) {
                                                System.out.println("connected");

                                                Statement st = connect.createStatement();
                                                String sql = "INSERT INTO user (name,password,gender,city,email) VALUES( '" + t.getText() + "','" + p.getText() + "','" + gender + "','" + value + "','" + t1.getText() + "')";
                                                //  PreparedStatement preparedStatement=connect.prepareStatement();

                                                int x = st.executeUpdate(sql);

                                                if (x == 0) {
                                                    JOptionPane.showMessageDialog(null, "this already exists");
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "welcome," + "you are successfully login");
                                                }
                                                String sql1 = "select city from signup2";
                                                java.sql.Statement stat = connect.createStatement();
                                                ResultSet res1 = stat.executeQuery(sql);
                                                while (res1.next()) {
                                                    Object[] ob = new Object[4];
                                                    ob[0] = res1.getString(1);

                                                    c.addItem((String) ob[0]);
                                                }
                                                connect.close();


                                            }
                                        } catch (Exception ee) {
                                            System.out.println(ee);
                                        }
                                    }
                                }}
        );

        f.getContentPane().add(b);
        JButton b1 = new JButton("Clear");
        b1.setBounds(450, 650, 100, 30);
        b1.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     cb.setSelected(false);
                                     t.setText(null);
                                     p.setText(null);
                                     r.setSelected(false);
                                     r1.setSelected(false);
                                     bg.clearSelection();
                                     t1.setText(null);
                                     c.setSelectedIndex(0);


                                 }
                             }
        );
        f.getContentPane().add(b1);

    }
    public static void main(String[] args) {
        new Register();
    }
}