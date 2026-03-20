/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.util.ArrayList;
import java.util.Scanner;
import sistematickets.model.Bus;
import sistematickets.model.Buseta;
import sistematickets.model.MicroBus;
import sistematickets.model.Ruta;
import sistematickets.model.Vehiculo;
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
    private void registrar() {
        System.out.println("\n--- Registrar Vehículo ---");
        System.out.print("Placa: "); String placa = sc.nextLine();

        System.out.println("Tipo: 1. Bus  2. Buseta  3. MicroBus");
        System.out.print("Tipo: "); int tipo = Integer.parseInt(sc.nextLine());

        System.out.print("Código de ruta: "); String codRuta = sc.nextLine();
        Ruta ruta = rutaService.buscarPorCodigo(codRuta);
        if (ruta == null) {
            System.out.println("No existe una ruta con ese código.");
            return;
        }

        Vehiculo v = switch (tipo) {
            case 1 -> new Bus(placa, ruta);
            case 2 -> new Buseta(placa, ruta);
            case 3 -> new MicroBus(placa, ruta);
            default -> null;
        };

        if (v == null) {
            System.out.println("Tipo no válido.");
            return;
        }

        if (vehiculoService.agregar(v)) {
            System.out.println("Vehículo registrado correctamente.");
        } else {
            System.out.println("Error: ya existe un vehículo con esa placa.");
        }
    }

    private void listarTodos() {
        System.out.println("\n--- Listado de Vehículos ---");
        ArrayList<Vehiculo> lista = vehiculoService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            for (Vehiculo v : lista) {
                System.out.println(v.imprimirDetalle());
                System.out.println("----------------------");
            }
        }
    }
    private void buscarPorPlaca() {
        System.out.println("\n--- Buscar Vehículo ---");
        System.out.print("Placa: ");
        Vehiculo v = vehiculoService.buscarPorPlaca(sc.nextLine());
        if (v != null) {
            System.out.println(v.imprimirDetalle());
        } else {
            System.out.println("No se encontró ningún vehículo con esa placa.");
        }
    }

    private void asignarConductor() {
        System.out.println("\n--- Asignar Conductor ---");
        System.out.print("Placa del vehículo  : "); String placa  = sc.nextLine();
        System.out.print("Cédula del conductor: "); String cedula = sc.nextLine();

        if (vehiculoService.asignarConductor(placa, cedula)) {
            System.out.println("Conductor asignado correctamente.");
        } else {
            System.out.println("Error: vehículo o conductor no encontrado, o conductor sin licencia.");
        }
    }
    private void eliminar() {
        System.out.println("\n--- Eliminar Vehículo ---");
        System.out.print("Placa: ");
        if (vehiculoService.eliminar(sc.nextLine())) {
            System.out.println("Vehículo eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún vehículo con esa placa.");
        }
    }
    private void actualizar() {
    System.out.println("\n--- Actualizar Vehículo ---");
    System.out.print("Placa del vehículo a actualizar: ");
    String placa = sc.nextLine();

    Vehiculo v = vehiculoService.buscarPorPlaca(placa);
    if (v == null) {
        System.out.println("No se encontró ningún vehículo con esa placa.");
        return;
    }

    System.out.println("Deja en blanco para conservar el valor actual.");
    System.out.print("Nuevo código de ruta (" + v.getRuta().getCodRuta() + "): ");
    String codRuta = sc.nextLine();

    String conductorActual = v.getConductor() != null ? v.getConductor().getCedula() : "Sin asignar";
    System.out.print("Nueva cédula conductor (" + conductorActual + "): ");
    String cedula = sc.nextLine();

    if (vehiculoService.actualizar(placa, codRuta, cedula)) {
        System.out.println("Vehículo actualizado correctamente.");
    } else {
        System.out.println("Error al actualizar.");
    }
}
}
