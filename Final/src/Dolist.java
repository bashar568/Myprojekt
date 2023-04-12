import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
public class Dolist extends JFrame {
    private JTable table;
    private JButton addButton;
    private JLabel userLabel;
    private JButton Delete;
    private JTextField DText;
    private JLabel success;

    public Dolist() throws SQLException {
        super("To Do List");
        JPanel inputPanel = new JPanel(new FlowLayout());

        userLabel = new  JLabel("User");
        inputPanel.add(userLabel);

        addButton = new JButton("Add Task");
        addButton.addActionListener(e -> {  addTask.main();


        });
        inputPanel.add(addButton);
        Delete = new JButton(" Delete");
        inputPanel.add(Delete);
        Delete.addActionListener(e -> {
            try {
                delete.main(DText.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });


        DText = new JTextField(20);
        inputPanel.add(DText);

        success = new JLabel("");
        success.setBounds(190, 62, 250, 100);
        inputPanel.add(success);
        success.setForeground(new Color(200, 2, 6));
        success.setFont(new Font("MV Boli ", Font.PLAIN, 13));

        int id = mygdbc.connectedUser.id;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdbc", "root", "Khadija");
        Statement stmt = con.createStatement();
        String sql = "select task, date from tasks where user_id ='" +id+ "'";
        ResultSet rs =stmt.executeQuery(sql);

        ResultSetMetaData metaData = rs.getMetaData();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Always return false to prevent editing
            }
        };
        int numColumns = metaData.getColumnCount();
        for (int i = 1; i <= numColumns; i++) {
            model.addColumn(metaData.getColumnName(i));
        }
        while (rs.next()) {
            Object[] rowData = new Object[numColumns];
            for (int i = 1; i <= numColumns; i++) {
                rowData[i - 1] = rs.getObject(i);
            }
            model.addRow(rowData);
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        rs.close();
        stmt.close();
        con.close();
        String username = Gui.userText.getText();
        userLabel.setText(username+"'s ToDo list");

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(inputPanel,BorderLayout.NORTH);
    }
}




