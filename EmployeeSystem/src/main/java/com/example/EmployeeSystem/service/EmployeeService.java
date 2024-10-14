package com.example.EmployeeSystem.service;

import com.example.EmployeeSystem.model.CheckIn;
import com.example.EmployeeSystem.model.Employee;
import com.example.EmployeeSystem.repository.CheckinRepository;
import com.example.EmployeeSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    // Check-in
    public Employee checkIn(Employee request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());
        employee.setPosition(request.getPosition());
        employee.setEmail(request.getEmail());

        CheckIn checkin = new CheckIn(); //Create a new object from check-in
        checkin.setCheckInTime(LocalDateTime.now()); //Save the dateTime
        //Add status
        checkin.setStatus(true);
        //Refer Check-in to Employee
        checkin.setEmployee(employee); // Associate the check-in to the Employee
        employee.getCheckIns().add(checkin); // Save the check-in in the ArrayList

        return employeeRepository.save(employee);
    }

    //Check-out
    public Employee checkOut(Long id) {
        //If the Employee exist
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Error! The Employee doesn't exist."));
        // Is the check-in do?
        CheckIn checkin1 = null;
        for (CheckIn checkIn : employee.getCheckIns()) {
            if (checkIn.getStatus() != null && checkIn.getStatus()) {
                checkin1 = checkIn;
                break; // Leave into the loop
            }
        }
        // If the check-in isn't true, send exception
        if (checkin1 == null) {
            throw new RuntimeException("Error! Mandatory to first check in.");
        }

        checkin1.setCheckOutTime(LocalDateTime.now());
        checkin1.setStatus(false);

        return employeeRepository.save(employee);

    }

    //All the records
    public List<Employee> getAllRecords() {
        return employeeRepository.findAll();
    }

    //Delete a record
    public void deleteRecord(Long id) {
        employeeRepository.deleteById(id);
    }

}
