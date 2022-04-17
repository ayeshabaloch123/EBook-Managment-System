import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frontpage implements ActionListener {
    JButton b,b1;
    JFrame f;
    frontpage(){
    f= new JFrame();
    f.setSize(500,500);
    f.setLayout(null);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    b = new JButton("Register");
    b.setBounds(10,20,100,30);
    b.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Register();
        }
    });
    f.add(b);

    b1 = new JButton("Sign in");
    b1.setBounds(100,20,100,30);
    b1.addActionListener(this);
    f.add(b1);


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
