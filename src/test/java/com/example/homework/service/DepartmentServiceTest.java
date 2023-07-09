package com.example.homework.service;

import com.example.homework.hw26collection.model.Employee;
import com.example.homework.hw26collection.service.DepartmentService;
import com.example.homework.hw26collection.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    public static final Collection<Employee> EMPLOYEES = Arrays.asList(
            new Employee("Антон", "Дроздов",1 ,10000.0),
            new Employee("Георгий", "Иванов", 1, 15000.0),
            new Employee("Василий", "Антонов", 2, 20000.0),
            new Employee("Дмитрий", "Васильев",2 , 40000.0),
            new Employee("Владимир", "Петров", 3, 50000.0)
    );
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init() {
        when(employeeService.getAll()).thenReturn(EMPLOYEES);
    }
    @Test
    void getSumOfSalariesByDepartment() {
        double actual = departmentService.getSumOfSalariesByDepartment(1);
        assertEquals(25000.0, actual);
    }

    @Test
    void getMaxSalaryDepartment() {
        double actual = departmentService.getMaxSalByDepartment(2);
        assertEquals(40000.0, actual);
    }

    @Test
    void getMinSalaryDepartment() {
        double actual = departmentService.getMinSalByDepartment(1);
        assertEquals(10000.0, actual);
    }

    @Test
    void getDepartmentAll() {
        List<Employee> actual = departmentService.getAll(3);
        Collection<Employee> expected = Collections.singletonList(
                new Employee("Владимир", "Петров", 3,50000));
        assertIterableEquals(expected, actual);
    }

    @Test
    void getAll() {
        Map<Integer, List<Employee>> actual = departmentService.getAll();

        Employee petr = new Employee("Владимир", "Петров",3, 50000);
        assertTrue(actual.get(3).contains(petr));
        assertFalse(actual.get(2).contains(petr));

        assertEquals(3, actual.keySet().size());
    }

    @Test
    void getAllByWrongDepartment() {
        List<Employee> all = departmentService.getAll(4);
        assertTrue(all.isEmpty());
    }
}