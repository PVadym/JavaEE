package JDBC.projectmanagersystem.jbdc;

import JDBC.projectmanagersystem.Dao;
import JDBC.projectmanagersystem.entity.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 21.02.2017.
 */
public class CompanyDao implements Dao<Company> {

    private final Logger LOGGER = LoggerFactory.getLogger(CompanyDao.class);

    private static CompanyDao companyDao;

    private ConnectionDB connectionDB = new ConnectionDB();

    public static CompanyDao getCompanyDao (){
        if (companyDao == null) companyDao = new CompanyDao();
        return companyDao;
    }

    @Override
    public List<Company> getAll() {
        List<Company> list = new ArrayList<>();
        try (Statement statement = connectionDB.getConnection().createStatement()) {

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM homeworksql.companies")) {
                while (resultSet.next()) {

                    Company company = createCompany(resultSet);
                    list.add(company);
                }
            }
        }catch (SQLException e) {
            LOGGER.error("Error in getAll procedure");

        }
        return list;
    }


    @Override
    public Company getById(int id) {
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("SELECT * FROM homeworksql.companies WHERE id = ?")) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createCompany(resultSet);
                } else {
                    LOGGER.error("Can not find company with id " + id);
                    throw new SQLException();
                }

            }
        }catch (SQLException e) {
            LOGGER.error("Error getById procedure");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("DELETE  FROM homeworksql.companies WHERE id = ?")) {

            statement.setInt(1, id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Company with id " + id + " was delete");
            } else {
                LOGGER.error("Can not find company with id " + id);
                throw new SQLException();
            }

        } catch (SQLException e) {
            LOGGER.error("Error in delete procedure");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addToDB(Company company) {
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("INSERT INTO homeworksql.companies VALUES (?,?,?)")) {

            statement.setInt(1, company.getId());
            statement.setString(2, company.getName());
            statement.setString(3, company.getAddress());

            int res = statement.executeUpdate();

            if (res !=0) {
                LOGGER.info("Company " + company.getName() +" was add");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in adding procedure");
            throw new RuntimeException(e);
        }

    }
    public void updateAddress(int id, String newAddress){

        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("UPDATE  homeworksql.companies " +
                             "SET homeworksql.companies.address = ? WHERE  id=?")){

            statement.setString(1,newAddress);
            statement.setInt(2,id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Company with ID " + id + " address successfully updated");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }

    private Company createCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getInt("id"));
        company.setName(resultSet.getString("name"));
        company.setAddress(resultSet.getString("address"));

        return company;
    }

}
