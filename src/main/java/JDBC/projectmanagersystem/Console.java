package JDBC.projectmanagersystem;

import JDBC.projectmanagersystem.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static JDBC.projectmanagersystem.EntityName.*;

/**
 * Created by Вадим on 25.02.2017.
 */
public class Console {

    private  Controller controller = new Controller();

    private  final Logger LOGGER = LoggerFactory.getLogger(Console.class);
    public void start (){
        mainMenu();
    }

    private   void mainMenu() {
        System.out.println("Hello, select the action");
        System.out.println();
        System.out.println("1- Operations  with Developers, 2- Operation with Companies, 3- Operations with Projects, " +
                "4- Operations with Customers, 5 - Operations with Skills, 0- Exit");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    developerMenu();
                    break;
                case "2":
                    companyMenu();
                    break;
                case "3":
                    projectMenu();
                    break;
                case "4":
                    customerMenu();
                    break;
                case "5":
                    skillMenu();
                    break;
                case "0": {
                    System.out.println("Good Bay!");
                    System.exit(0);
                }
                break;
                default: {
                    LOGGER.error("Invalid input!");
                    mainMenu();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void skillMenu() {
        System.out.println("********************************************************************************************************");
        System.out.println("Please, make a choice:\n" + "1-view all skills, 2- find by ID, 3- delete by ID, 4-add new skill,"
                + " 5- edit skill\n"
                + "9- main menu; 0- exit");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    controller.getAllSkills().forEach(System.out::println);
                    skillMenu();
                    break;
                case "2":{
                    System.out.println("enter ID skill");
                    getMenu(reader,SKILL);
                }
                break;
                case "3":{
                    System.out.println("enter ID skill for delete");
                    deleteMenu(reader,SKILL);
                }
                break;
                case "4": {
                    System.out.println("To add a new skill enter the name`s skill");
                    String nameSkill = reader.readLine();
                    controller.addNew(SKILL, new Skill(nameSkill));
                    skillMenu();
                }
                break;
                case "5": {
                    System.out.println("Enter skill`s ID  and skill`s New name through a separator/,for example: - 5/JavaNew");
                    String string = reader.readLine();
                    String array[] = string.split("/");
                    try {
                        controller.changeSkillName(Integer.parseInt(array[0]), array[1]);
                    }catch (NumberFormatException e){
                        LOGGER.error("Invalid input!");
                    }finally {
                        skillMenu();
                    }
                }
                break;

                case "9":
                    mainMenu();
                    break;
                case "0": {
                    System.out.println("Good Bay!");
                    System.exit(0);
                }
                break;
                default: {
                    LOGGER.error("Invalid input!");
                    skillMenu();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void customerMenu() {
        System.out.println("********************************************************************************************************");
        System.out.println("Please, make a choice:\n" + "1- view all customers, 2-find by ID, 3-delete by ID, 4-add new customer,"
                + " 5- edit customer`s data\n"
                + "9- main menu; 0- exit");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    controller.getAllCustomers().forEach(System.out::println);
                    customerMenu();
                    break;
                case "2":{
                    System.out.println("enter ID customer");
                    getMenu(reader,CUSTOMER);
                }
                break;
                case "3":{
                    System.out.println("enter ID customer");
                    deleteMenu(reader,CUSTOMER);
                }
                break;
                case "4":{
                    Customer customer = createCustomer(reader);
                    controller.addNew(CUSTOMER, customer);
                    customerMenu();
                }
                break;
                case "5":{
                    System.out.println("Enter customer`s ID  and new email through a separator/,for example: - 5/vasya@ukr.net"
                            + "\nor: 9- previous menu");
                    String choiceCust = reader.readLine();
                    if (choiceCust.equals("9")) customerMenu();
                    String array[] = choiceCust.split("/");
                    try {
                        controller.changeCustomerEmail(Integer.parseInt(array[0]), array[1]);

                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        LOGGER.error("Error input, for example: - 5/vasya@ukr.net");
                        customerMenu();
                        throw new RuntimeException();
                    }
                }
                customerMenu();
                break;
                case "9":
                    mainMenu();
                    break;
                case "0": {
                    System.out.println("Good Bay!");
                    System.exit(0);
                }
                break;
                default: {
                    LOGGER.error("Invalid input!");
                    customerMenu();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private  void projectMenu() {
        System.out.println("********************************************************************************************************");
        System.out.println("Please, make a choice:\n" + "1- view all projects, 2-find by ID, 3-delete by ID, 4-add new project,"
                + " 5- edit project`s data\n"
                + "9- main menu; 0- exit");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    controller.getAllProjects().forEach(System.out::println);
                    projectMenu();
                    break;
                case "2":{
                    System.out.println("enter ID project");
                    getMenu(reader,PROJECT);
                }
                break;
                case "3":{
                    System.out.println("enter ID project");
                    deleteMenu(reader,PROJECT);
                }
                break;
                case "4":{
                    Project project = createProject(reader);
                    controller.addNew(PROJECT, project);
                    projectMenu();
                }
                break;
                case "5":
                    editProjectMenu(reader);
                    projectMenu();
                    break;
                case "9":
                    mainMenu();
                    break;
                case "0": {
                    System.out.println("Good Bay!");
                    System.exit(0);
                }
                break;
                default: {
                    LOGGER.error("Invalid input!");
                    projectMenu();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void companyMenu() {
        System.out.println("********************************************************************************************************");
        System.out.println("Please, make a choice:\n" + "1- view all companies, 2-find by ID, 3-delete by ID, 4-add new company,"
                + " 5- edit company`s data\n"
                + "9- main menu; 0- exit");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    controller.getAllCompanies().forEach(System.out::println);
                    companyMenu();
                    break;
                case "2":{
                    System.out.println("enter ID company");
                    getMenu(reader,COMPANY);
                }
                break;
                case "3":{
                    System.out.println("enter ID company");
                    deleteMenu(reader,COMPANY);
                }
                break;
                case "4":{
                    Company company = createCompany(reader);
                    controller.addNew(COMPANY, company);
                    companyMenu();
                }
                break;
                case "5":{
                    System.out.println("Enter company`s ID  and new address through a separator/,for example: - 1/USA"
                            + "\nor: 9- previous menu");
                    String choiceCom = reader.readLine();
                    if (choiceCom.equals("9")) companyMenu();
                    String array[] = choiceCom.split("/");
                    try {
                        controller.changeCompanyAddress(Integer.parseInt(array[0]), array[1]);

                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                       LOGGER.error("Error input, for example: - 1/USA");
                        companyMenu();
                        throw new RuntimeException();
                    }
                }
                companyMenu();
                break;
                case "9":
                    mainMenu();
                    break;
                case "0": {
                    System.out.println("Good Bay!");
                    System.exit(0);
                }
                break;
                default: {
                    LOGGER.error("Invalid input!");
                    companyMenu();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private void developerMenu() {
        System.out.println("********************************************************************************************************");
        System.out.println("Please, make a choice:\n" + "1- view all developers, 2-find by ID, 3-delete by ID, 4-add new developer,"
                + " 5- edit developer`s data\n"
                + "9- main menu; 0- exit");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    controller.getAllDevelopers().forEach(System.out::println);
                    developerMenu();
                    break;
                case "2":{
                    System.out.println("enter ID developer");
                    getMenu(reader,DEVELOPER);
                }
                break;
                case "3":{
                    System.out.println("enter ID developer");
                    deleteMenu(reader,DEVELOPER);
                }
                break;
                case "4":{
                    Developer developer = createDeveloper(reader);
                    controller.addNew(DEVELOPER, developer);
                    developerMenu();
                }
                break;
                case "5":
                    editDeveloperMenu(reader);
                    developerMenu();
                    break;
                case "9":
                    mainMenu();
                    break;
                case "0": {
                    System.out.println("Good Bay!");
                    System.exit(0);
                }
                break;
                default: {
                    LOGGER.error("Invalid input!");
                    developerMenu();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private  void editDeveloperMenu(BufferedReader reader) throws IOException {
        System.out.println("********************************************************************************************************");
        System.out.println("1 - edit address, 2 -  edit email, 3 - edit join date, 4 - edit salary, " +
                "5 - edit skill"
                + "\nor: 9- previous menu");
        String choice = reader.readLine();
        if (choice.equals("9")) developerMenu();
        switch (choice){
            case "1" : {
                System.out.println("Enter developer`s ID  and address through a separator/,for example: - 5/Kyiv"
                        + "\nor: 9- previous menu");
                String choice2 = reader.readLine();
                if (choice2.equals("9")) editDeveloperMenu(reader);
                String array[] = choice2.split("/");
                try {
                    controller.changeDeveloperAddress(Integer.parseInt(array[0]), array[1]);

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error input, for example: - 5/Kyiv");
                    editDeveloperMenu(reader);
                    throw new RuntimeException();
                }
            }
            break;
            case "2" : {
                System.out.println("Enter developer`s ID  and email through a separator/,for example: - 5/vasya@ukr.net"
                        + "\nor: 9- previous menu");
                String choice2 = reader.readLine();
                if (choice2.equals("9")) editDeveloperMenu(reader);
                String array[] = choice2.split("/");
                try {
                    controller.changeDeveloperEmail(Integer.parseInt(array[0]), array[1]);


                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    LOGGER.error("Error input, for example: - 5/vasya@ukr.net");
                    editDeveloperMenu(reader);
                    throw new RuntimeException();
                }
            }
            break;
            case "3" : {
                System.out.println("Enter developer`s ID  and Join Date through a separator/,for example: - 5/2015-10-25"
                        + "\nor: 9- previous menu");
                String choice2 = reader.readLine();
                if (choice2.equals("9")) editDeveloperMenu(reader);
                String array[] = choice2.split("/");
                try {
                    controller.changeDeveloperJoinDate(Integer.parseInt(array[0]), LocalDate.parse(array[1]));


                } catch (DateTimeParseException |NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    LOGGER.error("Error input, for example: - 5/2015-10-25");
                    editDeveloperMenu(reader);
                    throw new RuntimeException();
                }
            }
            break;
            case "4" : {
                System.out.println("\"Enter developer`s ID  and salary through a separator/,for example: - 5/12500"
                        + "\nor: 9- previous menu");
                String choice2 = reader.readLine();
                if (choice2.equals("9")) editDeveloperMenu(reader);
                String array[] = choice2.split("/");
                try {
                    controller.changeDeveloperSalary(Integer.parseInt(array[0]), Integer.parseInt(array[1]));


                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    LOGGER.error("Error input, for example: - 5/2015-10-25");
                    editDeveloperMenu(reader);
                    throw new RuntimeException();
                }
            }
            break;
            case "5" :
                editSkillDeveloperMenu(reader);
                editDeveloperMenu(reader);
                break;
            case "9":
                developerMenu();
                break;
            default: {
                LOGGER.error("Invalid input!");
                developerMenu();
            }
        }
    }


    private void editSkillDeveloperMenu(BufferedReader reader) throws IOException {
        System.out.println("********************************************************************************************************");
        System.out.println("1- view all skills, 2 - add skill for developer, 3 - delete skill from developer "
                + "\nor: 9- previous menu");
        String choiceskill = reader.readLine();
        if (choiceskill.equals("9")) editDeveloperMenu(reader);
        if (choiceskill.equals("1")) {
            controller.getAllSkills().forEach(System.out::println);
            editSkillDeveloperMenu(reader);
        }

        if (choiceskill.equals("2")) {
            System.out.println("Enter developer`s ID  and skill`s ID through a separator/,for example: - 5/2"
                    + "\nor: 9- previous menu");
            String choiceskill2 = reader.readLine();
            if (choiceskill2.equals("9")) editSkillDeveloperMenu(reader);

            String array[] = choiceskill2.split("/");
            try {
                controller.addToDeveloperSkill(Integer.parseInt(array[0]), Integer.parseInt(array[1]));

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                LOGGER.error("Error input, ,for example: - 5/2");
                editSkillDeveloperMenu(reader);
                throw new RuntimeException();
            }
        }
        if (choiceskill.equals("3")) {
            System.out.println("Enter developer`s ID  and skill`s ID through a separator/,for example: - 5/2"
                    + "\nor: 9- previous menu");
            String choiceskill3 = reader.readLine();

            if (choiceskill3.equals("9")) editSkillDeveloperMenu(reader);

            String array[] = choiceskill3.split("/");
            try {
                controller.deleteDeveloperSkill(Integer.parseInt(array[0]), Integer.parseInt(array[1]));

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                LOGGER.error("Error input, ,for example: - 5/2");
                editSkillDeveloperMenu(reader);
                throw new RuntimeException();
            }

        }

    }



    private  Developer createDeveloper(BufferedReader reader) throws IOException {
        System.out.println("********************************************************************************************************");

        System.out.println("To add developer enter his name, surname, address, email, join date, salary through a separator/:" +
                ", for example: Vasya/Petrov/Kyev/vasya@ukr.net/2015-05-26/5200" +"\nor 9- previous menu");
        String developerData = reader.readLine();
        if (developerData.equals("9")) developerMenu();
        String array[] = developerData.split("/");
        try {
            String firstName = array[0];
            String lastName = array[1];
            String address = array[2];
            String email = array[3];
            LocalDate date;
            try {
                date = LocalDate.parse((array[4]));
            } catch (DateTimeParseException e) {
                LOGGER.error("Error in enter Date, example : 2015-10-12");
                createDeveloper(reader);
                throw new RuntimeException();
            }

            int salary;
            try {
                salary = Integer.parseInt(array[5]);
            } catch (NumberFormatException e) {
                LOGGER.error("Invalid input salary");
                createDeveloper(reader);
                throw new RuntimeException();
            }
            return new Developer(firstName, lastName,address, email,date,salary);

        } catch (ArrayIndexOutOfBoundsException e){
            LOGGER.error("Error input, for example: Vasya/Petrov/Kyev/vasya@ukr.net/2015-05-26/5200");
            createDeveloper(reader);
            throw new RuntimeException();
        }
    }

    private  Customer createCustomer(BufferedReader reader) throws IOException {
        System.out.println("********************************************************************************************************");

        System.out.println("To add customer enter his first name, last name, email through a separator/:" +
                ", for example: Vasya/Petrov/vasya@ukr.net" +"\nor 9- previous menu");
        String customerData = reader.readLine();
        if (customerData.equals("9")) customerMenu();
        String array[] = customerData.split("/");
        try {
            String firstName = array[0];
            String lastName = array[1];
            String email = array[2];

            return new Customer(firstName, lastName, email);

        } catch (ArrayIndexOutOfBoundsException e){
            LOGGER.error("Error input, for example: Vasya/Petrov/vasya@ukr.net");
            createCustomer(reader);
            throw new RuntimeException();
        }
    }
    private  Company createCompany(BufferedReader reader) throws IOException {
        System.out.println("********************************************************************************************************");

        System.out.println("To add company enter name, address through a separator/:" +
                ", for example: Google/USA" +"\nor 9- previous menu");
        String companyData = reader.readLine();
        if (companyData.equals("9")) companyMenu();
        String array[] = companyData.split("/");
        try {
            String name = array[0];
            String address = array[1];
            return new Company(name, address);

        } catch (ArrayIndexOutOfBoundsException e){
            LOGGER.error("Error input, for example: Google/USA");
            createCompany(reader);
            throw new RuntimeException();
        }
    }

    private  Project createProject(BufferedReader reader) throws IOException {
        System.out.println("********************************************************************************************************");

        System.out.println("To add project enter name, cost, id company, id customer through a separator/:" +
                ", for example: Game/10000/1/1" +"\nor 1 - view all companies, 2 - view all customers, 9- previous menu");
        String projectData = reader.readLine();
        if (projectData.equals("9")) projectMenu();
        if (projectData.equals("1")) {
            controller.getAllCompanies().forEach(System.out::println);
            createProject(reader);
        }
        if (projectData.equals("2")) {
            controller.getAllCustomers().forEach(System.out::println);
            createProject(reader);
        }
        String array[] = projectData.split("/");
        try {
            String name = array[0];
            int cost;
            try {
                cost = Integer.parseInt(array[1]);
            } catch (NumberFormatException e) {
                LOGGER.error("Invalid input cost");
                createProject(reader);
                throw new RuntimeException();
            }
            int idCompany;
            try {
                idCompany = Integer.parseInt(array[2]);
            } catch (NumberFormatException e) {
                LOGGER.error("Invalid input id Company");
                createProject(reader);
                throw new RuntimeException();
            }
            int idCustomer;
            try {
                idCustomer = Integer.parseInt(array[3]);
            } catch (NumberFormatException e) {
                LOGGER.error("Invalid input id Customer");
                createProject(reader);
                throw new RuntimeException();
            }

            return new Project(name, cost, idCompany, idCustomer);

        } catch (ArrayIndexOutOfBoundsException e){
            LOGGER.error("Error input, for example: Game/10000/1/1");
            createProject(reader);
            throw new RuntimeException();
        }
    }

    private  void editProjectMenu(BufferedReader reader) throws IOException {
        System.out.println("********************************************************************************************************");
        System.out.println("1 - edit name, 2 -  edit cost, 3 - edit company, 4 - edit customer, 5 - edit developer "
                + "\nor: 9- previous menu");
        String choice = reader.readLine();
        if (choice.equals("9")) projectMenu();
        switch (choice){
            case "1" : {
                System.out.println("Enter project`s ID  and new name through a separator/,for example: - 5/Hello"
                        + "\nor: 9- previous menu");
                String choice2 = reader.readLine();
                if (choice2.equals("9")) editProjectMenu(reader);
                String array[] = choice2.split("/");
                try {
                    controller.changeProjectName(Integer.parseInt(array[0]), array[1]);

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    LOGGER.error("Error input, for example: - 5/Hello");
                    editProjectMenu(reader);
                    throw new RuntimeException();
                }
            }
            break;
            case "2" : {
                System.out.println("Enter project`s ID  and cost through a separator/,for example: - 5/15000"
                        + "\nor: 9- previous menu");
                String choice2 = reader.readLine();
                if (choice2.equals("9")) editProjectMenu(reader);
                String array[] = choice2.split("/");
                try {
                    controller.changeProjectCost(Integer.parseInt(array[0]), Integer.parseInt(array[1]));

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    LOGGER.error("Error input, for example: - 5/15000");
                    editProjectMenu(reader);
                    throw new RuntimeException();
                }
            }
            break;
            case "3" : {
                System.out.println("Enter project`s ID  and company`s ID through a separator/,for example: - 5/2"
                        + "\nor:1 - view all companies, 9- previous menu");
                String choice2 = reader.readLine();
                if (choice2.equals("9")) editDeveloperMenu(reader);
                if (choice2.equals("1")){
                    controller.getAllCompanies().forEach(System.out::println);
                    editDeveloperMenu(reader);
                }
                String array[] = choice2.split("/");
                try {
                    controller.changeProjectCompany(Integer.parseInt(array[0]), Integer.parseInt(array[1]));

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    LOGGER.error("Error input, for example: - 5/2");
                    editProjectMenu(reader);
                    throw new RuntimeException();
                }
            }
            break;
            case "4" : {
                System.out.println("Enter project`s ID  and customer`s ID through a separator/,for example: - 5/2"
                        + "\nor:1 - view all customers, 9- previous menu");
                String choice2 = reader.readLine();
                if (choice2.equals("9")) editDeveloperMenu(reader);
                if (choice2.equals("1")){
                    controller.getAllCustomers().forEach(System.out::println);
                    editDeveloperMenu(reader);
                }
                String array[] = choice2.split("/");
                try {
                    controller.changeProjectCustomer(Integer.parseInt(array[0]), Integer.parseInt(array[1]));

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    LOGGER.error("Error input, for example: - 5/2");
                    editProjectMenu(reader);
                    throw new RuntimeException();
                }
            }
            break;
            case "5" :
                editDeveloperOfProjectMenu(reader);
                editProjectMenu(reader);
                break;
            case "9":
                projectMenu();
                break;
            default: {
                LOGGER.error("Invalid input!");
                projectMenu();
            }
        }

    }

    private void editDeveloperOfProjectMenu(BufferedReader reader) throws IOException {
        System.out.println("********************************************************************************************************");
        System.out.println("1- view all developers, 2 - add developer for project, 3 - delete developer from project "
                + "\nor: 9- previous menu");
        String choiceDev = reader.readLine();
        if (choiceDev.equals("9")) editProjectMenu(reader);
        if (choiceDev.equals("1")) {
            controller.getAllDevelopers().forEach(System.out::println);
            editDeveloperOfProjectMenu(reader);
        }

        if (choiceDev.equals("2")) {
            System.out.println("Enter projects`s ID  and developer`s ID through a separator/,for example: - 5/2"
                    + "\nor: 9- previous menu");
            String choiceDev2 = reader.readLine();
            if (choiceDev2.equals("9")) editDeveloperOfProjectMenu(reader);

            String array[] = choiceDev2.split("/");
            try {
                controller.addToProjectDeveloper(Integer.parseInt(array[0]), Integer.parseInt(array[1]));

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                LOGGER.error("Error input, ,for example: - 5/2");
                editDeveloperOfProjectMenu(reader);
                throw new RuntimeException();
            }
        }
        if (choiceDev.equals("3")) {
            System.out.println("Enter project`s ID  and developer`s ID through a separator/,for example: - 5/2"
                    + "\nor: 9- previous menu");
            String choicesDev3 = reader.readLine();

            if (choicesDev3.equals("9")) editDeveloperOfProjectMenu(reader);

            String array[] = choicesDev3.split("/");
            try {
                controller.deleteFromProjectDeveloper(Integer.parseInt(array[0]), Integer.parseInt(array[1]));

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                LOGGER.error("Error input, ,for example: - 5/2");
                editDeveloperOfProjectMenu(reader);
                throw new RuntimeException();
            }
        }
    }


    private  void getMenu( BufferedReader reader, EntityName entityName) throws IOException {
        try {
            int id = Integer.parseInt(reader.readLine());
            System.out.println(controller.getById(entityName, id));
        } catch (NumberFormatException e) {
            LOGGER.error("Invalid input!");
        }finally {
            choseMenu(entityName);
        }
    }

    private  void deleteMenu(BufferedReader reader, EntityName entityName) throws IOException {

        try {
            int id = Integer.parseInt(reader.readLine());
            controller.deleteById(entityName, id);
        } catch (NumberFormatException e){
            LOGGER.error("Invalid input!");
        } finally {
            choseMenu(entityName);
        }
    }

    private  void choseMenu(EntityName entityName) {
        if (entityName.equals(DEVELOPER)) developerMenu();
        if (entityName.equals(CUSTOMER)) customerMenu();
        if (entityName.equals(SKILL)) skillMenu();
        if (entityName.equals(COMPANY)) companyMenu();
        if (entityName.equals(PROJECT)) projectMenu();
    }
}

