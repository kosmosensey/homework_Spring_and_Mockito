package com.example.hw26collection.service;

import com.example.hw26collection.exception.EmployeeNotFoundException;
import com.example.hw26collection.model.Employee;
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

    public Employee getMaxSalByDepartment(Integer idDep) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == idDep)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public Employee getMinSalByDepartment(Integer idDep) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == idDep)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
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
