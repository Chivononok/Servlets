package enterprise.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnect {
    private String url;
    private String login;
    private String password;
    private Connection connection;

    public PostgreConnect(String url, String login, String password) throws SQLException {
        this.url = url;
        this.login = login;
        this.password = password;
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(this.url, this.login, this.password);
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {return connection;}
    public void closeConnection() throws SQLException {
        try {
            this.connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
