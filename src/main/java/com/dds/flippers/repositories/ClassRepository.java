package com.dds.flippers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dds.flippers.models.ClassModel;

public interface ClassRepository extends JpaRepository<ClassModel, Integer> {

}
