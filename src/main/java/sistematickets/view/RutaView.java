/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.util.Scanner;
import sistematickets.service.RutaService;

/**
 *
 * @author josec
 */
public class RutaView {
    private Scanner sc = new Scanner(System.in);
    private RutaService rutaService = new RutaService();
 
    public void menu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ RUTAS =====");
            System.out.println("1. Agregar ruta");
            System.out.println("2. Listar todas");
            System.out.println("3. Buscar por código");
            System.out.println("4. Actualizar ruta");
            System.out.println("5. Eliminar ruta");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> listarTodas();
                case 3 -> buscarPorCodigo();
                case 4 -> actualizar();
                case 5 -> eliminar();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
