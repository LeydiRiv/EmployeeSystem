package com.example.EmployeeSystem.service;

import com.example.EmployeeSystem.model.CheckIn;
import com.example.EmployeeSystem.model.Employee;
import com.example.EmployeeSystem.repository.CheckinRepository;
import com.example.EmployeeSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
//import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CheckinRepository checkinRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CheckinRepository checkinRepository) {
        this.employeeRepository = employeeRepository;
        this.checkinRepository = checkinRepository;
    }


    // RegisterEmployee
    public Employee registerEmployee(Employee request) {
        CheckIn checkin = new CheckIn(); //Create a new object from check-in
        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());
        employee.setPosition(request.getPosition());
        employee.setEmail(request.getEmail());
        checkin.setEmployee(employee); // Associate the check-in to the Employee

        return employeeRepository.save(employee);
    }


//    //Check-in
//    public Employee checkIn(Long id){
//        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Error! The Employee doesn't exist."));
//        CheckIn checkin = new CheckIn(); //Create a new object from check-in
//        checkin.setStatus(true);
//        checkin.setCheckInTime(LocalDateTime.now()); //Save the dateTime
//        checkin.setEmployee(employee); // Associate the check-in to the Employee
//        employee.getCheckIns().add(checkin); // Save the check-in in the ArrayList
//        return employeeRepository.save(employee);
//
//    }


    public Employee updateEmployee(Long id, Map<String, Object> updates) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Error! The Employee doesn't exist."));
        // Update the specific data
        updates.forEach((op, value) -> {
            switch (op) {
                case "name":
                    employee.setName((String) value);
                    break;
                case "position":
                    employee.setPosition((String) value);
                    break;
                case "department":
                    employee.setDepartment((String) value);
                    break;
                case "email":
                    employee.setEmail((String) value);
                    break;
                default:
                    throw new RuntimeException("Error! Field " + op + " cannot be updated.");
            }
        });

        return employeeRepository.save(employee);
    }






//
//    //Check-out
//    public Employee checkOut(Long id) {
//        //If the Employee exist
//        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Error! The Employee doesn't exist."));
//        // Is the check-in do?
//        CheckIn checkin1 = null;
//        for (CheckIn checkIn : employee.getCheckIns()) {
//            if (checkIn.getStatus() != null && checkIn.getStatus()) {
//                checkin1 = checkIn;
//                break; // Leave into the loop
//            }
//        }
//        // If the check-in isn't true, send exception
//        if (checkin1 == null) {
//            throw new RuntimeException("Error! Mandatory to first check in.");
//        }
//
//        checkin1.setCheckOutTime(LocalDateTime.now());
//        checkin1.setStatus(false);
//
//        return employeeRepository.save(employee);
//
//    }






    //All the records
    public List<Employee> getAllRecords() {
        return employeeRepository.findAll();
    }

    //Delete a record
    public void deleteRecord(Long id) {
        employeeRepository.deleteById(id);
    }

}
