package com.dds.flippers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dds.flippers.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByNombreUsuario(String nombreUsuario);

}
