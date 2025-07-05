package com.dds.flippers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dds.flippers.designpatterns.acl.UserNotifierPort;
import com.dds.flippers.designpatterns.bridge.EmailNotifier;
import com.dds.flippers.designpatterns.bridge.Notifier;
import com.dds.flippers.designpatterns.bridge.SmsNotifier;
import com.dds.flippers.model.UserModel;
import com.dds.flippers.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserNotifierPort userNotifierPort;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    // Implementacion de patrón Bride
    public void saveUser(UserModel userModel) {
        userRepository.save(userModel);
        userNotifierPort.notifyUser(userModel);
    }

    public UserModel getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido"));
    }

    public void deleteUserById(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public boolean authenticate(String nombre, String contrasena) {
        UserModel user = userRepository.findByNombreUsuario(nombre);
        return user != null && user.getContraseñaUsuario().equals(contrasena);
    }

    public UserModel getUserByName(String nombre) {
        return userRepository.findByNombreUsuario(nombre);
    }

    public boolean existsByNombreUsuario(String nombre) {
        return userRepository.findByNombreUsuario(nombre) != null;
    }

    public boolean existsByEmailUsuario(String email) {
        return userRepository.findByEmailUsuario(email) != null;
    }

    public boolean existsByTelefonoUsuario(String telefono) {
        return userRepository.findByTelefonoUsuario(telefono) != null;
    }

}
