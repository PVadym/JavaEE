package projectmanagersystem.jbdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectmanagersystem.Dao;
import projectmanagersystem.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 19.02.2017.
 */
public class SkillDao extends Dao<Skill> {

    private final Logger LOGGER = LoggerFactory.getLogger(SkillDao.class);


    @Override
    public List<Skill> getAll() {
        List<Skill> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM sql_homework.skills");
            while (resultSet.next()) {

                Skill skill = createSkill(resultSet);
                list.add(skill);
            }

        } catch (SQLException e) {
            LOGGER.error("Error connection");
            throw new RuntimeException(e);
        }

        return list;
    }

    private Skill createSkill(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();
        skill.setId(resultSet.getInt("id"));
        skill.setName(resultSet.getString("name"));
        return skill;
    }

    @Override
    public Skill getById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM sql_homework.skills WHERE id = ?")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createSkill(resultSet);
            } else
                throw new RuntimeException("Can not find skill with id " + id);

        } catch (SQLException e) {
            LOGGER.error("Error connection");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection
                     .prepareStatement("DELETE  FROM sql_homework.skills WHERE id = ?")) {

            statement.setInt(1, id);
            statement.executeUpdate();
            LOGGER.info("Skill with id " + id + " was delete");

        } catch (SQLException e) {
            LOGGER.error("Error in delete procedure");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addToDB(Skill skill) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO sql_homework.skills VALUES (?,?)")) {

            statement.setInt(1, skill.getId());
            statement.setString(2, skill.getName());

            statement.executeUpdate();
            LOGGER.info("Skill " + skill.getName() + " was add");

        } catch (SQLException e) {
            LOGGER.error("Error in adding procedure");
            throw new RuntimeException(e);
        }

    }

    public void updateSkill(int id, String name){
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE  sql_homework.skills " +
                             "SET sql_homework.skills.name = ? WHERE  id=?")){

            statement.setString(1,name);
            statement.setInt(2,id);
            statement.executeUpdate();
            LOGGER.info("Skill with ID " + id + " name successfully updated");

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }
}
