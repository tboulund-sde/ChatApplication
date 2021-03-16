package dal;

import be.Message;

import java.sql.*;

public class DBMessageRepository implements IMessageRepository {
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection("jdbc:sqlserver://devops.setgo.dk:61433;databaseName=ChatApplication;user=sa;password=HelloW0rld");
        return con;
    }

    @Override
    public void save(Message message) {
        try {
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO Message (Id, [Text]) VALUES (?, ?)");
            st.setInt(1, message.getId());
            st.setString(2, message.getMessage());
            st.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int getNextId() {
        try {
            Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(Id) FROM Message");

            if(rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return 1;
    }
}
