package com.dds.flippers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dds.flippers.models.PromoModel;

public interface PromoRepository extends JpaRepository<PromoModel, Integer> {

}
