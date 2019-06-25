package com.uca.capas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.capas.domain.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Integer>{

}
