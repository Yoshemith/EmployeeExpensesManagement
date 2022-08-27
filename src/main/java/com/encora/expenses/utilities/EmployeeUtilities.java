package com.encora.expenses.utilities;

import com.encora.expenses.domain.Employee;
import com.encora.expenses.domain.EmployeesInMemoryImpl;
import com.encora.expenses.exceptions.InvalidEmployeeIdException;
import com.encora.expenses.exceptions.NameTooShortException;

public class EmployeeUtilities {

    public boolean employeeExists(EmployeesInMemoryImpl employees, Employee employee) {
        return employees.findBySurname(employee.getSurname()) != null;
    }

    public Integer validateEmployeeId(String inputId) throws InvalidEmployeeIdException {
        try {
            Integer id = Integer.valueOf(inputId);
            return id;
        } catch(NumberFormatException e) {
            throw new InvalidEmployeeIdException();
        }
    }

    public void validateEmployeeName(String firstName, String  surname) throws NameTooShortException {
        Integer lengths = firstName.length() + surname.length();
        if (lengths < 6) {
            throw new NameTooShortException();
        }
    }

}
