package JDBC.projectmanagersystem.jbdc;

import JDBC.projectmanagersystem.Dao;
import JDBC.projectmanagersystem.entity.Company;
import JDBC.projectmanagersystem.entity.Developer;
import JDBC.projectmanagersystem.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 21.02.2017.
 */
public class ProjectDao implements Dao<Project>{

    private final Logger LOGGER = LoggerFactory.getLogger(ProjectDao.class);
    private static ProjectDao projectDao;
    private ConnectionDB connectionDB = new ConnectionDB();

    public static ProjectDao getProjectDao (){
        if (projectDao == null) projectDao = new ProjectDao();
        return projectDao;
    }


    @Override
    public List<Project> getAll() {
        List<Project> list = new ArrayList<>();
        try (Statement statement = connectionDB.getConnection().createStatement()) {

            try(ResultSet resultSet = statement.executeQuery("SELECT * FROM homeworksql.projects")) {
                while (resultSet.next()) {
                    Project project = createProject(resultSet);
                    list.add(project);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error in getAll procedure");
        }
        return list;
    }



    @Override
    public Project getById(int id) {
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("SELECT * FROM homeworksql.projects WHERE id = ?")) {

            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createProject(resultSet);
                } else {
                    LOGGER.error("Can not find project with id " + id);
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
                .prepareStatement("DELETE  FROM homeworksql.projects WHERE id = ?")) {

            statement.setInt(1, id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Project with id " + id + " was delete");
            } else {
                LOGGER.error("Can not find project with id " + id);
                throw new SQLException();
            }

        } catch (SQLException e) {
            LOGGER.error("Error in delete procedure");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToDB(Project project) {
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("INSERT INTO homeworksql.projects VALUES (?,?,?,?,?)")) {

            statement.setInt(1, project.getId());
            statement.setString(2, project.getName());
            statement.setInt(3, project.getCompanies_id());
            statement.setInt(4, project.getCustomers_id());
            statement.setInt(5, project.getCost());

            int res = statement.executeUpdate();

            if (res !=0) {
                LOGGER.info("Project " + project.getId()+ " was add");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in adding procedure");
            throw new RuntimeException(e);
        }

    }

    public void updateName(int id, String newName){

        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("UPDATE  homeworksql.projects " +
                             "SET homeworksql.projects.name = ? WHERE  id=?")){

            statement.setString(1,newName);
            statement.setInt(2,id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Project with ID " + id + " name successfully updated");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }
    public void updateCost(int id, int newCost){

        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("UPDATE  homeworksql.projects " +
                             "SET homeworksql.projects.cost = ? WHERE  id=?")){

            statement.setInt(1,newCost);
            statement.setInt(2,id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Project with ID " + id + " cost successfully updated");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }
    public void updateCompany(int id, int idNewCompany){

        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("UPDATE  homeworksql.projects " +
                             "SET homeworksql.projects.companies_id = ? WHERE  id=?")){

            statement.setInt(1,idNewCompany);
            statement.setInt(2,id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Project with ID " + id + " company successfully updated");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }

    public void updateCustomer(int id, int idNewCustomer){

        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("UPDATE  homeworksql.projects " +
                             "SET homeworksql.projects.customers_id = ? WHERE  id=?")){

            statement.setInt(1,idNewCustomer);
            statement.setInt(2,id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Project with ID " + id + " customer successfully updated");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }

    public void addDeveloperToProject (int idProject, int idDeveloper){
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("INSERT INTO homeworksql.projects_developers VALUES (?,?)")) {
            statement.setInt(1,idProject);
            statement.setInt(2,idDeveloper);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Project with id "+ idProject + "new developer was add");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error addDeveloper procedure");
            throw new RuntimeException(e);        }
    }

    public void deleteDeveloperFromProject (int idProject, int idDeveloper){
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("DELETE FROM homeworksql.projects_developers" +
                     " WHERE developers_id=? AND projects_id=?")) {
            statement.setInt(1,idDeveloper);
            statement.setInt(2,idProject);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("From Project with id "+ idProject + "developer " + idDeveloper +" was delete");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error delete procedure");
            throw new RuntimeException(e);        }
    }

    private Project createProject(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getInt("id"));
        project.setName(resultSet.getString("name"));
        project.setCost(resultSet.getInt("cost"));
        project.setCompanies_id(resultSet.getInt("companies_id"));
        project.setCustomers_id(resultSet.getInt("customers_id"));

        return project;
    }
}
