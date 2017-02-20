package projectmanagersystem;

import java.time.LocalDate;

/**
 * Created by Вадим on 18.02.2017.
 */
public class Developer {
   private int id;
   private String name;
   private String surname;
   private String address;
   private String email;
   private LocalDate date;
   private int salary;

    public Developer(String name, String surname, String address, String email, LocalDate date, int salary) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.date = date;
        this.salary = salary;
    }

    public Developer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        if (id != developer.id) return false;
        if (salary != developer.salary) return false;
        if (!name.equals(developer.name)) return false;
        if (!surname.equals(developer.surname)) return false;
        if (address != null ? !address.equals(developer.address) : developer.address != null) return false;
        if (email != null ? !email.equals(developer.email) : developer.email != null) return false;
        return date != null ? date.equals(developer.date) : developer.date == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + salary;
        return result;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                ", salary=" + salary +
                '}';
    }
}
