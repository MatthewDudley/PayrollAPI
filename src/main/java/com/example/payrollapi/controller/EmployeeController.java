package com.example.payrollapi.controller;

import com.example.payrollapi.model.Employee;
import com.example.payrollapi.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    // Create Employee Repo object
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/test")
    public String sayHello() {
        return "I'm up and running waiting for some requests!";
    }

    // Return all Employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();

    }

    // Return specific Employee by ID
    // TODO: enhancements to error handling can be made
    @GetMapping("/employees/{id}")
    public Employee getEmployeeByID(@PathVariable(value = "id") Long empid) {

        return employeeRepository.findById(empid)
                .orElseThrow(() -> new IndexOutOfBoundsException());

    }

    // Add an Employee
    // Method takes in defined Employee data based on the object
    // @Valid is used to validate the data defined as not null is in fact not null
    @PostMapping("/employees")
    public Employee addEmployee(@Valid @RequestBody Employee employee) {

        return employeeRepository.save(employee);

    }

    // Edit an existing Employee
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long empid, @Valid @RequestBody Employee employeeDetails) {

        // find the specified employee
        Employee employee = employeeRepository.findById(empid)
                .orElseThrow(() -> new IndexOutOfBoundsException());

        // use setters to set the new employee information
        employee.setEmpname(employeeDetails.getEmpname());
        employee.setEmptitle(employeeDetails.getEmptitle());

        // save the new data
        Employee updatedEmp = employeeRepository.save(employee);

        return updatedEmp;

    }

}
