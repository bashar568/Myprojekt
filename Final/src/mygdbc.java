import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class mygdbc {
    public static User connectedUser = new User();

    public static void main(){

        try {

           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdbc", "root", "Khadija");

            // create a table for users if it doesn't exist
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, username VARCHAR(255) NOT NULL, Email VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, PRIMARY KEY (id))");

            // create a table for tasks if it doesn't exist
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS tasks (id INT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, task VARCHAR(255) NOT NULL, date VARCHAR(255) NOT NULL, PRIMARY KEY (id), FOREIGN KEY (user_id) REFERENCES users(id))");
            String user = Gui.userText.getText();
            String password = Gui.passwordText.getText();


            int userId = 0 ;
            while (true) {

                if ("".equals(user) || "".equals(password)) {
                    Gui.success.setText("\"! Pleas Enter Valid Information !\"");
                } else {
                    // check if the user exists in the database
                    PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM users WHERE username = ? AND password = ?");

                    pstmt.setString(1, user);
                    pstmt.setString(2, password);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        userId = rs.getInt("id");
                        connectedUser.id = userId;
                        System.out.println(userId);
                        new Dolist();
                        break; // user found, exit the loop
                    } else {

                        Gui.success.setText("\"! Pleas Enter Valid Information !\"");

                    }
                }
                break;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }


class Gui {

    public static JLabel userLabel;
    public static JLabel newuser;
    public static JTextField userText;
    public static JLabel passwordLabel;
    public static JPasswordField passwordText;
    public static JButton button;
    public static JButton button1;
    public static JLabel success;

    public static void main(String[] args) {


        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle(" To Do List ");

        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 50, 25);
        panel.add(userLabel);

        newuser = new JLabel(" new? Then..");
        newuser.setBounds(90, 100, 100, 100);
        panel.add(newuser);

        button1 = new JButton(" Register");
        button1.setBounds(175, 135, 90, 25);
        button1.addActionListener(e -> Register.main());
        panel.add(button1);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 125, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 70, 50);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 60, 125, 25);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setBounds(100, 100, 80, 25);
        button.addActionListener(e -> mygdbc.main());
        panel.add(button);

        // comment

        success = new JLabel("");
        success.setBounds(190, 62, 250, 100);
        panel.add(success);
        success.setForeground(new Color(200, 2, 6));
        success.setFont(new Font("MV Boli ", Font.PLAIN, 13));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }


}



