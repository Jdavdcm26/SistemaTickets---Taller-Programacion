/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import sistematickets.model.Reserva;
import sistematickets.service.ReservaService;

/**
 *
 * @author josec
 */
public class ReservaView {
  private Scanner sc = new Scanner(System.in);
    private ReservaService reservaService = new ReservaService();
    
    private void crear() throws IOException {
        System.out.println("\n--- Crear Reserva ---");
        System.out.print("Cedula del pasajero       : "); String cedula = sc.nextLine();
        System.out.print("Placa del vehículo        : "); String placa = sc.nextLine();
        System.out.print("Fecha de viaje (yyyy-MM-dd): ");
        LocalDate fechaViaje;
        try {
            fechaViaje = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Fecha no valida.");
            return;
        }
        System.out.println(reservaService.crear(cedula, placa, fechaViaje));
    }
     private void cancelar() throws IOException {
        System.out.println("\n--- Cancelar Reserva ---");
        System.out.print("Codigo de la reserva: ");
        String codigo = sc.nextLine();
        System.out.println(reservaService.cancelar(codigo));
    }
private void listarActivas() throws IOException {
        System.out.println("\n--- Reservas Activas ---");
        ArrayList<Reserva> lista = reservaService.listarActivas();
        if (lista.isEmpty()) {
            System.out.println("No hay reservas activas.");
        } else {
            for (Reserva r : lista) {
                System.out.println(r.imprimirDetalle());
                System.out.println("----------------------");
            }
        }
    }
}
