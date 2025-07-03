package com.dds.flippers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dds.flippers.model.ClassModel;

public interface ClassRepository extends JpaRepository<ClassModel, Integer> {

}
