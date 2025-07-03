package com.dds.flippers.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reserva")
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;

    @ManyToOne
    @JoinColumn(name = "idClase") 
    private ClassModel clase;

    private String dia;
    private String hora;

    @ManyToOne
    @JoinColumn(name = "idUsuario") 
    private UserModel usuario;

    
    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public ClassModel getClase() {
        return clase;
    }

    public void setClase(ClassModel clase) {
        this.clase = clase;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }
}
