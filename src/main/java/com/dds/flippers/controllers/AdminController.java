package com.dds.flippers.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    private static final String CLAVE_ADMIN = "admin";

    // Mostrar formulario login de admin
    @GetMapping("/admin/login")
    public String showAdminLogin() {
        return "admin/admin-login";
    }

    // Procesar login de admin
    @PostMapping("/admin/login")
    public String processAdminLogin(@RequestParam String clave, HttpSession session, Model model) {
        if (CLAVE_ADMIN.equals(clave)) {
            session.setAttribute("adminLogueado", true);
            return "redirect:/admin/menu";
        } else {
            model.addAttribute("error", "Contraseña incorrecta");
            return "admin/admin-login";
        }
    }

    // Mostrar menú si está logueado
    @GetMapping("/admin/menu")
    public String showAdminInterface(HttpSession session) {
        Boolean adminLogueado = (Boolean) session.getAttribute("adminLogueado");
        if (adminLogueado != null && adminLogueado) {
            return "admin/admin-menu";
        }
        return "redirect:/admin/login";
    }

    // Cerrar sesión admin
    @GetMapping("/admin/logout")
    public String logOutAdmin(HttpSession session) {
        session.removeAttribute("adminLogueado");
        return "redirect:/admin/login";
    }

}
