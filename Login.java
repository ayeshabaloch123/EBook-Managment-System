import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class Login implements ActionListener {

    JFrame f;
    JLabel l1, l2;
    JTextField t;
    JPasswordField p;
    JButton b1, b2;
    JLabel label;

    Login(){

        f=new JFrame("Login");
        f.setLayout(null);

        label = new JLabel("Login");
        label.setFont(new Font("Serif", Font.BOLD, 55));
        label.setBounds(110,40,250,100);
        l1=new JLabel("Username");
        l1.setBounds(40, 190, 100, 30);
        f.add(l1);
        f.add(label);

        l2=new JLabel("Password");
        l2.setBounds(40, 250, 100, 30);
        f.add(l2);

        t=new JTextField();
        t.setBounds(150, 190, 150, 30);
        f.add(t);

        p=new JPasswordField();
        p.setBounds(150, 250, 150, 30);
        f.add(p);





        b1 = new JButton("Login");
        b1.setBounds(40,360,140,40);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        f.add(b1);
        f.setResizable(false);
        b1.addActionListener(this);

        b2=new JButton("Cancel");
        b2.setBounds(180,360,140,40);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        f.add(b2);
        b2.addActionListener(this);

        f.getContentPane().setBackground(new Color(153, 153,153));

        f.setVisible(true);
        f.setSize(400,500);
        f.setLocation(400,250);

    }


    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1) {
            try{
                String password = "root";
                String userName = "root";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection database = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook",
                        userName, password);
                Statement stateMent = database.createStatement();
                String q = "select name, password from user where name='"+t.getText()+"' and password='"+p.getText()+"'";
                ResultSet rs = stateMent.executeQuery(q);
                if(rs.next()){
                    new library();
                    f.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    f.setVisible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
                //System.out.print(e);
            }}
        else if(ae.getSource()==b2) {
            System.exit(0);
        }
    }
    public static void main(String[] arg){
        new Login();
    }
}

