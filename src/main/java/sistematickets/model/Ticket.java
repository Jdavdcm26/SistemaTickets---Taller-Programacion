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
public class Ticket implements InterfazImprimible, Calculable{
    
    private String numeroTicket;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDate fechaCompra;
    private double valorFinal;
    
    public Ticket(String numeroTicket, Pasajero pasajero, Vehiculo vehiculo, 
                  LocalDate fechaCompra, double valorFinal) {
        this.numeroTicket = numeroTicket;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCompra = fechaCompra;
        this.valorFinal = valorFinal;
    }

    public String getNumeroTicket() { return numeroTicket; }
    public Pasajero getPasajero() { return pasajero; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public LocalDate getFechaCompra() { return fechaCompra; }
    public double getValorFinal() { return valorFinal; }

    // calcularTotal() ya no se llama en el constructor
    // se llama desde TicketService antes de crear el ticket
    @Override
    public double calcularTotal() {
        double descuento = pasajero.calcularDescuento();
        return vehiculo.getTarifaBase() * (1 - descuento);
    }

    @Override
    public String imprimirDetalle() {
        return "N° Ticket    : " + numeroTicket +
               "\nPasajero     : " + pasajero.getNombre() +
               "\nCédula       : " + pasajero.getCedula() +
               "\nTipo pasajero: " + pasajero.getTipo() +
               "\nVehículo     : " + vehiculo.getTipo() + " - " + vehiculo.getPlaca() +
               "\nOrigen       : " + vehiculo.getRuta().getCiuOrigen() +
               "\nDestino      : " + vehiculo.getRuta().getCiuDestino() +
               "\nFecha compra : " + fechaCompra +
               "\nDescuento    : " + (int)(pasajero.calcularDescuento() * 100) + "%" +
               "\nValor final  : $" + valorFinal;
    }

    @Override
    public String toFile() {
        return numeroTicket + ";" +
               pasajero.getCedula() + ";" +
               vehiculo.getPlaca() + ";" +
               fechaCompra + ";" +
               valorFinal;
    }
}
