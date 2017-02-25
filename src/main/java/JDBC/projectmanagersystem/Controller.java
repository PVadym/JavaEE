package JDBC.projectmanagersystem;

import JDBC.projectmanagersystem.entity.*;
import JDBC.projectmanagersystem.jbdc.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Вадим on 18.02.2017.
 */
public class Controller {
    private DeveloperDao developerDao;
    private SkillDao skillDao;
    private CompanyDao companyDao;
    private CustomerDao customerDao;
    private ProjectDao projectDao;

    public Controller() {
        developerDao = DeveloperDao.getDeveloperDao();
        skillDao = SkillDao.getSkillDao();
        companyDao = CompanyDao.getCompanyDao();
        customerDao = CustomerDao.getCustomerDao();
        projectDao = ProjectDao.getProjectDao();
    }

    public List<Developer> getAllDevelopers(){
        return developerDao.getAll();
    }
    public List<Skill> getAllSkills(){
        return skillDao.getAll();
    }
    public List<Project> getAllProjects(){
        return projectDao.getAll();
    }
    public List<Company> getAllCompanies(){
        return companyDao.getAll();
    }
    public List<Customer> getAllCustomers(){
        return customerDao.getAll();
    }

    public Object getById (EntityName entityName, int id){
        Object object;
        switch (entityName){
            case DEVELOPER: object =  developerDao.getById(id);
            break;
            case SKILL:  object =  skillDao.getById(id);
            break;
            case COMPANY:  object = companyDao.getById(id);
            break;
            case CUSTOMER:  object = customerDao.getById(id);
            break;
            case PROJECT: object = projectDao.getById(id);
                break;
            default:
                throw new RuntimeException("Error input!");
        }
        return object;
    }

    public void deleteById (EntityName entityName, int id){

        switch (entityName){
            case DEVELOPER:
                developerDao.deleteById(id);
                break;
            case SKILL:
                skillDao.deleteById(id);
                break;
            case COMPANY:
                companyDao.deleteById(id);
                break;
            case CUSTOMER:
                customerDao.deleteById(id);
                break;
            case PROJECT:
                projectDao.deleteById(id);
                break;
            default:
                throw new RuntimeException("Error input!");

        }

    }

    public void addNew (EntityName entityName, Object o){
        switch (entityName){
            case DEVELOPER:
                developerDao.addToDB((Developer) o);
                break;
            case SKILL:
                skillDao.addToDB((Skill) o);
                break;
            case COMPANY:
                companyDao.addToDB((Company) o);
                break;
            case CUSTOMER:
                customerDao.addToDB((Customer) o);
                break;
            case PROJECT:
                projectDao.addToDB((Project) o);
                break;
            default:
                throw new RuntimeException("Error input!");

        }
    }

    // methods of update SkillsDB
    public void changeSkillName(int id, String name){
        skillDao.updateSkill(id, name);
    }

    // methods of update DevelopersDB
    public void changeDeveloperAddress(int id, String name){
        developerDao.updateAddress(id, name);
    }
    public void changeDeveloperEmail(int id, String name){
        developerDao.updateEmail(id, name);
    }
    public void changeDeveloperJoinDate(int id, LocalDate date){
        developerDao.updateDateJoin(id, date);
    }
    public void changeDeveloperSalary(int id, int salary){
        developerDao.updateSalary(id, salary);
    }
    public void addToDeveloperSkill(int idDeveloper, int idSkill) {
        developerDao.addSkill(idDeveloper, idSkill);
    }
    public void deleteDeveloperSkill(int idDeveloper, int idSkill){
        developerDao.deleteSkill(idDeveloper, idSkill);
    }
    // methods of update ProjectsDB
    public void changeProjectName(int id, String name){
        projectDao.updateName(id,name);
    }
    public void changeProjectCost(int id, int cost){
        projectDao.updateCost(id,cost);
    }
    public void changeProjectCustomer(int idProject, int idCustomer){
        projectDao.updateCustomer(idProject,idCustomer);
    }
    public void changeProjectCompany(int idProject, int idCompany){
        projectDao.updateCompany(idProject,idCompany);
    }
    public void addToProjectDeveloper(int idProject, int idDeveloper){
        projectDao.addDeveloperToProject(idProject,idDeveloper);
    }
    public void deleteFromProjectDeveloper(int idProject, int idDeveloper){
        projectDao.deleteDeveloperFromProject(idProject,idDeveloper);
    }
    // methods of update CompaniesDB
    public void changeCompanyAddress(int id, String name){
        companyDao.updateAddress(id, name);
    }
    // methods of update CustomerDB
    public void changeCustomerEmail(int id, String name){
        customerDao.updateEmail(id, name);
    }

    public DeveloperDao getDeveloperDao() {
        return developerDao;
    }

    public SkillDao getSkillDao() {
        return skillDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public ProjectDao getProjectDao() {
        return projectDao;
    }
}
