package com.uca.capas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;

import com.uca.capas.domain.Department;
import com.uca.capas.repository.DepartmentRepository;

public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentRepository departmentRepo;
	
	@Override
	public List<Department> getDeparmentList() throws DataAccessException {
		return departmentRepo.findAll(Sort.by("name"));
	}

	@Override
	public Department getDepartment(Integer code) throws DataAccessException {
		Optional<Department> department = departmentRepo.findById(code);
		return department.isPresent()? department.get() : null;
	}

	@Override
	public void save(Department department) throws DataAccessException {
		departmentRepo.save(department);
	}

}
