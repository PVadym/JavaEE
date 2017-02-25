package JDBC.projectmanagersystem.entity;

/**
 * Created by Вадим on 21.02.2017.
 */
public class Project {
    private int id;
    private String name;
    private int cost;
    private int companies_id;
    private int customers_id;

    public Project(String name, int cost, int companies_id, int customers_id) {
        this.name = name;
        this.cost = cost;
        this.companies_id = companies_id;
        this.customers_id = customers_id;
    }

    public Project(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCompanies_id() {
        return companies_id;
    }

    public void setCompanies_id(int companies_id) {
        this.companies_id = companies_id;
    }

    public int getCustomers_id() {
        return customers_id;
    }

    public void setCustomers_id(int customers_id) {
        this.customers_id = customers_id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", companies_id=" + companies_id +
                ", customers_id=" + customers_id +
                '}';
    }
}
