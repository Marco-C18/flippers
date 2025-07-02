package com.dds.flippers.repositories;

import com.dds.flippers.models.ReservationModel;
import com.dds.flippers.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationModel, Integer> {
    List<ReservationModel> findByUsuario(UserModel usuario);
}
