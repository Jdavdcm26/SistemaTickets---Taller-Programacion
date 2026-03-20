/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.util.ArrayList;
import java.util.Scanner;
import sistematickets.model.Ticket;
import sistematickets.model.Vehiculo;
import sistematickets.service.TicketService;
import sistematickets.service.VehiculoService;

/**
 *
 * @author josec
 */
public class TicketView {
  private Scanner sc = new Scanner(System.in);
    private TicketService ticketService   = new TicketService();
    private VehiculoService vehiculoService = new VehiculoService();

    public void menu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ TICKETS =====");
            System.out.println("1. Vender ticket");
            System.out.println("2. Listar todos");
            System.out.println("3. Reportes y estadísticas");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> vender();
                case 2 -> listarTodos();
                case 3 -> menuReportes();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    } 
    private void vender() {
    System.out.println("\n--- Vender Ticket ---");
    System.out.print("Código de ruta: ");
    String codRuta = sc.nextLine();

    // CAMBIO: ArrayList con tipo genérico
    ArrayList<Vehiculo> vehiculos = vehiculoService.buscarPorRuta(codRuta);
    if (vehiculos.isEmpty()) {
        System.out.println("No hay vehículos disponibles para esa ruta.");
        return;
    }

    System.out.println("\nVehículos disponibles:");
    for (int i = 0; i < vehiculos.size(); i++) {
        // CAMBIO: imprimirDetalle() en vez de toString()
        System.out.println((i + 1) + ". " + vehiculos.get(i).imprimirDetalle());
        System.out.println("----------------------");
    }

    System.out.print("\nCédula del pasajero: ");
    String cedula = sc.nextLine();

    System.out.print("Placa del vehículo : ");
    String placa = sc.nextLine();

    String resultado = ticketService.venderTicket(cedula, placa);
    System.out.println(resultado);
}
   private void listarTodos() {
        System.out.println("\n--- Listado de Tickets ---");
        ArrayList<Ticket> lista = ticketService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay tickets registrados.");
        } else {
            for (Ticket t : lista) {
                System.out.println(t.imprimirDetalle());
                System.out.println("----------------------");
            }
        }
    }     
}
