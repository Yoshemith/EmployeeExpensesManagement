package com.encora.expenses;

import com.encora.expenses.domain.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Employee employee1 = new Employee(); //Instantiation of the com.encora.expenses.domain.Employee Class
        employee1.setId(1);
        employee1.setTitle("Mr.");
        employee1.setFirstName("Yoshe");
        employee1.setSurname("Castellanos");

        System.out.println(employee1.getMailingName());
        System.out.println(employee1.getMailingName(true));
        System.out.println(employee1.getMailingName(false));

        Employee employee2 = new Employee(1, "Manager");
        employee2.setTitle("Doctor");
        employee2.setFirstName("Dennis");
        employee2.setSurname("Yellow");

        //Where the employees will be stored
        Employees employees = new Employees();
        employees.addEmployee(employee1);
        employees.addEmployee(employee2);
        employees.addEmployee( new Employee(3, "Mr.", "Gabriel", "Cameron", "Director", Department.MARKETING));

        employees.printEmployees();
        Employee foundEmployee = employees.findBySurname("Cameron");
        System.out.println("Found " + foundEmployee.getMailingName());

        Employee foundEmployee2 = employees.findBySurname("Cyan");
        System.out.println("Didn't find " + (foundEmployee2 == null));

        ExpenseClaim expenseClaim = new ExpenseClaim(1, 1, LocalDate.now());
        expenseClaim.setApproved(false);
        expenseClaim.setPaid(true);

        System.out.println(expenseClaim.isPaid());

        ExpenseItem expenseItem = new ExpenseItem(1, 1, ExpenseType.TRAVEL, "Just something", 254.4);
        System.out.println(expenseItem.getDescription());

        System.out.println(employee1.toString());
        System.out.println(employee1);

        Employee employee3 = new Employee();
        employee3.setId(1);
        employee3.setTitle("Mr.");
        employee3.setFirstName("Yoshe");
        employee3.setSurname("Castellanos");

        System.out.println(employee1.equals(employee3));
        System.out.println(employee1 == employee1);
        System.out.println(employee1.getClass());

    }

}
