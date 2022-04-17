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

    Login(){

        f=new JFrame("Login");
        f.setLayout(null);

        l1=new JLabel("Username");
        l1.setBounds(40, 20, 100, 30);
        f.add(l1);

        l2=new JLabel("Password");
        l2.setBounds(40, 70, 100, 30);
        f.add(l2);

        t=new JTextField();
        t.setBounds(150, 20, 150, 30);
        f.add(t);

        p=new JPasswordField();
        p.setBounds(150, 70, 150, 30);
        f.add(p);

        ImageIcon i1 = new ImageIcon("F:\\java\\OnlineShoppingProject\\src\\loggin.png");
        Image i2 = i1.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(350,20,150,150);
        f.add(l3);



        b1 = new JButton("Login");
        b1.setBounds(40,140,120,30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.setBackground(Color.white);
        b1.setForeground(Color.black);
        f.add(b1);
        b1.addActionListener(this);

        b2=new JButton("Cancel");
        b2.setBounds(180,140,120,30);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.setBackground(Color.white);
        b2.setForeground(Color.black);
        f.add(b2);
        b2.addActionListener(this);

        f.getContentPane().setBackground(new Color(200,228,186));

        f.setVisible(true);
        f.setSize(600,300);
        f.setLocation(400,300);

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
