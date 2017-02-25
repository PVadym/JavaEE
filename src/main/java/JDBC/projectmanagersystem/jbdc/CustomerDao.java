package JDBC.projectmanagersystem.jbdc;

import JDBC.projectmanagersystem.Dao;
import JDBC.projectmanagersystem.entity.Company;
import JDBC.projectmanagersystem.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 21.02.2017.
 */
public class CustomerDao implements Dao<Customer> {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomerDao.class);

    private static CustomerDao customerDao;

    private ConnectionDB connectionDB = new ConnectionDB();

    public static CustomerDao getCustomerDao (){
        if (customerDao == null) customerDao = new CustomerDao();
        return customerDao;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        try (Statement statement = connectionDB.getConnection().createStatement()) {

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM homeworksql.customers")) {
                while (resultSet.next()) {

                    Customer customer = createCustomer(resultSet);
                    list.add(customer);
                }
            }
        }catch (SQLException e) {
            LOGGER.error("Error in getAll procedure");
        }
        return list;
    }


    @Override
    public Customer getById(int id) {
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("SELECT * FROM homeworksql.customers WHERE id = ?")) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createCustomer(resultSet);
                } else {
                    LOGGER.error("Can not find customer with id " + id);
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
                .prepareStatement("DELETE  FROM homeworksql.customers WHERE id = ?")) {

            statement.setInt(1, id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Customer with id " + id + " was delete");
            } else {
                LOGGER.error("Can not find customer with id " + id);
                throw new SQLException();
            }

        } catch (SQLException e) {
            LOGGER.error("Error in delete procedure");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addToDB(Customer customer) {
        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("INSERT INTO homeworksql.customers VALUES (?,?,?,?)")) {

            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getFirst_name());
            statement.setString(3, customer.getLast_name());
            statement.setString(4, customer.getEmail());

            int res = statement.executeUpdate();

            if (res !=0) {
                LOGGER.info("Customer " + customer.getFirst_name() +" "+ customer.getLast_name() +" was add");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in adding procedure");
            throw new RuntimeException(e);
        }

    }
    public void updateEmail(int id, String newEmail){

        try (PreparedStatement statement = connectionDB.getConnection()
                .prepareStatement("UPDATE  homeworksql.customers " +
                             "SET homeworksql.customers.email = ? WHERE  id=?")){

            statement.setString(1,newEmail);
            statement.setInt(2,id);
            int res = statement.executeUpdate();
            if (res !=0) {
                LOGGER.info("Customer with ID " + id + " email successfully updated");
            } else
                throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error("Error in updating procedure");
            throw new RuntimeException(e);
        }
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setFirst_name(resultSet.getString("first_name"));
        customer.setLast_name(resultSet.getString("last_name"));
        customer.setEmail(resultSet.getString("email"));

        return customer;
    }

}
