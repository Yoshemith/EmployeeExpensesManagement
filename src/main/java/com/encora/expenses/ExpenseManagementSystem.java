package com.encora.expenses;

import com.encora.expenses.domain.*;
import com.encora.expenses.exceptions.EmployeeNotFoundException;
import com.encora.expenses.management.ExpenseManagementProcess;
import com.encora.expenses.management.ExpressExpenseManagementProcess;
import com.encora.expenses.management.RegularExpenseManagementProcess;
import com.encora.expenses.ui.UIFunctions;
import com.encora.expenses.utilities.ExpenseAnalysis;
import com.encora.expenses.utilities.ExpenseAnalysisImpl;
import com.encora.expenses.utilities.ExpenseAnalysisTempImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ExpenseManagementSystem {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Scanner scanner = new Scanner(System.in);
        UIFunctions uiFunctions = new UIFunctions();
        Employees employees = new EmployeesDatabaseImpl();

        ExpenseManagementProcess expressEMP = new ExpressExpenseManagementProcess();
        ExpenseManagementProcess regularEMP = new RegularExpenseManagementProcess();

        boolean readyToExit = false;

        while (!readyToExit) {

            System.out.println("Expense Management System");
            System.out.println("-------------------------");
            System.out.println("e - register new employee");
            System.out.println("c - register new claim");
            System.out.println("p - print all employees");
            System.out.println("f - export the data to a file");
            System.out.println("a - approve claim");
            System.out.println("r1 - outstanding expense claims");
            System.out.println("r2 - paid expense claims");
            System.out.println("r3 - expense claims above specified amount");
            System.out.println("x - exit");

            String option = scanner.nextLine();

            ExpenseAnalysis expenseAnalysis = new ExpenseAnalysisImpl(employees);

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
                case "f":
                    List<Employee> employeeList = employees.getEmployeeList();
                    Path report = Paths.get(System.getProperty("user.home") + File.separator + "expense-report.txt");
                    String lineTerminator = System.getProperty("line.separator");

                    Collections.sort(employeeList);
                    for (Employee emp: employeeList) {
                        Files.writeString(report, emp.toString() + lineTerminator, StandardOpenOption.CREATE , StandardOpenOption.APPEND);
                        for (ExpenseClaim empClaim: emp.getClaims().values()) {
                            Files.writeString(report, empClaim.toString() + lineTerminator, StandardOpenOption.CREATE , StandardOpenOption.APPEND);
                            List<String> claimData = empClaim.getExpenseItems().stream().map(ei -> ei.toString()).toList();
                            Files.write(report, claimData, StandardOpenOption.APPEND);
                            Files.writeString(report, "Total value of claim: " + empClaim.getTotalAmount() + lineTerminator, StandardOpenOption.APPEND);
                        }
                    }
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
                case "r1":
                    expenseAnalysis.printOutstandingClaims();
                    break;
                case "r2":
                    System.out.println("Entre date to: ");
                    String dateFrom = scanner.nextLine();

                    System.out.println("Entre date to: ");
                    String dateTo = scanner.nextLine();

                    expenseAnalysis.printPaidClaims(LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
                    break;
                case "r3":
                    System.out.println("Entre the amount: ");
                    Double amount = scanner.nextDouble();
                    scanner.nextLine();

                    expenseAnalysis.printClaimsOverAmount(amount);
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
