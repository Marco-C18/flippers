package com.dds.flippers.designpatterns.bff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dds.flippers.model.UserModel;
import com.dds.flippers.service.UserService;

@RestController
@RequestMapping("/api/client")
public class ClientBFFController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel userModel) {
        userService.saveUser(userModel);
        return ResponseEntity.ok("Usuario registrado correctamente.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String nombreUsuario,
            @RequestParam String contraseñaUsuario) {
        boolean autenticado = userService.authenticate(nombreUsuario, contraseñaUsuario);
        if (autenticado) {
            return ResponseEntity.ok("Inicio de sesión exitoso.");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas.");
        }
    }
}
