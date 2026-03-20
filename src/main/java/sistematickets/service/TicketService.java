/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import sistematickets.dao.ConductorDao;
import sistematickets.dao.PasajeroDao;
import sistematickets.dao.RutaDao;
import sistematickets.dao.TicketDao;
import sistematickets.dao.VehiculoDao;
import sistematickets.model.Pasajero;
import sistematickets.model.Ticket;
import sistematickets.model.Vehiculo;

/**
 *
 * @author jadis
 */
public class TicketService {
    
    private TicketDao ticketDao       = new TicketDao();
    private PasajeroDao pasajeroDao   = new PasajeroDao();
    private VehiculoDao vehiculoDao   = new VehiculoDao();
    private RutaDao rutaDao           = new RutaDao();
    private ConductorDao conductorDao = new ConductorDao();

    // Lista de festivos configurada
    private static final List<LocalDate> FESTIVOS = Arrays.asList(
        LocalDate.of(LocalDate.now().getYear(), 1,  1),  // Año nuevo
        LocalDate.of(LocalDate.now().getYear(), 5,  1),  // Día del trabajo
        LocalDate.of(LocalDate.now().getYear(), 7,  20), // Independencia
        LocalDate.of(LocalDate.now().getYear(), 8,  7),  // Batalla de Boyacá
        LocalDate.of(LocalDate.now().getYear(), 12, 25)  // Navidad
    );
    
    public String venderTicket(String cedulaPasajero, String placaVehiculo) {
        try {
            // Buscar pasajero y vehículo
            Pasajero pasajero = pasajeroDao.buscarPorCedula(cedulaPasajero);
            if (pasajero == null) return "Error: pasajero no encontrado.";

            HashMap<String, Vehiculo> vehiculos = vehiculoDao.cargarTodos(rutaDao, conductorDao);
            Vehiculo vehiculo = vehiculos.get(placaVehiculo);
            if (vehiculo == null) return "Error: vehículo no encontrado.";
            
            if (vehiculo.getConductor() == null) return "Error: el vehículo no tiene conductor asignado."; 
            
            // Validación: verificar cupos disponibles
            if (vehiculo.getCuposDisponibles() <= 0) {
                return "Error: el vehículo está lleno, no hay cupos disponibles.";
            }

            // Validación taller 2: máximo 3 tickets por día
            LocalDate hoy = LocalDate.now();
            long ticketsHoy = ticketDao.cargarLista(pasajeroDao, vehiculoDao, rutaDao, conductorDao)
                    .stream()
                    .filter(t -> t.getPasajero().getCedula().equals(cedulaPasajero)
                              && t.getFechaCompra().equals(hoy))
                    .count();
            if (ticketsHoy >= 3) {
                return "Error: el pasajero ya tiene " + ticketsHoy + " tickets comprados hoy.";
            }

            // Calcular valor con descuento
            double valorFinal = vehiculo.getTarifaBase() * (1 - pasajero.calcularDescuento());

            // Validación taller 2: recargo del 20% en festivos
            if (FESTIVOS.contains(hoy)) {
                valorFinal = valorFinal * 1.20;
            }

            // Generar número de ticket
            String numeroTicket = "TK-" + System.currentTimeMillis();

            // Crear y guardar ticket
            Ticket t = new Ticket(numeroTicket, pasajero, vehiculo, hoy, valorFinal);
            ticketDao.agregarTicket(t);

            // Actualizar pasajeros en el vehículo
            vehiculo.setPasajerosActuales(vehiculo.getPasajerosActuales() + 1);
            if (vehiculo.getCuposDisponibles() == 0) {
                vehiculo.setDisponible(false);
            }
            vehiculoDao.guardarTodos(vehiculos);

            return "Ticket vendido correctamente.\n" + t.imprimirDetalle();

        } catch (IOException e) {
            return "Error al procesar la venta.";
        }
    }
}
