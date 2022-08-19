package com.encora.expenses;

import com.encora.expenses.domain.Employee;
import com.encora.expenses.domain.Employees;
import com.encora.expenses.domain.ExpenseClaim;
import com.encora.expenses.domain.ExpenseItem;

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
        Employees employees = new Employees(15);
        employees.addEmployee(employee1);
        employees.addEmployee(employee2);
        employees.addEmployee( new Employee(3, "Mr.", "Gabriel", "Cameron", "Director", "Finance"));

        employees.printEmployees();
        Employee foundEmployee = employees.findBySurname("Cameron");
        System.out.println("Found " + foundEmployee.getMailingName());

        Employee foundEmployee2 = employees.findBySurname("Cyan");
        System.out.println("Didn't find " + (foundEmployee2 == null));

        ExpenseClaim expenseClaim = new ExpenseClaim(1, 1, "18/08/2022", 20);
        expenseClaim.setApproved(false);
        expenseClaim.setPaid(true);

        System.out.println(expenseClaim.isPaid());

        ExpenseItem expenseItem = new ExpenseItem(1, 1, "Travel", "Just something", 254.4);

        System.out.println(expenseItem.getDescription());

    }

}
