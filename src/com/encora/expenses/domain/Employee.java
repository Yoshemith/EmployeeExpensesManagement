package com.encora.expenses.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Employee {

    //Properties
    private int id;
    private String title;
    private String firstName;
    private String surname;
    private String jobTitle;
    private Department department;
    private ArrayList<ExpenseClaim> claims = new ArrayList<>();

    //Constructor that allows to use every get or set method in here
    //No need to create it manually though, java does this for you.
    public Employee() {

    }

    //Constructor that allows to create a com.encora.expenses.domain.Employee when you instantiate the class
    //This way you do it directly with only id and jobTitle (in this case)
    public Employee(int id, String jobTitle) {
        this.id = id;
        this.jobTitle = jobTitle;

    }

    //Constructor that allows you to create an employee when you instantiate the class
    //This way the class would appear to be read-only
    //By using this constructor the set Methods are not necessary only the get methods.
    public Employee(int id, String title, String firstName, String surname, String jobTitle, Department department) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
        this.jobTitle = jobTitle;
        this.department = department;
    }

    //Any other method for this class
    public String getMailingName() {
        return title + " " + firstName + " " + surname;
    }

    public String getMailingName(boolean firstInitialOnly) {
        if (firstInitialOnly) {
            return title + " " + firstName.substring(0, 1) + " " + surname;
        } else{
            return title + " " + surname;
        }
    }

    //Setters and Getters Methods
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() < 2) {
            System.out.println("Error - first name must be at least 2 characters");
        } else {
            this.firstName = firstName;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public ArrayList<ExpenseClaim> getClaims() {
        return claims;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", department=" + department +
                ", claims=" + claims +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(title, employee.title) && Objects.equals(firstName, employee.firstName) && Objects.equals(surname, employee.surname) && Objects.equals(jobTitle, employee.jobTitle) && department == employee.department && Objects.equals(claims, employee.claims);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, firstName, surname, jobTitle, department, claims);
    }
}
