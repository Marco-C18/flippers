package com.dds.flippers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dds.flippers.model.PromoModel;

public interface PromoRepository extends JpaRepository<PromoModel, Integer> {

}
