/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    private void buscarPorCedula() {
        System.out.println("\n--- Buscar Pasajero ---");
        System.out.print("Cédula: ");
        String cedula = sc.nextLine();
        Pasajero p = pasajeroService.buscarPorCedula(cedula);
        if (p != null) {
            System.out.println(p.imprimirDetalle());
        } else {
            System.out.println("No se encontró ningún pasajero con esa cédula.");
        }
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Pasajero ---");
        System.out.print("Cédula: ");
        String cedula = sc.nextLine();
        if (pasajeroService.eliminar(cedula)) {
            System.out.println("Pasajero eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún pasajero con esa cédula.");
        }
    }
    public void actualizarPasajero() {
    try (Scanner scanner = new Scanner(System.in)) {
        System.out.println("=== Actualizar Pasajero ===");
        
        System.out.print("Ingrese la cédula del pasajero a actualizar: ");
        String cedula = scanner.nextLine().trim();
        if (cedula.isEmpty()) {
            System.out.println("Cédula no puede estar vacía.");
            return;
        }
        
        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("Nombre no puede estar vacío.");
            return;
        }
        
        System.out.print("Ingrese la nueva fecha de nacimiento (formato: yyyy-MM-dd): ");
        String fechaStr = scanner.nextLine().trim();
        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (fechaNacimiento.isAfter(LocalDate.now())) {
                System.out.println("La fecha de nacimiento no puede ser futura.");
                return;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Use yyyy-MM-dd.");
            return;
        }
        
        System.out.print("Ingrese el nuevo tipo (estudiante/regular): ");
        String tipo = scanner.nextLine().trim().toLowerCase();
        if (!tipo.equals("estudiante") && !tipo.equals("regular")) {
            System.out.println("Tipo inválido. Debe ser 'estudiante' o 'regular'.");
            return;
        }
        
        boolean exito = pasajeroService.actualizar(cedula, nombre, fechaNacimiento, tipo);
        if (exito) {
            System.out.println("Pasajero actualizado exitosamente.");
        } else {
            System.out.println("Error: No se pudo actualizar el pasajero. Verifique la cédula o intente nuevamente.");
        }
    }
}
}
