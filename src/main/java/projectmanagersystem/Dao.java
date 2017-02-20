package projectmanagersystem;

import java.util.List;

/**
 * Created by Вадим on 19.02.2017.
 */
public abstract class Dao<T> {

    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    protected String USER = "root";
    protected String PASSWORD = "root";
   protected String DB_URL = "jdbc:mysql://localhost:3306/SQL_homework?autoReconnect=true&useSSL=false";

   protected  void loadDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

   abstract protected List<T> getAll();

   abstract protected T getById(int id);

   abstract protected void deleteById(int id);

   abstract protected void addToDB(T t);

}
