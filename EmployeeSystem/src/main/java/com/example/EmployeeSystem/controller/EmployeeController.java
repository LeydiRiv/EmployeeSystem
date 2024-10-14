package com.example.EmployeeSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import com.example.EmployeeSystem.model.Employee;
import com.example.EmployeeSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//This can handle HTTP requests and return responses in JSON format
@RestController
@RequestMapping("/api/v1/people")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/checkin")
    public ResponseEntity<Employee> checkIn(@RequestBody Employee request) {
        Employee savedEmployee = employeeService.checkIn(request);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/checkout/{id}") //Updates an existing resource
    public ResponseEntity<Optional<Employee>> checkOut(@PathVariable Long id) {
        Optional<Employee> person = Optional.ofNullable(employeeService.checkOut(id));
        return ResponseEntity.ok(person); //Represent the http response
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
}