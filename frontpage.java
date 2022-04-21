import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frontpage implements ActionListener {
    JButton b,b1;
    JFrame f;
    JLabel pic = new JLabel();
    JPanel pane = new JPanel();




    frontpage(){
        f= new JFrame();
        f.setSize(500,450);
        f.setLayout(null);
        pane.setLayout(null);
        pane.setBackground(Color.gray);
        pane.setBounds(0,0, 500,450 );

        pic.setIcon(new ImageIcon("src\\p2.jpg") );
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        b = new JButton("Register");
        b.setBounds(250,300,150,40);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
            }
        });
        //f.add(b);
        //f.add(pic);
        f.setBackground(Color.gray);
        pic.setBounds(2,2, 750,200);
        b1 = new JButton("Sign in");
        b1.setBounds(100,300,150,40);
        b1.addActionListener(this);
        b1.setFocusable(false);
        b.setFocusable(false);
        b1.setForeground(Color.white);
        b.setForeground(Color.white);
        b.setBackground(Color.black);
        b1.setBackground(Color.black);
        //f.add(b1);
        f.setResizable(false);

        f.add(pane);
        pane.add(pic);
        pane.add(b);
        pane.add(b1);



        f.setVisible(true);
    }

    public static void main(String[] args) {
        new frontpage();
    }

    @Override

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b){
            new Register();
        }
        if(e.getSource()==b1){
            new Login();
        }
    }
}
