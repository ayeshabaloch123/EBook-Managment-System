import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Register implements ActionListener{
     JButton b;
    static String password = "root";
    static String userName = "root";
    public void actionPerformed(ActionEvent e){





    }

    Register() {
        JFrame f = new JFrame("Registration Form");
        f.setSize(800, 1000);

        f.getContentPane().setBackground(new Color(153,153,153));
        f.setVisible(true);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        ImageIcon i = new ImageIcon("imge.png");
        JLabel l = new JLabel(i);
        l.setBounds(80, 00, 350, 300);
        f.getContentPane().add(l);
        JLabel l2 = new JLabel("Uname: ");
        l2.setBounds(100, 200, 150, 30);
        l2.setFont(new Font("DialogInput", Font.BOLD, 25));
        l2.setForeground(Color.black);
        f.getContentPane().add(l2);
        JTextField t = new JTextField();
        t.setBounds(250, 200, 250, 30);
        f.getContentPane().add(t);
        JLabel l3 = new JLabel("Passwrd: ");
        l3.setBounds(100, 270, 150, 30);
        l3.setFont(new Font("DialogInput", Font.BOLD, 25));
        l3.setForeground(Color.black);
        f.getContentPane().add(l3);
        JPanel panel= new JPanel();
        panel.setBounds(0,0,800,100);
        panel.setBackground(Color.black);
        f.getContentPane().add(panel);

        JLabel mainL = new JLabel("Registration");
        mainL.setFont(new Font("Serif", Font.BOLD, 55));
        mainL.setBounds(100,50, 350, 65);
        mainL.setForeground(Color.white);
        panel.add(mainL);
        JPasswordField p = new JPasswordField();
        p.setBounds(250, 270, 250, 30);
        f.getContentPane().add(p);
        JLabel l4 = new JLabel("Gender: ");
        l4.setBounds(100, 330, 150, 30);
        l4.setFont(new Font("DialogInput", Font.BOLD, 25));
        l4.setForeground(Color.black);
        f.getContentPane().add(l4);
        JRadioButton r = new JRadioButton("Male");
        r.setBounds(250, 330, 100, 30);
        JRadioButton r1 = new JRadioButton("Female");
        r1.setBounds(350, 330, 100, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r);
        bg.add(r1);
        f.getContentPane().add(r);
        f.getContentPane().add(r1);
        JLabel l5 = new JLabel("City: ");
        l5.setBounds(100, 410, 150, 30);
        l5.setFont(new Font("DialogInput", Font.BOLD, 25));
        l5.setForeground(Color.BLACK);
        f.getContentPane().add(l5);
        String Choice[] = {"Hyderabad", "Jamshoro", "Karachi", "Dadu", "Lahore", "Islamabad", "Quetta"};
        JComboBox combo = new JComboBox(Choice);
        combo.setBounds(250, 410, 150, 30);
        f.getContentPane().add(combo);
        JLabel l6 = new JLabel("Email: ");
        l6.setBounds(100, 480, 150, 30);
        l6.setFont(new Font("DialogInput", Font.BOLD, 25));
        l6.setForeground(Color.BLACK);
        f.getContentPane().add(l6);
        JTextField t1 = new JTextField();
        t1.setBounds(250, 480, 180, 30);
        f.getContentPane().add(t1);


        b = new JButton("Register");
        b.setBounds(200, 600, 140, 40);
        b.setBackground(Color.black);
        b.setForeground(Color.white);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Name = t.getText(); //Store username entered by the user in the variable "username"
                String password1 = p.getText(); //Store password entered by the user in the variable "password"
                // String Email = tf.getText();
                String email = t1.getText();
                String value = combo.getSelectedItem().toString();
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

                                combo.addItem((String) ob[0]);
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
        b1.setBounds(330, 600, 140, 40);
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {

                                     if(e.getSource()== b1)
                                        // cb.setText("");
                                     t.setText("");
                                     p.setText("");
                                     r.setSelected(false);
                                     r1.setSelected(false);
                                     bg.clearSelection();
                                     t1.setText("");
                                     combo.setSelectedIndex(0);


                                 }
                             }

        );
        f.getContentPane().add(b1);

    }
    public static void main(String[] args) {
        new Register();
    }
}