package com.controllerprotection.controllerprotection.repositories;

import com.controllerprotection.controllerprotection.entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
