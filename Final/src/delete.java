import java.sql.*;

public class delete {
    public static void main(String a) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdbc", "root", "Khadija");

        Statement stmt = conn.createStatement();

        String sql = "delete FROM tasks WHERE task = (?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, a);
        preparedStatement.executeUpdate();
        mygdbc.main();

        String sql1 = "CREATE TABLE my_table_temp\n" +
                "(id INT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, task VARCHAR(255) NOT NULL, date VARCHAR(255) NOT NULL, PRIMARY KEY (id), FOREIGN KEY (user_id) REFERENCES users(id));";
        String sql2 = "INSERT INTO my_table_temp(id,user_id,task,date)\n" +
                "SELECT ROW_NUMBER() OVER (ORDER BY id) as id,user_id,task,date\n" +
                "FROM tasks;";
        String sql3 ="drop table tasks;\n";
        String sql4 ="RENAME TABLE my_table_temp TO tasks;\n";

       stmt.executeUpdate(sql1);
        stmt.executeUpdate(sql2);
        stmt.executeUpdate(sql3);
        stmt.executeUpdate(sql4);


            conn.close();
            stmt.close();
        }
    }



