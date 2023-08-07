package com.example.homework.hw26collection.controler;

import com.example.homework.hw26collection.model.Employee;
import com.example.homework.hw26collection.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public double getMaxSalByDepartment(@RequestParam("departmentId") Integer idDep) {
        return departmentService.getMaxSalByDepartment(idDep);
    }

    @GetMapping(path = "/min-salary")
    public double getMinSalByDepartment(@RequestParam("departmentId") Integer idDep) {
        return departmentService.getMinSalByDepartment(idDep);
    }

    @GetMapping(value = "all", params = "departmentId")
    public List<Employee> getAll(@RequestParam("departmentId") int idDep) {
        return departmentService.getAll((idDep));
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }
}
