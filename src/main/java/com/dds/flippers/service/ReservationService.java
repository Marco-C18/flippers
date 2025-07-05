package com.dds.flippers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dds.flippers.model.ClassModel;
import com.dds.flippers.model.ReservationModel;
import com.dds.flippers.model.UserModel;
import com.dds.flippers.repository.ClassRepository;
import com.dds.flippers.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public void saveReservation(ReservationModel reserva) {
        reservationRepository.save(reserva);
    }

    public boolean createReservation(String fecha, String hora, Integer idClase, UserModel usuario) {
        ClassModel clase = classRepository.findById(idClase).orElse(null);
        if (clase == null)
            return false;

        ReservationModel reserva = new ReservationModel();
        reserva.setDia(fecha);
        reserva.setHora(hora);
        reserva.setUsuario(usuario);
        reserva.setClase(clase);

        reservationRepository.save(reserva);
        return true;
    }

    public List<ReservationModel> getReservationByUser(UserModel usuario) {
        return reservationRepository.findByUsuario(usuario);
    }

    public ReservationModel getReservationById(Integer id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }
}
