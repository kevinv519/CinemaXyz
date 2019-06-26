package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Department;

public interface DepartmentService {

	List<Department> getDeparmentList() throws DataAccessException;
	
	Department getDepartment(Integer code) throws DataAccessException;
	
	void save(Department department) throws DataAccessException;

}
