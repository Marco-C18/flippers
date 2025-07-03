package com.dds.flippers.controller;

import com.dds.flippers.model.PromoModel;
import com.dds.flippers.service.PromoService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/promos")
public class PromoController {

    @Autowired
    private PromoService promoService;

    @GetMapping
    public String mostrarPromociones(HttpSession session, Model model) {
        Boolean adminLogueado = (Boolean) session.getAttribute("adminLogueado");
        if (adminLogueado == null || !adminLogueado) {
            return "redirect:/admin/login";
        }

        model.addAttribute("promos", promoService.getAllPromos());
        model.addAttribute("clases", promoService.getAllClasses());
        model.addAttribute("nuevaPromo", new PromoModel());
        return "/admin/promos";
    }

    @PostMapping("/crear")
    public String crearPromo(@ModelAttribute("nuevaPromo") PromoModel promo,
            @RequestParam("classModel.idClass") Integer idClass) {
        promoService.createPromo(promo, idClass);
        return "redirect:/admin/promos";
    }

    @GetMapping("/editar/{id}")
    public String editarPromo(@PathVariable Integer id, Model model) {
        model.addAttribute("promo", promoService.getPromoById(id));
        model.addAttribute("clases", promoService.getAllClasses());
        return "/admin/editar-promo";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Integer id, @ModelAttribute PromoModel promo) {
        promo.setIdPromo(id);
        promoService.saveEditedPromo(promo);
        return "redirect:/admin/promos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPromo(@PathVariable Integer id) {
        promoService.deletePromo(id);
        return "redirect:/admin/promos";
    }
}
