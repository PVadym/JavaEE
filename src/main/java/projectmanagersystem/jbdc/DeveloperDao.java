package projectmanagersystem.jbdc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectmanagersystem.Dao;
import projectmanagersystem.Developer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 18.02.2017.
 */
public class DeveloperDao extends Dao<Developer> {

    private final Logger LOGGER = LoggerFactory.getLogger(DeveloperDao.class);


    public DeveloperDao() {
        loadDriver();
    }



    @Override
    public List<Developer> getAll() {
        List<Developer> list = new ArrayList<Developer>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM sql_homework.developers");
            while (resultSet.next()) {

                Developer developer = createDeveloper(resultSet);
                list.add(developer);
            }

        } catch (SQLException e) {
            LOGGER.error("Error connection");
            throw new RuntimeException(e);
        }

        return list;
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

    @Override
    public Developer getById(int id) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM sql_homework.developers WHERE id = ?")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createDeveloper(resultSet);
            } else
                throw new RuntimeException("Can not find developer with id " + id);

        } catch (SQLException e) {
            LOGGER.error("Error connection");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection
                     .prepareStatement("DELETE  FROM sql_homework.developers WHERE id = ?")) {

            statement.setInt(1, id);
            statement.executeUpdate();
            LOGGER.info("Developer with id " + id + " was delete");

        } catch (SQLException e) {
            LOGGER.error("Error in delete procedure");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToDB(Developer developer){
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO sql_homework.developers VALUES (?,?,?,?,?,?,?)")) {

            statement.setInt(1, developer.getId());
            statement.setString(2, developer.getName());
            statement.setString(3, developer.getSurname());
            statement.setString(4, developer.getAddress());
            statement.setString(5, developer.getEmail());
            statement.setDate(6, Date.valueOf(developer.getDate()));
            statement.setInt(7, developer.getSalary());

            statement.executeUpdate();
            LOGGER.info("Developer " + developer.getName() + " "+ developer.getSurname() + " was add");

        } catch (SQLException e) {
            LOGGER.error("Error in adding procedure");
            throw new RuntimeException(e);
        }

    }


    public void updateSalary(int id, int newSalary){

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE  sql_homework.developers " +
                                     "SET sql_homework.developers.salary = ? WHERE  id=?")){

            statement.setInt(1,newSalary);
            statement.setInt(2,id);
            statement.executeUpdate();
            LOGGER.info("Developer with ID " + id + " salary successfully updated");

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }
    public void updateAddress(int id, String newAddress){

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE  sql_homework.developers " +
                             "SET sql_homework.developers.address = ? WHERE  id=?")){

            statement.setString(1,newAddress);
            statement.setInt(2,id);
            statement.executeUpdate();
            LOGGER.info("Developer with ID " + id + " address successfully updated");

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }
    public void updateEmail(int id, String email){

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE  sql_homework.developers " +
                             "SET sql_homework.developers.email = ? WHERE  id=?")){

            statement.setString(1,email);
            statement.setInt(2,id);
            statement.executeUpdate();
            LOGGER.info("Developer with ID " + id + " email successfully updated");

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }
    public void updateDateJoin(int id, LocalDate date){

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE  sql_homework.developers " +
                             "SET sql_homework.developers.join_date = ? WHERE  id=?")){

            statement.setDate(1,Date.valueOf(date));
            statement.setInt(2,id);
            statement.executeUpdate();
            LOGGER.info("Developer with ID " + id + " date of join successfully updated");

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }

    public void addSkill (int idDeveloper, int idSkill){
        try (Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO sql_homework.developers_skills VALUES (?,?)")) {
            statement.setInt(1,idDeveloper);
            statement.setInt(2,idSkill);
            statement.executeUpdate();
            System.out.println("Developer with id "+ idDeveloper + "new skill was add");

        } catch (SQLException e) {
            LOGGER.error("Error addSkill procedure");
            throw new RuntimeException(e);        }
    }
}
