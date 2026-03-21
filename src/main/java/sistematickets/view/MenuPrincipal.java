/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.util.Scanner;

/**
 *
 * @author josec
 */
public class MenuPrincipal {
 private Scanner sc = new Scanner(System.in);

    private RutaView rutaView           = new RutaView();
    private VehiculoView vehiculoView   = new VehiculoView();
    private ConductorView conductorView = new ConductorView();
    private PasajeroView pasajeroView   = new PasajeroView();
    private TicketView ticketView       = new TicketView();
            
    public void iniciar() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║  SISTEMA DE TICKETS INTERMUNICIPALES ║");
        System.out.println("║          TransCesar S.A.S.           ║");
        System.out.println("╚══════════════════════════════════════╝");

        int opcion;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gestion de Rutas");
            System.out.println("2. Gestion de Vehículos");
            System.out.println("3. Gestion de Conductores");
            System.out.println("4. Gestion de Pasajeros");
            System.out.println("5. Venta de Tickets");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> rutaView.menu();
                case 2 -> vehiculoView.menu();
                case 3 -> conductorView.menu();
                case 4 -> pasajeroView.menu();
                case 5 -> ticketView.menu();
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opcion no válida.");
            }
        } while (opcion != 0);
    }
}
