package projectmanagersystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;


/**
 * Created by Вадим on 18.02.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        first:
        while (true) {
            System.out.println("Hello, select the action");
            System.out.println("1- Operations  with Developers, 2- Operation with Companies, 3- Operations with Projects, 4- Operations with Customers, 5 - Operations with Skills, 0- Exit");
            String choice1 = bufferedReader.readLine();

            if (choice1.equals("0")) break;

            if (choice1.equals("1")) {
                second:
                while (true) {
                    System.out.println("Please, make a choice:\n" + "1- view all developers, 2-find by ID, 3-delete by ID, 4-add new developer,"
                            + " 5- edit developer`s data\n"
                            + "9- main menu; 0- exit");
                    String choice4 = bufferedReader.readLine();
                    if (choice4.equals("0")) break first;
                    if (choice4.equals("9")) continue first;
                    if (choice4.equals("1")) {
                        controller.getDeveloperDao().getAll().forEach(System.out::println);
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                    }
                    if (choice4.equals("2")) {
                        System.out.println("enter ID developer");
                        String idDeveloper = bufferedReader.readLine();
                        int id = Integer.parseInt(idDeveloper);
                        if (idDeveloper.isEmpty()) {
                            System.out.println("Invalid Entry!");
                            continue;
                        }
                        System.out.println(controller.getDeveloperDao().getById(id));
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    }
                    if (choice4.equals("3")) {
                        System.out.println("enter ID developer");
                        String idDeveloper = bufferedReader.readLine();
                        int id = Integer.parseInt(idDeveloper);
                        if (idDeveloper.isEmpty()) {
                            System.out.println("Invalid Entry!");
                            continue;
                        }
                        controller.getDeveloperDao().deleteById(id);
                    }
                    if (choice4.equals("4")) {
                        while (true) {
                            System.out.println("To add developer enter his name, surname, address, email, join date, salary through a separator/:" +
                                    ", for example: Vasya/Petrov/Kyev/vasya@ukr.net/2015-05-26/5200" +
                                    "\nor: 9- previous menu, 0- exit");
                            String choice = bufferedReader.readLine();
                            if (choice.equals("0")) break first;
                            if (choice.equals("9")) continue second;
                            String s[] = choice.split("/");
                            if (s.length != 6) {
                                System.out.println("Invalid Entry!");
                                continue;
                            }
                            Developer developer = new Developer(s[0], s[1], s[2], s[3], LocalDate.parse((s[4])), Integer.parseInt(s[5]));
                            controller.getDeveloperDao().addToDB(developer);
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Continue to add to the developers?");
                        }
                    }
                    if (choice4.equals("5")) {
                        while (true) {
                            System.out.println("1 - edit address, 2 -  edit email, 3 - edit join date, 4 - edit salary, " +
                                    "5 - edit skill"
                                    + "\nor: 9- previous menu, 0- exit");
                            String choice = bufferedReader.readLine();
                            if (choice.equals("0")) break first;
                            if (choice.equals("9")) continue second;
                            if (choice.equals("1")) {
                                System.out.println("Enter developer`s ID  and address through a separator/,for example: - 5/Kyiv"
                                        + "\nor: 9- previous menu, 0- exit");
                                String choice11 = bufferedReader.readLine();
                                String str11[];
                                if (choice11.equals("0")) break first;
                                if (choice11.equals("9")) continue second;
                                else {
                                    str11 = choice11.split("/");
                                    controller.getDeveloperDao().updateAddress(Integer.parseInt(str11[0]), str11[1]);
                                }
                            }
                            if (choice.equals("2")) {
                                System.out.println("Enter developer`s ID  and email through a separator/,for example: - 5/vasya@ukr.net"
                                        + "\nor: 9- previous menu, 0- exit");
                                String choice12 = bufferedReader.readLine();
                                String str12[];
                                if (choice12.equals("0")) break first;
                                if (choice12.equals("9")) continue second;
                                else {
                                    str12 = choice12.split("/");
                                    controller.getDeveloperDao().updateEmail(Integer.parseInt(str12[0]), str12[1]);
                                }
                            }
                            if (choice.equals("3")) {
                                System.out.println("Enter developer`s ID  and join date through a separator/,for example: - 5/2017-01-01"
                                        + "\nor: 9- previous menu, 0- exit");
                                String choice13 = bufferedReader.readLine();
                                String str13[];
                                if (choice13.equals("0")) break first;
                                if (choice13.equals("9")) continue second;
                                else {
                                    str13 = choice13.split("/");
                                    controller.getDeveloperDao().updateDateJoin(Integer.parseInt(str13[0]), LocalDate.parse(str13[1]));
                                }
                            }

                            if (choice.equals("4")) {
                                System.out.println("Enter developer`s ID  and salary through a separator/,for example: - 5/12500"
                                        + "\nor: 9- previous menu, 0- exit");
                                String choice14 = bufferedReader.readLine();
                                String str14[];
                                if (choice14.equals("0")) break first;
                                if (choice14.equals("9")) continue second;
                                else {
                                    str14 = choice14.split("/");
                                    controller.getDeveloperDao().updateSalary(Integer.parseInt(str14[0]), Integer.parseInt(str14[1]));
                                }
                            }
                            if (choice.equals("5")) {

                                System.out.println("1- view all skills, 2 - add skill for developer");
                                String choice3 = bufferedReader.readLine();
                                if (choice3.equals("1")) {
                                    controller.getSkillDao().getAll().forEach(System.out::println);
                                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                }
                                if (choice3.equals("2")) {
                                    System.out.println("Enter developer`s ID  and skill`s ID through a separator/,for example: - 5/2"
                                            + "\nor: 9- previous menu, 0- exit");
                                    String choice2 = bufferedReader.readLine();
                                    String str[];
                                    if (choice2.equals("0")) break first;
                                    if (choice2.equals("9")) continue second;
                                    else {
                                        str = choice2.split("/");
                                        controller.getDeveloperDao().addSkill(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
                                    }
                                }
                            }
                            continue second;
                        }
                    }
                }
            }

            if (choice1.equals("5")) {
                second:
                while (true) {
                    System.out.println("Please, make a choice:\n" + "1-view all skills, 2- find by ID, 3- delete by ID, 4-add new skill,"
                            + " 5-edit skill\n"
                            + "9- main menu; 0- exit");
                    String choice = bufferedReader.readLine();
                    if (choice.equals("0")) break first;
                    if (choice.equals("9")) continue first;
                    if (choice.equals("1")) {
                        controller.getSkillDao().getAll().forEach(System.out::println);
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                    }
                    if (choice.equals("2")) {
                        System.out.println("enter ID  of skill");
                        String idSkill = bufferedReader.readLine();
                        int id = Integer.parseInt(idSkill);
                        if (idSkill.isEmpty()) {
                            System.out.println("Invalid Entry!");
                            continue;
                        }
                        System.out.println(controller.getSkillDao().getById(id));
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    }
                    if (choice.equals("3")) {
                        System.out.println("enter ID of skill");
                        String idSkill = bufferedReader.readLine();
                        int id = Integer.parseInt(idSkill);
                        if (idSkill.isEmpty()) {
                            System.out.println("Invalid Entry!");
                            continue;
                        }
                        controller.getSkillDao().deleteById(id);
                    }
                    if (choice.equals("4")) {
                        while (true) {
                            System.out.println("To add a new skill enter the name`s skill"
                                    +  "\nor: 9- previous menu, 0- exit");
                            String choice14 = bufferedReader.readLine();
                            if (choice14.equals("0")) break first;
                            if (choice14.equals("9")) continue second;
                            Skill skill = new Skill(choice14);
                            controller.getSkillDao().addToDB(skill);
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Continue to add skills?");
                        }
                    }
                    if (choice.equals("5")) {

                            System.out.println("Enter skill`s ID  and skill`s new name through a separator/,for example: - 5/JavaNew"
                                    + "\nor: 9- previous menu, 0- exit");
                            String choice15 = bufferedReader.readLine();
                            String str15[];
                            if (choice15.equals("0")) break first;
                            if (choice15.equals("9")) continue second;
                            else {
                                str15 = choice15.split("/");
                                controller.getSkillDao().updateSkill(Integer.parseInt(str15[0]), str15[1]);
                            }

                    }
                }
            }


            System.out.println("Invalid Entry");
        }
        System.out.println("Good Bay!");
        bufferedReader.close();
    }
}

