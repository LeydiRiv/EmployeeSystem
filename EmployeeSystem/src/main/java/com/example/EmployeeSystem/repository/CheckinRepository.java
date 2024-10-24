package com.example.EmployeeSystem.repository;

import com.example.EmployeeSystem.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckinRepository extends JpaRepository<CheckIn, Long> {
}