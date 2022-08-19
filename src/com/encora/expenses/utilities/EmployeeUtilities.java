package com.encora.expenses.utilities;

import com.encora.expenses.domain.Employee;
import com.encora.expenses.domain.Employees;

public class EmployeeUtilities {

    public boolean employeeExists(Employees employees, Employee employee) {
        return employees.findBySurname(employee.getSurname()) != null;
    }

}
