import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Register {


    private static JLabel userLabel;
    private static JLabel newuser;
    private static JTextField userText;
    private static JTextField userEmail;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JButton button1;
    private static JLabel success;
    public static JFrame frame = new JFrame();

    public static void main() {


        JPanel panel = new JPanel();

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle(" Register ");

        panel.setLayout(null);

        userLabel = new JLabel("UserName");
        userLabel.setBounds(10, 20, 90, 25);
        panel.add(userLabel);

        newuser = new JLabel(" Email ");
        newuser.setBounds(20, 65, 100, 100);
        panel.add(newuser);

        button1 = new JButton(" Register");
        button1.setBounds(120, 140, 90, 25);
        button1.addActionListener(e -> registerUser() );
        panel.add(button1);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 70, 50);
        panel.add(passwordLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 125, 25);
        panel.add(userText);

        userEmail = new JTextField(20);
        userEmail.setBounds(100, 100, 125, 25);
        panel.add(userEmail);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 60, 125, 25);
        panel.add(passwordText);

        success = new JLabel("");
        success.setBounds(100, 140, 150, 100);
        panel.add(success);
        success.setForeground(new Color(200, 2, 6));
        success.setFont(new Font("MV Boli ", Font.PLAIN, 13));

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private static void registerUser() {
        String Username = userText.getText();
        String Email = userEmail.getText();
        String Password = String.valueOf(passwordText.getPassword());
        int id=0;

        if (Username.isEmpty() || Email.isEmpty() || Password.isEmpty()) {
            success.setText("! Please Enter All fields!");

        }
        user = addUsertodatabase(id,Username, Email, Password);
        if (user != null) {
            frame.dispose();
        } else success.setText("Registration Failed");
    }

        public static User user;
        private static User addUsertodatabase(int id,String Username, String Email, String Password){
            User user = null;
            try {

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdbc", "root", "Khadija");

                Statement stmt = conn.createStatement();
                String sql = "insert into users (username , password, Email) Values (?,?,?) ";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, Password);
                preparedStatement.setString(3, Email);

                int addRows = preparedStatement.executeUpdate();
                if ( addRows > 0){
                    user = new User();
                    user.Username= Username;
                    user.Password=Password;
                    user.Email=Email;
                }
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return user;


        }


    }



