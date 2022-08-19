package com.encora.expenses.domain;

public class ExpenseItem{
    private int id;
    private int claimId;
    private String expenseType;
    private String description;
    private double amount;

    public ExpenseItem(int id, int claimId, String expenseType, String description, double amount) {
        this.id = id;
        this.claimId = claimId;
        this.expenseType = expenseType;
        this.description = description;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getClaimId() {
        return claimId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
