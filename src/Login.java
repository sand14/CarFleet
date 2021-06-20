import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JButton button1=new JButton("Submit");
    private JFrame panel1 = new JFrame("Car Fleet Management System");
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JTextField username = new JTextField();
    private JPasswordField password =new JPasswordField();
    private Container contentPane=panel1.getContentPane();

    private Login() {
        contentPane.setLayout(null);
        usernameLabel.setBounds(120,120,112,23);
        username.setBounds(250,120,112,20);
        passwordLabel.setBounds(120,146,112,23);
        password.setBounds(250,146,112,20);
        button1.setBounds(185,200,112,20);
        contentPane.add(usernameLabel);
        contentPane.add(passwordLabel);
        contentPane.add(username);
        contentPane.add(password);
        contentPane.add(button1);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel1.setLocationRelativeTo(null);
        panel1.setSize(500,500);
        panel1.setVisible(true);
        //panel1.pack();
        panel1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[]){
        Login logins=new Login();
    }
}
