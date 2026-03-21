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
    
}
