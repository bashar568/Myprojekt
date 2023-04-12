import java.sql.*;

public class Addtolist {

    int id = (mygdbc.connectedUser.id);

    public void addtolist(String a, String b) throws SQLException {
        new mygdbc();

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdbc", "root", "Khadija");

        Statement stmt = conn.createStatement();

        String sql = "insert into tasks (user_id , task, date) Values (?,?,?) ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, a);
        preparedStatement.setString(3, b);
        preparedStatement.executeUpdate();

        String sql1 = "ALTER TABLE TASKS AUTO_INCREMENT = 1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        preparedStatement1.executeUpdate();

        stmt.close();
        conn.close();


    }

    public String setTask(int n) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdbc", "root", "Khadija");
            Statement stmt = conn.createStatement();

            String task ="";
            String sql = "select* from tasks where user_id = ? and id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1 , id);
            preparedStatement.setInt(2 , n);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                 task = resultSet.getString("task");
            }
            return task;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String setDate( int n) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdbc", "root", "Khadija");
            Statement stmt = conn.createStatement();


            String task ="";
            String sql = "select* from tasks where user_id = ? and id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1 , id);
            preparedStatement.setInt(2 , n);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                task = resultSet.getString("date");
            }
            return task;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
