package com.uca.capas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.capas.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
