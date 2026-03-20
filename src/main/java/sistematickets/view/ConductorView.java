/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.util.ArrayList;
import java.util.Scanner;
import sistematickets.model.Conductor;
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
    private void registrar() {
        System.out.println("\n--- Registrar Conductor ---");
        System.out.print("Cédula          : "); String cedula = sc.nextLine();
        System.out.print("Nombre          : "); String nombre = sc.nextLine();
        System.out.print("N° Licencia     : "); String licencia = sc.nextLine();
        System.out.println("Categoría (B1, B2, C1, C2): ");
        System.out.print("Categoría       : "); String categoria = sc.nextLine();

        Conductor c = new Conductor(cedula, nombre, licencia, categoria);
        if (conductorService.agregar(c)) {
            System.out.println("Conductor registrado correctamente.");
        } else {
            System.out.println("Error: ya existe un conductor con esa cédula o licencia inválida.");
        }
    }

    private void listarTodos() {
        System.out.println("\n--- Listado de Conductores ---");
        ArrayList<Conductor> lista = conductorService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay conductores registrados.");
        } else {
            for (Conductor c : lista) {
                System.out.println(c.imprimirDetalle());
                System.out.println("----------------------");
            }
        }
    }
        private void buscarPorCedula() {
        System.out.println("\n--- Buscar Conductor ---");
        System.out.print("Cédula: ");
        String cedula = sc.nextLine();
        Conductor c = conductorService.buscarPorCedula(cedula);
        if (c != null) {
            System.out.println(c.imprimirDetalle());
        } else {
            System.out.println("No se encontró ningún conductor con esa cédula.");
        }
    }

    private void actualizar() {
        System.out.println("\n--- Actualizar Conductor ---");
        System.out.print("Cédula del conductor a actualizar: ");
        String cedula = sc.nextLine();

        Conductor c = conductorService.buscarPorCedula(cedula);
        if (c == null) {
            System.out.println("No se encontró ningún conductor con esa cédula.");
            return;
        }

        System.out.println("Deja en blanco para conservar el valor actual.");
        System.out.print("Nuevo nombre (" + c.getNombre() + "): ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo N° licencia (" + c.getNumeroLicencia() + "): ");
        String licencia = sc.nextLine();
        System.out.print("Nueva categoría (" + c.getCategoriaLicencia() + "): ");
        String categoria = sc.nextLine();

        if (!nombre.isBlank())    c.setNombre(nombre);
        if (!licencia.isBlank())  c.setNumeroLicencia(licencia);
        if (!categoria.isBlank()) c.setCategoriaLicencia(categoria);

        if (conductorService.actualizar(c)) {
            System.out.println("Conductor actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar.");
        }
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Conductor ---");
        System.out.print("Cédula: ");
        String cedula = sc.nextLine();
        if (conductorService.eliminar(cedula)) {
            System.out.println("Conductor eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún conductor con esa cédula.");
        }
    }
}
