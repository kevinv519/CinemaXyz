package com.uca.capas.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.uca.capas.domain.Department;
import com.uca.capas.repository.DepartmentRepository;

@Service
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
	@Transactional
	public void save(Department department) throws DataAccessException {
		departmentRepo.save(department);
	}

}
