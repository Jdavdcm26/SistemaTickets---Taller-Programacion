/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.model;

import java.time.LocalDate;

/**
 *
 * @author jadis
 */
public class PasajeroAdultoMayor extends Pasajero{
    
    public PasajeroAdultoMayor(String cedula, String nombre, LocalDate fechaNacimiento) {
        super(cedula, nombre, fechaNacimiento);
    }

    @Override
    public double calcularDescuento() { return 0.30; }

    @Override
    public String getTipo() { return "Adulto Mayor"; }
    
}
