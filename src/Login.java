import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener{
    private JButton button1=new JButton("Submit");
    private JFrame frame = new JFrame("Car Fleet Management System");
    private JPanel panel = new JPanel();
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JTextField username = new JTextField();
    private JPasswordField password =new JPasswordField();

    private Login() {
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        usernameLabel.setBounds(120,120,112,23);
        username.setBounds(250,120,112,20);
        passwordLabel.setBounds(120,146,112,23);
        password.setBounds(250,146,112,20);
        button1.setBounds(185,200,112,20);
        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(username);
        panel.add(password);
        panel.add(button1);
        frame.setVisible(true);
        button1.addActionListener(this);
    }
    public static void main(String args[]){
        Login logins = new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String passText = new String(password.getPassword());
        if(username.getText().equals("admin") && passText.equals("admin")){
            System.out.println("Login successful");
            int input = JOptionPane.showConfirmDialog(null,
                    "Successfully logged in", "Welcome!", JOptionPane.DEFAULT_OPTION);
            if(input==0){
                    frame.dispose();
                    CarFleetSystem carFleet = new CarFleetSystem("cars.dat");
                    carFleet.setVisible(true);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Username or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
