package com.encora.expenses.utilities;

import com.encora.expenses.domain.Employees;

import java.time.LocalDate;

public class ExpenseAnalysisImpl implements ExpenseAnalysis{

    private Employees employees;

    public ExpenseAnalysisImpl(Employees employees){
        this.employees = employees;
    }

    @Override
    public void printOutstandingClaims() {
        employees.getEmployeeList().stream().forEach(
                employee -> {
                    employee.getClaims().values().stream()
                            .filter(claim -> !claim.isApproved() )
                            .forEach( claim -> System.out.println(claim));
                }
        );
    }

    @Override
    public void printPaidClaims(LocalDate from, LocalDate to) {
        employees.getEmployeeList().stream().forEach(
                employee -> {
                    employee.getClaims().values().stream()
                            .filter(claim -> claim.isPaid() )
                            .filter(claim -> claim.getDateOfClaim().isAfter(from) && claim.getDateOfClaim().isBefore(to))
                            .forEach( claim -> System.out.println(claim));
                }
        );
    }

    @Override
    public void printClaimsOverAmount(Double amount) {
        employees.getEmployeeList().stream().forEach(
                employee -> {
                    employee.getClaims().values().stream()
                            .filter(claim -> claim.getTotalAmount() >= amount )
                            .forEach( claim -> System.out.println(claim));
                }
        );
    }
}
