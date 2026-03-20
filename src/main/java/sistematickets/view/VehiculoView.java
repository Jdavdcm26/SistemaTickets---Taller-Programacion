/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.util.Scanner;
import sistematickets.service.RutaService;
import sistematickets.service.VehiculoService;

/**
 *
 * @author josec
 */
public class VehiculoView {
     private Scanner sc = new Scanner(System.in);
    private VehiculoService vehiculoService = new VehiculoService();
    private RutaService rutaService         = new RutaService();

    public void menu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ VEHÍCULOS =====");
            System.out.println("1. Registrar vehículo");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por placa");
            System.out.println("4. Asignar conductor");
            System.out.println("5. Actualizar vehículo");
            System.out.println("6. Eliminar vehículo");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> listarTodos();
                case 3 -> buscarPorPlaca();
                case 4 -> asignarConductor();
                case 5 -> actualizar();
                case 6 -> eliminar();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
