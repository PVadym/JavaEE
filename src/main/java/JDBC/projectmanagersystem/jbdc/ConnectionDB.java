package JDBC.projectmanagersystem.jbdc;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Вадим on 25.02.2017.
 */
public class ConnectionDB {

    private String USER = "root";
    private String PASSWORD = "root";
    private String DB_URL = "jdbc:mysql://localhost:3306/SQL_homework?autoReconnect=true&useSSL=false";

    private BasicDataSource dataSource = new BasicDataSource();

    public ConnectionDB() {
        loadDataSource();
    }

    private void loadDataSource(){

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setMinIdle(5);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }



}
