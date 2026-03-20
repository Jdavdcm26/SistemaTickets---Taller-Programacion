/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.util.ArrayList;
import java.util.Scanner;
import sistematickets.model.Pasajero;
import sistematickets.service.PasajeroService;

/**
 *
 * @author josec
 */
public class PasajeroView {
   private Scanner sc = new Scanner(System.in);
    private PasajeroService pasajeroService = new PasajeroService();

    public void menu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ PASAJEROS =====");
            System.out.println("1. Registrar pasajero");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por cédula");
            System.out.println("4. Actualizar pasajero");
            System.out.println("5. Eliminar pasajero");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> listarTodos();
                case 3 -> buscarPorCedula();
                case 4 -> actualizarPasajero();
                case 5 -> eliminar();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    } 
    private void registrar() {
        System.out.println("\n--- Registrar Pasajero ---");
        System.out.print("Cédula                    : "); String cedula = sc.nextLine();
        System.out.print("Nombre                    : "); String nombre = sc.nextLine();
        System.out.print("Fecha nacimiento (yyyy-MM-dd): "); 
        LocalDate fechaNacimiento = LocalDate.parse(sc.nextLine());
        System.out.println("Tipo (Regular / Estudiante): ");
        System.out.print("Tipo                      : "); String tipo = sc.nextLine();

        if (pasajeroService.agregar(cedula, nombre, fechaNacimiento, tipo)) {
            System.out.println("Pasajero registrado correctamente.");
        } else {
            System.out.println("Error: ya existe un pasajero o conductor con esa cédula.");
        }
    }

    private void listarTodos() {
        System.out.println("\n--- Listado de Pasajeros ---");
        ArrayList<Pasajero> lista = pasajeroService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
        } else {
            for (Pasajero p : lista) {
                System.out.println(p.imprimirDetalle());
                System.out.println("----------------------");
            }
        }
    }
}
