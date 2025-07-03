package com.dds.flippers.bff;

import com.dds.flippers.models.UserModel;
import com.dds.flippers.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminBFFController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestParam String clave, HttpSession session) {
        if ("admin".equals(clave)) {
            session.setAttribute("adminLogueado", true);
            return ResponseEntity.ok("Administrador autenticado.");
        } else {
            return ResponseEntity.status(401).body("Contraseña incorrecta.");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutAdmin(HttpSession session) {
        session.removeAttribute("adminLogueado");
        return ResponseEntity.ok("Sesión de administrador cerrada.");
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UserModel>> listarUsuarios() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UserModel> obtenerUsuario(@PathVariable Integer id) {
        UserModel usuario = userService.getUserById(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<String> editarUsuario(@PathVariable Integer id, @RequestBody UserModel usuarioEditado) {
        UserModel original = userService.getUserById(id);

        if (usuarioEditado.getContraseñaUsuario() == null || usuarioEditado.getContraseñaUsuario().isEmpty()) {
            usuarioEditado.setContraseñaUsuario(original.getContraseñaUsuario());
        }

        usuarioEditado.setIdUsuario(id);
        userService.saveUser(usuarioEditado);
        return ResponseEntity.ok("Usuario editado correctamente.");
    }
}
