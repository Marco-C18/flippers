package com.dds.flippers.controllers;

import com.dds.flippers.models.ClassModel;
import com.dds.flippers.models.ReservationModel;
import com.dds.flippers.models.UserModel;
import com.dds.flippers.services.ClassService;
import com.dds.flippers.services.ReservationService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClassService classService;

    // Mostrar formulario de reserva
    @GetMapping("/reserva")
    public String showFormReservation(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        List<ClassModel> clasesDisponibles = classService.getAllClasses();
        model.addAttribute("clases", clasesDisponibles);

        return "client/reserva";
    }

    // Realizar reserva
    @PostMapping("/reserva")
    public String createReservation(@RequestParam String fecha,
            @RequestParam String hora,
            @RequestParam Integer idClase,
            HttpSession session, Model model) {

        UserModel user = (UserModel) session.getAttribute("usuarioLogueado");
        if (user == null)
            return "redirect:/login";

        ClassModel claseSeleccionada = classService.getClassById(idClase);

        ReservationModel reserva = new ReservationModel();
        reserva.setDia(fecha);
        reserva.setHora(hora);
        reserva.setUsuario(user);
        reserva.setClase(claseSeleccionada);

        reservationService.saveReservation(reserva);

        return "redirect:/mis-reservas";
    }

    // Vista de reservas del usuario
    @GetMapping("/mis-reservas")
    public String showReservationInterface(Model model, HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("usuarioLogueado");
        if (user == null)
            return "redirect:/login";

        List<ReservationModel> reservas = reservationService.getReservationByUser(user);
        model.addAttribute("reservas", reservas);

        return "client/mis-reservas";
    }
}