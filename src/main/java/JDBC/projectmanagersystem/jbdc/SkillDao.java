package JDBC.projectmanagersystem.jbdc;

import JDBC.projectmanagersystem.Dao;
import JDBC.projectmanagersystem.entity.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 19.02.2017.
 */
public class SkillDao implements Dao<Skill> {

    private final Logger LOGGER = LoggerFactory.getLogger(SkillDao.class);

    private static SkillDao skillDao;

    private ConnectionDB connectionDB = new ConnectionDB();

    public static SkillDao getSkillDao (){
        if (skillDao == null) skillDao = new SkillDao();
        return skillDao;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> list = new ArrayList<>();
        try (Connection connection = connectionDB.getConnection()){
                try (Statement statement = connection.createStatement()){
                    try (ResultSet resultSet = statement.executeQuery("SELECT * FROM homeworksql.skills")){

                        while (resultSet.next()) {
                            Skill skill = createSkill(resultSet);
                            list.add(skill);
                        }
                    }
                }
        } catch (SQLException e) {
            LOGGER.error("Error in getAll procedure");
            throw new RuntimeException(e);
        }
        return list;
    }



    @Override
    public Skill getById(int id) {

        try (Connection connection = connectionDB.getConnection()){
            try (PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM homeworksql.skills WHERE id = ?")){

                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        return createSkill(resultSet);
                    } else {
                        LOGGER.error("Can not find skill with id " + id);
                        throw new SQLException();
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error in getById procedure");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = connectionDB.getConnection()){
            try (PreparedStatement statement = connection
                    .prepareStatement("DELETE  FROM homeworksql.skills WHERE id = ?")){
                statement.setInt(1, id);
                int res = statement.executeUpdate();
                if (res!=0){
                    LOGGER.info("Skill with id " + id + " was delete");
                }else {
                    LOGGER.error("Can not found skill with id " + id);
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error in delete procedure");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToDB(Skill skill) {
        try (Connection connection = connectionDB.getConnection()){
            try(PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO homeworksql.skills VALUES (?,?)")){
                statement.setInt(1, skill.getId());
                statement.setString(2, skill.getName());

                int res = statement.executeUpdate();
                if (res!=0) {
                    LOGGER.info("Skill " + skill.getName() + " was add");
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error in adding procedure");
            throw new RuntimeException(e);
        }

    }

    public void updateSkill(int id, String name){
        try (Connection connection = connectionDB.getConnection()){
            try(PreparedStatement statement = connection
                    .prepareStatement("UPDATE  homeworksql.skills " +
                            "SET homeworksql.skills.name = ? WHERE  id=?")){
                statement.setString(1,name);
                statement.setInt(2,id);
                int res = statement.executeUpdate();
                if (res != 0) {
                    LOGGER.info("Skill with ID " + id + " name successfully updated");
                }else{
                    LOGGER.error("Can not found skill with id " + id);
                    throw  new SQLException();
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }

    private Skill createSkill(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();
        skill.setId(resultSet.getInt("id"));
        skill.setName(resultSet.getString("name"));
        return skill;
    }
}
