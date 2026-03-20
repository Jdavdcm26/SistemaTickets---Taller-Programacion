/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.model;

/**
 *
 * @author josec
 */
public class Buseta extends Vehiculo {

    public Buseta(String placa, Ruta ruta) {
        super(placa, ruta);
        this.tarifaBase=8000;
        this.capacidadMaxima=19;
    }

    @Override
    public String getTipo() {
        return "Buseta"; 
    }

    @Override
    public String imprimirDetalle() {
        return "Tipo : " + getTipo()
                + "\nPlaca : " + placa + "\n" +
               ruta.imprimirDetalle() + "\n" +
               "Tarifa base :" + tarifaBase + "\n" +
               "Pasajeros : " + pasajerosActuales + " personas\n" +
               "Capacidad maxima " + capacidadMaxima + " personas" + // NUEVO
               "\nCupos disponibles : " + getCuposDisponibles() + " personas"+
               "\nDisponible :" + (disponible ? "Sí" : "No") +
               "\nConductor : " + (conductor != null ? conductor.getNombre() : "Sin conductor asignado"); // NUEVO
    }

    @Override
    public String toFile() {
        return placa + ";" + ruta.getCodRuta() + ";" + tarifaBase + ";" + pasajerosActuales + ";" + capacidadMaxima + ";" + disponible + ";" + (conductor != null ? conductor.getCedula() : "null"); // NUEVO
    }
    
}
