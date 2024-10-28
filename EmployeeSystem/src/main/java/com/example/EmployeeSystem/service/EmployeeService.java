package com.example.EmployeeSystem.service;

import com.example.EmployeeSystem.model.CheckIn;
import com.example.EmployeeSystem.model.Employee;
import com.example.EmployeeSystem.repository.CheckinRepository;
import com.example.EmployeeSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
//import org.springframework.util.ReflectionUtils;

import java.util.List;

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


    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        //Check if the employee exist
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error! The Employee doesn't exist."));

        // update
        employee.setName(updatedEmployee.getName());
        employee.setPosition(updatedEmployee.getPosition());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setEmail(updatedEmployee.getEmail());

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
