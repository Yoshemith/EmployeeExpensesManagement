package com.encora.expenses;

import com.encora.expenses.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws JsonProcessingException, ClassNotFoundException, SQLException {
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
        EmployeesInMemoryImpl employees = new EmployeesInMemoryImpl();
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


        System.out.println(employee1);

        ObjectMapper objectMapper = new ObjectMapper();
        String employee1Json = objectMapper.writeValueAsString(employee1);
        System.out.println(employee1Json);

        StaffEmployee employeeFromJson = objectMapper.readValue(employee1Json, StaffEmployee.class);
        System.out.println(employeeFromJson);

        //Class.forName("com.mysql.cj.jdbc.Driver");
        Class.forName("org.h2.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:h2:./customerdata", "sa", "")){

            Statement statement = connection.createStatement();
            //statement.executeUpdate("CREATE TABLE customer (id INTEGER, name VARCHAR(255), age INTEGER, PRIMARY KEY (id) )");
            //statement.executeUpdate("INSERT INTO customer (id, name, age) VALUES (2, 'Andrea', '26')");
            ResultSet rs = statement.executeQuery("SELECT * FROM customer");

            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("name"));
            }
        }
        //connection.close();

    }

}
