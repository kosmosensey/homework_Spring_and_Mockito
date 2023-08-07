package com.example.homework.hw26collection.service;

import com.example.homework.hw26collection.exception.EmployeeNotFoundException;
import com.example.homework.hw26collection.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public double getMaxSalByDepartment(int idDep) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == idDep)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .map(Employee::getSalary)
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public double getMinSalByDepartment(int idDep) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == idDep)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .map(Employee::getSalary)
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public double getSumOfSalariesByDepartment(int idDep) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == idDep)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public List<Employee> getAll(int idDep){
       return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == idDep)
                .collect(Collectors.toList());
    }
    public Map <Integer,List <Employee>> getAll(){
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
