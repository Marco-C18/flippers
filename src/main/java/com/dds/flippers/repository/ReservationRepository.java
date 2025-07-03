package com.dds.flippers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dds.flippers.model.ReservationModel;
import com.dds.flippers.model.UserModel;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationModel, Integer> {
    List<ReservationModel> findByUsuario(UserModel usuario);
}
