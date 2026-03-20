/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.view;

import java.util.ArrayList;
import java.util.Scanner;
import sistematickets.model.Ruta;
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
    private void agregar() {
        System.out.println("\n--- Agregar Ruta ---");
        System.out.print("Código      : "); String cod  = sc.nextLine();
        System.out.print("Origen      : "); String ori  = sc.nextLine();
        System.out.print("Destino     : "); String des  = sc.nextLine();
        System.out.print("Kilómetros  : "); int km       = Integer.parseInt(sc.nextLine());
        System.out.print("Tiempo(min) : "); int tiem     = Integer.parseInt(sc.nextLine());

        Ruta r = new Ruta(cod, ori, des, km, tiem);
        if (rutaService.agregar(r)) {
            System.out.println("Ruta agregada correctamente.");
        } else {
            System.out.println("Error: ya existe una ruta con ese código.");
        }
    }
      private void listarTodas() {
        System.out.println("\n--- Listado de Rutas ---");
        ArrayList<Ruta> rutas = rutaService.listarTodas();
        if (rutas.isEmpty()) {
            System.out.println("No hay rutas registradas.");
        } else {
            for (Ruta r : rutas) {
                System.out.println(r.imprimirDetalle());
                System.out.println("----------------------");
            }
        }
    }
      private void buscarPorCodigo() {
        System.out.println("\n--- Buscar Ruta ---");
        System.out.print("Código: ");
        String cod = sc.nextLine();
        Ruta r = rutaService.buscarPorCodigo(cod);
        if (r != null) {
            System.out.println(r.imprimirDetalle());
        } else {
            System.out.println("No se encontró ninguna ruta con ese código.");
        }
    }
        private void actualizar() {
            System.out.println("\n--- Actualizar Ruta ---");
            System.out.print("Código de la ruta a actualizar: ");
            String cod = sc.nextLine();

            Ruta r = rutaService.buscarPorCodigo(cod);
            if (r == null) {
            System.out.println("No se encontró ninguna ruta con ese código.");
            return;
        }

            System.out.println("Deja en blanco para conservar el valor actual.");
            System.out.print("Nuevo origen (" + r.getCiuOrigen() + "): ");
            String ori = sc.nextLine();
            System.out.print("Nuevo destino (" + r.getCiuDestino() + "): ");
            String des = sc.nextLine();
            System.out.print("Nuevos kilómetros (" + r.getKilometros() + "): ");
            String km = sc.nextLine();
            System.out.print("Nuevo tiempo en min (" + r.getTiemReco() + "): ");
            String tiem = sc.nextLine();

            if (!ori.isBlank())  r.setCiuOrigen(ori);
            if (!des.isBlank())  r.setCiuDestino(des);
            if (!km.isBlank())   r.setKilometros(Integer.parseInt(km));
            if (!tiem.isBlank()) r.setTiemReco(Integer.parseInt(tiem));

            if (rutaService.actualizar(r)) {
            System.out.println("Ruta actualizada correctamente.");
            } else {
            System.out.println("Error al actualizar.");
            }
    }
        private void eliminar() {
        System.out.println("\n--- Eliminar Ruta ---");
        System.out.print("Código: ");
        String cod = sc.nextLine();
        if (rutaService.eliminar(cod)) {
            System.out.println("Ruta eliminada correctamente.");
        } else {
            System.out.println("No se encontró ninguna ruta con ese código.");
        }
    }
}
