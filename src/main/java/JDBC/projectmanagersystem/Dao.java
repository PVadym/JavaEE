package JDBC.projectmanagersystem;

import java.util.List;

/**
 * Created by Вадим on 19.02.2017.
 */
public interface Dao<T> {


    List<T> getAll();

    T getById(int id);

    void deleteById(int id);

    void addToDB(T t);

}
