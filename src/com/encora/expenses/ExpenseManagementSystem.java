package com.encora.expenses;

import com.encora.expenses.domain.Employee;
import com.encora.expenses.domain.Employees;
import com.encora.expenses.domain.ExpenseClaim;
import com.encora.expenses.exceptions.EmployeeNotFoundException;
import com.encora.expenses.management.ExpenseManagementProcess;
import com.encora.expenses.management.ExpressExpenseManagementProcess;
import com.encora.expenses.management.RegularExpenseManagementProcess;
import com.encora.expenses.ui.UIFunctions;

import java.util.Scanner;

public class ExpenseManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UIFunctions uiFunctions = new UIFunctions();
        Employees employees = new Employees();

        ExpenseManagementProcess expressEMP = new ExpressExpenseManagementProcess();
        ExpenseManagementProcess regularEMP = new RegularExpenseManagementProcess();

        boolean readyToExit = false;

        while (!readyToExit) {

            System.out.println("Expense Management System");
            System.out.println("-------------------------");
            System.out.println("e - register new employee");
            System.out.println("c - register new claim");
            System.out.println("p - print all employees");
            System.out.println("a - approve claim");
            System.out.println("x - exit");

            String option = scanner.nextLine();

            switch (option) {
                case "e":
                    Employee e = uiFunctions.registerNewEmployee();
                    employees.addEmployee(e);
                    break;
                case "c":
                    ExpenseClaim claim = uiFunctions.registerNewExpenseClaim();
                    try {
                        employees.addExpenseClaim(claim);
                        expressEMP.registerExpenseClaim(claim);
                        int id = regularEMP.registerExpenseClaim(claim);
                        System.out.println("The claim has been registered with ID " + id);
                    } catch (EmployeeNotFoundException ex) {
                        System.out.println("There was no employee with ID " + claim.getEmployeeId());
                    }
                    break;
                case "p":
                    employees.printEmployees();
                    break;
                case "a":
                    System.out.println("Enter the claim Id:");
                    int claimId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter the employee Id:");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();

                    Employee foundEmployee = employees.findById(employeeId);

                    System.out.println("Enter r for regular, or e for express:");
                    String claimType = scanner.nextLine();

                    ExpenseManagementProcess requestedProcess;

                    if (claimType.equals("r")) {
                        requestedProcess = regularEMP;
                    } else {
                        requestedProcess = expressEMP;
                    }
                    boolean result = requestedProcess.approvedClaim(claimId, foundEmployee);
                    System.out.println("The result was " + result);

                    break;
                case "x":
                    readyToExit = true;
                    break;
                default:
                    System.out.println("Option not valid!!!");
            }
        }

    }

}
