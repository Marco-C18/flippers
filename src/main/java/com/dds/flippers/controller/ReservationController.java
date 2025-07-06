package com.dds.flippers.controller;

import com.dds.flippers.model.ClassModel;
import com.dds.flippers.model.PromoModel;
import com.dds.flippers.model.ReservationModel;
import com.dds.flippers.model.UserModel;
import com.dds.flippers.service.ClassService;
import com.dds.flippers.service.PromoService;
import com.dds.flippers.service.ReservationService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClassService classService;

    @Autowired
    private PromoService promoService;

    // Mostrar formulario de reserva
    @GetMapping("/reserva")
    public String showFormReservation(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        List<ClassModel> clasesDisponibles = classService.getAllClasses();
        List<PromoModel> promocionesActivas = promoService.getAllPromos();

        model.addAttribute("clases", clasesDisponibles);
        model.addAttribute("promociones", promocionesActivas);

        return "client/reserva";
    }

    // Realizar reserva
    @PostMapping("/reserva")
    public String createReservation(@RequestParam String fecha,
            @RequestParam String hora,
            @RequestParam Integer idClase,
            @RequestParam(required = false) Integer idPromocion,
            HttpSession session, Model model) {

        UserModel user = (UserModel) session.getAttribute("usuarioLogueado");
        if (user == null)
            return "redirect:/login";

        if (LocalDate.parse(fecha).isBefore(LocalDate.now())) {
            model.addAttribute("error", "No puedes seleccionar una fecha pasada.");
            model.addAttribute("clases", classService.getAllClasses());
            model.addAttribute("promociones", promoService.getAllPromos());
            return "client/reserva";
        }

        ClassModel claseSeleccionada = classService.getClassById(idClase);

        ReservationModel reserva = new ReservationModel();
        reserva.setDia(fecha);
        reserva.setHora(hora);
        reserva.setUsuario(user);
        reserva.setClase(claseSeleccionada);

        double precioBase = claseSeleccionada.getPriceClass();

        if (idPromocion != null) {
            PromoModel promo = promoService.getPromoById(idPromocion);
            double descuento = promo.getDiscountPercent();
            double precioConDescuento = precioBase - (precioBase * descuento / 100);
            reserva.setPromocion(promo);
            reserva.setMontoFinal(precioConDescuento);
        } else {
            reserva.setMontoFinal(precioBase);
        }

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

    // Eliminar Reservas
    @PostMapping("/reserva/eliminar")
    public String eliminarReserva(@RequestParam Integer id, HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("usuarioLogueado");
        if (user == null) {
            return "redirect:/login";
        }

        ReservationModel reserva = reservationService.getReservationById(id);
        if (reserva != null && reserva.getUsuario().getIdUsuario().equals(user.getIdUsuario())) {
            reservationService.deleteReservation(id);
        }

        return "redirect:/mis-reservas";
    }

    // GESTIONES DE ADMIN

    // Vista CRUD usuarios - VER
    @GetMapping("/admin/reservation")
    public String showAdminUserInteface(HttpSession session, Model model) {
        Boolean adminLogueado = (Boolean) session.getAttribute("adminLogueado");
        if (adminLogueado == null || !adminLogueado) {
            return "redirect:/admin/login";
        }

        model.addAttribute("reservationModels", reservationService.getAllReservation());
        return "admin/reservation";
    }

    // Vista CRUD usuarios - ELIMINAR
    @GetMapping("/admin/reservation/eliminar/{id}")
    public String deleteUser(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return "redirect:/admin/reservation";
    }
}