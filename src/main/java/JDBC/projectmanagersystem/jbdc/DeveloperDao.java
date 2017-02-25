package JDBC.projectmanagersystem.jbdc;


import JDBC.projectmanagersystem.Dao;
import JDBC.projectmanagersystem.entity.Developer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 18.02.2017.
 */
public class DeveloperDao implements Dao<Developer> {

    private final Logger LOGGER = LoggerFactory.getLogger(DeveloperDao.class);

    private static DeveloperDao developerDao;

    private ConnectionDB connectionDB = new ConnectionDB();

    public static DeveloperDao getDeveloperDao (){
        if (developerDao == null) developerDao = new DeveloperDao();
        return developerDao;
    }

    @Override
    public List<Developer> getAll() {

        List<Developer> list = new ArrayList<Developer>();
            try(Statement statement = connectionDB.getConnection().createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM homeworksql.developers")) {

                    while (resultSet.next()) {
                        Developer developer = createDeveloper(resultSet);
                        list.add(developer);
                    }
                }
            }catch (SQLException e) {
            LOGGER.error("Error in getAll procedure");
            }
        return list;
    }


    @Override
    public Developer getById(int id) {

        try (PreparedStatement statement = connectionDB.getConnection()
                     .prepareStatement("SELECT * FROM homeworksql.developers WHERE id = ?")) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    return createDeveloper(resultSet);
                } else {
                    LOGGER.error("Can not find developer with id " + id);
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error getById procedure");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {

        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("DELETE  FROM homeworksql.developers WHERE id = ?")) {
            statement.setInt(1, id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Developer with id " + id + " was delete");
            } else {
                LOGGER.error("Can not find developer with id " + id);
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("Error in delete procedure");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToDB(Developer developer){
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("INSERT INTO homeworksql.developers VALUES (?,?,?,?,?,?,?)")) {

            statement.setInt(1, developer.getId());
            statement.setString(2, developer.getName());
            statement.setString(3, developer.getSurname());
            statement.setString(4, developer.getAddress());
            statement.setString(5, developer.getEmail());
            statement.setDate(6, Date.valueOf(developer.getDate()));
            statement.setInt(7, developer.getSalary());

            int res = statement.executeUpdate();

            if (res !=0) {
                LOGGER.info("Developer " + developer.getName() + " "+ developer.getSurname() + " was add");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in adding procedure");
            throw new RuntimeException(e);
        }

    }

    public void updateSalary(int id, int newSalary){

        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("UPDATE  homeworksql.developers "
                        +"SET homeworksql.developers.salary = ? WHERE  id=?")){
            statement.setInt(1,newSalary);
            statement.setInt(2,id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Developer with ID " + id + " salary successfully updated");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }

    public void updateAddress(int id, String newAddress){

        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("UPDATE  homeworksql.developers " +
                             "SET homeworksql.developers.address = ? WHERE  id=?")){

            statement.setString(1,newAddress);
            statement.setInt(2,id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Developer with ID " + id + " address successfully updated");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }

    public void updateEmail(int id, String email){

        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("UPDATE  homeworksql.developers " +
                             "SET homeworksql.developers.email = ? WHERE  id=?")){

            statement.setString(1,email);
            statement.setInt(2,id);
            int res =  statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Developer with ID " + id + " email successfully updated");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }

    public void updateDateJoin(int id, LocalDate date){

        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("UPDATE  homeworksql.developers " +
                             "SET homeworksql.developers.join_date = ? WHERE  id=?")){

            statement.setDate(1,Date.valueOf(date));
            statement.setInt(2,id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Developer with ID " + id + " date of join successfully updated");
            } else
                throw new SQLException();
        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }

    public void addSkill (int idDeveloper, int idSkill){
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("INSERT INTO homeworksql.developers_skills VALUES (?,?)")) {
            statement.setInt(1,idDeveloper);
            statement.setInt(2,idSkill);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Developer with id "+ idDeveloper + "new skill was add");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error addSkill procedure");
            throw new RuntimeException(e);        }
    }

    public void deleteSkill (int idDeveloper, int idSkill){
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("DELETE FROM homeworksql.developers_skills" +
                     " WHERE developers_id=? AND skills_id=?")) {
            statement.setInt(1,idDeveloper);
            statement.setInt(2,idSkill);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Developer with id "+ idDeveloper + "skill was delete");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error delete procedure");
            throw new RuntimeException(e);        }
    }

    private Developer createDeveloper(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        developer.setId(resultSet.getInt("id"));
        developer.setName(resultSet.getString("name"));
        developer.setSurname(resultSet.getString("surname"));
        developer.setAddress(resultSet.getString("address"));
        developer.setEmail(resultSet.getString("email"));
        developer.setDate(LocalDate.parse(resultSet.getString("join_date")));
        developer.setSalary(resultSet.getInt("salary"));
        return developer;
    }
}
