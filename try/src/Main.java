import javax.swing.*;
import java.awt.*;
import java.sql.*;

class MySQLGui extends JFrame {
    private JTable table;

    public MySQLGui() {
        super("MySQL Data");
        setLayout(new BorderLayout());

        try {
            // Connect to the MySQL database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdbc", "root", "Khadija");
            Statement stmt = con.createStatement();

            // Retrieve data from the database
            ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");
            ResultSetMetaData metaData = rs.getMetaData();

            // Create a JTable to display the data
            int numColumns = metaData.getColumnCount();
            Object[][] data = new Object[100][numColumns];
            String[] columnNames = new String[numColumns];

            for (int i = 1; i <= numColumns; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            int row = 0;
            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    data[row][i - 1] = rs.getObject(i);
                }
                row++;
            }

            table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);

            // Close the database connection
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MySQLGui();
    }
}