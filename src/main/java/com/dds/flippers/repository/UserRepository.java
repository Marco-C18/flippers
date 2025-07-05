package com.dds.flippers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dds.flippers.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByNombreUsuario(String nombreUsuario);

    UserModel findByEmailUsuario(String emailUsuario);

    UserModel findByTelefonoUsuario(String telefonoUsuario);

}
