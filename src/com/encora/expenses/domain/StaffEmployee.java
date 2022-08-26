package com.encora.expenses.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

//This annotation will tell jackson that, when we're working with json
// and we try to create an instance of one of those classes from some json
// if there are properties in the json that don't exist in the class, just ignore them.
@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffEmployee extends Employee implements Comparable<Employee> {

    private String username;
    private String password;

    public StaffEmployee(Employee e) {
        super(e.getId(), e.getTitle(), e.getFirstName(), e.getSurname(), e.getJobTitle(), e.getDepartment());
    }

    public StaffEmployee(){
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StaffEmployee that = (StaffEmployee) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password);
    }
}
