/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.util.Scanner;
import sistematickets.service.ConductorService;

/**
 *
 * @author josec
 */
public class ConductorView {
  private Scanner sc = new Scanner(System.in);
    private ConductorService conductorService = new ConductorService();

    public void menu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ CONDUCTORES =====");
            System.out.println("1. Registrar conductor");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por cédula");
            System.out.println("4. Actualizar conductor");
            System.out.println("5. Eliminar conductor");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> listarTodos();
                case 3 -> buscarPorCedula();
                case 4 -> actualizar();
                case 5 -> eliminar();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }  
}
