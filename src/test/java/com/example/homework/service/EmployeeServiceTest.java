package com.example.homework.service;

import com.example.homework.hw26collection.exception.EmployeeAlreadyAddedException;
import com.example.homework.hw26collection.exception.EmployeeArrayIsFullException;
import com.example.homework.hw26collection.exception.InvalidInputException;
import com.example.homework.hw26collection.model.Employee;
import com.example.homework.hw26collection.service.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
    void whenFullThenTrow() {
        for (int i = 0; i < 12; i++) {
            char additionChar = (char) ('a' + i);
            employeeService.addEmployee(new Employee("name" + additionChar, "second name", 1, 1));
        }

        assertThrows(EmployeeArrayIsFullException.class,
                () -> employeeService.addEmployee(new Employee("qwerrty", "smit", 1, 1)));
    }

    @Test
    void whenNotUniqThenTrow() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.addEmployee(employee);
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.addEmployee(employee));
    }

    @Test
    void addPositive() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.addEmployee(employee);
        assertTrue(employeeService.getAll().contains(employee));
    }

    @Test
    void findDone() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.addEmployee(employee);
        Employee correct = employeeService.findEmployee("name", "last_name");
        assertNotNull(correct);
        assertEquals(employee, correct);
    }

    @Test
    void findNotDone() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.addEmployee(employee);

        assertThrows(InvalidInputException.class,
                () -> employeeService.findEmployee("not_name", "not_last_name"));
    }

    @Test
    void removeDone() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.addEmployee(employee);

        employeeService.removeEmployee("name", "last_name");

        assertFalse(employeeService.getAll().contains(employee));
    }

    @Test
    void removeNotDone() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.addEmployee(employee);

        assertThrows(InvalidInputException.class,
                () -> employeeService.removeEmployee("Not_that_name", "Not_that_last_name"));
    }
}
