/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.model;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author jadis
 */
public abstract class Pasajero extends Persona {
    protected LocalDate fechaNacimiento;
    
    public Pasajero(String cedula, String nombre, LocalDate fechaNacimiento) {
        super(cedula, nombre);
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public abstract double calcularDescuento();
    public abstract String getTipo();
    
    @Override
    public String imprimirDetalle() {
        return "Cédula          : " + cedula + "\n" +
               "Nombre          : " + nombre + "\n" +
               "Fecha nacimiento: " + fechaNacimiento + "\n" +
               "Tipo            : " + getTipo() + "\n" +
               "Descuento       : " + (int)(calcularDescuento() * 100) + "%";
    }

    @Override
    public String toFile() {
        return cedula + ";" + nombre + ";" + fechaNacimiento + ";" + getTipo();
    }
}
