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
public class Ticket {
    private String numeroTicket;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDate fechaCompra;
    private double valorFinal;
}
