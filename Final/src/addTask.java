import com.mysql.cj.x.protobuf.MysqlxCursor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class addTask {


    private static JLabel userLabel;
    private static JLabel newuser;
    private static JTextField userText;
    private static JTextField date;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JButton button1;
    private static JLabel success;
    public static JFrame frame = new JFrame();

    public static void main() {


        JPanel panel = new JPanel();

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle(" New Task ");

        panel.setLayout(null);

        userLabel = new JLabel("Your New Task");
        userLabel.setBounds(10, 20, 90, 25);
        panel.add(userLabel);

        newuser = new JLabel(" When? ");
        newuser.setBounds(20, 40, 100, 100);
        panel.add(newuser);

        button1 = new JButton(" Add");
        button1.setBounds(120, 110, 90, 25);
        button1.addActionListener(e -> {
            try {
                new Addtolist().addtolist( userText.getText(), date.getText() );
                frame.dispose();
                mygdbc.main();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(button1);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 150, 25);
        panel.add(userText);

        date = new JTextField(20);
        date.setBounds(100, 80, 150, 25);
        panel.add(date);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}