package com.example.EmployeeSystem.controller;

import com.example.EmployeeSystem.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.EmployeeSystem.model.Employee;
import com.example.EmployeeSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;

//This can handle HTTP requests and return responses in JSON format
@RestController
@RequestMapping("/api/v1/people")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    @Autowired
//    private EmployeeRepository employeeRepository;


    //register employee
    @PostMapping("/register")
    public ResponseEntity<Employee> registerEmployee(@RequestBody Employee request) {
        Employee savedEmployee = employeeService.registerEmployee(request);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee savedEmployee = employeeService.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok(savedEmployee);
    }

    //URL: http://localhost:8080/api/v1/people
    @GetMapping //Retrieves data for a resource
    public ResponseEntity<Iterable<Employee>> getRecords() {
        return ResponseEntity.ok(employeeService.getAllRecords());
    }

    //URL: http://localhost:8080/api/v1/people/{id}
    @DeleteMapping("/{id}") //Deletes a resource
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        employeeService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }



//    @GetMapping
//    public Page<Employee> getAllEmployees(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        return employeeRepository.findAll(PageRequest.of(page, size));
//    }


}