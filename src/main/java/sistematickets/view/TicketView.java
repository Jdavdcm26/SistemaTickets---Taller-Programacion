/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.util.Scanner;
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
}
