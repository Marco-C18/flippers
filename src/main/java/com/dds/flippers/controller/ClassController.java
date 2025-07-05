package com.dds.flippers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.dds.flippers.model.ClassModel;
import com.dds.flippers.service.ClassService;
import com.dds.flippers.service.PromoService;
import com.dds.flippers.view.ViewRoutes;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

// @RestController
@Controller
@RequestMapping
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private PromoService promoService;

    // Vista HOME
    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("classModels", classService.getAllClasses());
        model.addAttribute("promos", promoService.getAllPromos());
        return ViewRoutes.HOME;
    }

    // Vista NOSOTROS
    @GetMapping("/nosotros")
    public String showNosotros() {
        return ViewRoutes.ABOUT_US;
    }

    // GESTIONES DE ADMIN

    // Vista CRUD clases - VER
    @GetMapping("/admin/clases")
    public String showAdminClassInterface(HttpSession session, Model model) {
        Boolean adminLogueado = (Boolean) session.getAttribute("adminLogueado");
        if (adminLogueado == null || !adminLogueado) {
            return "redirect:/admin/login";
        }

        model.addAttribute("clases", classService.getAllClasses());
        model.addAttribute("nuevaClase", new ClassModel());
        return "admin/clases";
    }

    // Vista CRUD clases - CREAR
    @PostMapping("/admin/clases/crear")
    public String createClass(@ModelAttribute("nuevaClase") ClassModel clase) {
        classService.saveClass(clase);
        return "redirect:/admin/clases";
    }

    // Vista CRUD clases - VER EDITAR
    @GetMapping("/admin/clases/editar/{id}")
    public String showAdminClassEditInterface(@PathVariable Integer id, Model model) {
        model.addAttribute("clase", classService.getClassById(id));
        return "admin/editar-clase";
    }

    // Vista CRUD clases - EJECUTAR EDITAR
    @PostMapping("/admin/clases/editar/{id}")
    public String editClass(@PathVariable Integer id, @ModelAttribute ClassModel clase) {
        clase.setIdClass(id);
        // classService.saveClass(clase);
        classService.saveClassWithBackup(clase);
        return "redirect:/admin/clases";
    }

    // Vista CRUD clases - ELIMINAR
    @GetMapping("/admin/clases/eliminar/{id}")
    public String deleteClass(@PathVariable Integer id) {
        classService.deleteClass(id);
        return "redirect:/admin/clases";
    }

    // Vista CRUD clases - RESTAURAR
    @GetMapping("/admin/clases/restaurar/{id}")
    public String restaurarClase(@PathVariable Integer id) {
        classService.restoreLastVersion(id);
        return "redirect:/admin/clases";
    }

}
