/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author jadis
 */
public class Reserva implements InterfazImprimible{
    
    public enum Estado { ACTIVA, CONVERTIDA, CANCELADA }

    private String codigo;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDateTime fechaCreacion;
    private LocalDate fechaViaje;
    private Estado estado;
    
    public Reserva(String codigo, Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaViaje) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaViaje = fechaViaje;
        this.estado = Estado.ACTIVA;
    }

    
    public Reserva(String codigo, Pasajero pasajero, Vehiculo vehiculo,
        LocalDateTime fechaCreacion, LocalDate fechaViaje, Estado estado) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCreacion = fechaCreacion;
        this.fechaViaje = fechaViaje;
        this.estado = estado;
    }
    
    public String getCodigo() { 
        return codigo; 
    }
    
    public Pasajero getPasajero() { 
        return pasajero; 
    }
    
    public Vehiculo getVehiculo() { 
        return vehiculo; 
    }
    
    public LocalDateTime getFechaCreacion() { 
        return fechaCreacion; 
    }
    
    public LocalDate getFechaViaje() { 
        return fechaViaje; 
    }
    
    public Estado getEstado() { 
        return estado; 
    }
    
    public void setEstado(Estado estado) { 
        this.estado = estado; 
    }
    
    public boolean estaVencida() {
        return estado == Estado.ACTIVA &&
               LocalDateTime.now().isAfter(fechaCreacion.plusHours(24));
    }
    
      @Override
    public String imprimirDetalle() {
        return "Codigo reserva  : " + codigo +
               "\nPasajero        : " + pasajero.getNombre() +
               "\nCedula          : " + pasajero.getCedula() +
               "\nVehiculo        : " + vehiculo.getTipo() + " - " + vehiculo.getPlaca() +
               "\nRuta            : " + vehiculo.getRuta().getCiuOrigen() + " - " + vehiculo.getRuta().getCiuDestino() +
               "\nFecha creacion  : " + fechaCreacion +
               "\nFecha viaje     : " + fechaViaje +
               "\nEstado          : " + estado;
    }
    
     @Override
    public String toFile() {
        return codigo + ";" +
               pasajero.getCedula() + ";" +
               vehiculo.getPlaca() + ";" +
               fechaCreacion + ";" +
               fechaViaje + ";" +
               estado;
    }  
    
}
