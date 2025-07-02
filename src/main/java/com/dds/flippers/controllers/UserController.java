package com.dds.flippers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.dds.flippers.models.UserModel;
import com.dds.flippers.services.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    // Vista para registrar usuario
    @GetMapping("/register")
    public String showFormRegister() {
        return "client/register";
    }

    // Crear nuevo usuario
    @PostMapping("/register")
    public String createUser(@ModelAttribute UserModel userModel) {
        userService.saveUser(userModel);
        return "redirect:/";
    }

    // Vista para iniciar sesión
    @GetMapping("/login")
    public String showFormLogin() {
        return "client/login";
    }

    // Iniciar sesión
    @PostMapping("/login")
    public String loginUser(@RequestParam String nombreUsuario,
            @RequestParam String contraseñaUsuario,
            HttpSession session,
            Model model) {
        if (userService.authenticate(nombreUsuario, contraseñaUsuario)) {
            session.setAttribute("usuarioLogueado", userService.getUserByName(nombreUsuario));
            return "redirect:/";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "client/login";
        }
    }

    // Cerrar sesión
    @GetMapping("/logout")
    public String logOutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // GESTIONES DE ADMIN

    // Vista CRUD usuarios - VER
    @GetMapping("/admin/usuario")
    public String showAdminUserInteface(Model model) {
        model.addAttribute("usuarioModels", userService.getAllUsers());
        return "admin/usuario";
    }

    // Vista CRUD usuarios - ELIMINAR
    @GetMapping("/admin/usuario/eliminar/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin/usuario";
    }

    // Vista CRUD usuarios - VER EDITAR
    @GetMapping("/admin/usuario/editar/{id}")
    public String showAdminUserEditInterface(@PathVariable Integer id, Model model) {
        UserModel usuario = userService.getUserById(id);
        model.addAttribute("usuario", usuario);
        return "/admin/editar-usuario";
    }

    // Vista CRUD usuarios - EJECUTAR EDITAR
    @PostMapping("/admin/usuario/editar/{id}")
    public String editUser(@PathVariable Integer id, @ModelAttribute UserModel usuarioEditado) {

        UserModel usuarioOriginal = userService.getUserById(id);

        if (usuarioEditado.getContraseñaUsuario() == null || usuarioEditado.getContraseñaUsuario().isEmpty()) {
            usuarioEditado.setContraseñaUsuario(usuarioOriginal.getContraseñaUsuario());
        }

        usuarioEditado.setIdUsuario(id);
        userService.saveUser(usuarioEditado);
        return "redirect:/admin/usuario";
    }

}
