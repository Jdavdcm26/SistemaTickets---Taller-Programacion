/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import sistematickets.dao.ConductorDao;
import sistematickets.dao.PasajeroDao;
import sistematickets.dao.ReservaDao;
import sistematickets.dao.RutaDao;
import sistematickets.dao.VehiculoDao;
import sistematickets.model.Pasajero;
import sistematickets.model.Reserva;
import sistematickets.model.Vehiculo;

/**
 *
 * @author jadis
 */
public class ReservaService {
    
    private ReservaDao reservaDao     = new ReservaDao();
    private PasajeroDao pasajeroDao   = new PasajeroDao();
    private VehiculoDao vehiculoDao   = new VehiculoDao();
    private RutaDao rutaDao           = new RutaDao();
    private ConductorDao conductorDao = new ConductorDao();
    private TicketService ticketService = new TicketService();
    
     public String crear(String cedulaPasajero, String placaVehiculo, LocalDate fechaViaje) throws IOException {
        
        Pasajero pasajero = pasajeroDao.buscarPorCedula(cedulaPasajero);
        if (pasajero == null) return "Error: pasajero no encontrado.";
        HashMap<String, Vehiculo> vehiculos = vehiculoDao.cargarTodos(rutaDao, conductorDao);
        Vehiculo vehiculo = vehiculos.get(placaVehiculo);
        if (vehiculo == null) return "Error: vehiculo no encontrado.";
        HashMap<String, Reserva> reservas = reservaDao.cargarReservas(pasajeroDao, vehiculoDao, rutaDao, conductorDao);
        
        long reservasActivas = reservas.values().stream()
                .filter(r -> r.getVehiculo().getPlaca().equals(placaVehiculo)
                        && r.getEstado() == Reserva.Estado.ACTIVA)
                .count();
        
        if (vehiculo.getPasajerosActuales() + reservasActivas >= vehiculo.getCapacidadMaxima()) {
            return "Error: no hay cupos disponibles para ese vehiculo.";
        }
        
        boolean yaReservo = reservas.values().stream()
                .anyMatch(r -> r.getPasajero().getCedula().equals(cedulaPasajero)
                        && r.getVehiculo().getPlaca().equals(placaVehiculo)
                        && r.getFechaViaje().equals(fechaViaje)
                        && r.getEstado() == Reserva.Estado.ACTIVA);
        if (yaReservo) return "Error: el pasajero ya tiene una reserva activa para ese vehiculo y fecha.";
        
        String codigo = "RES-" + System.currentTimeMillis();
        Reserva reserva = new Reserva(codigo, pasajero, vehiculo, fechaViaje);
        reservaDao.agregarReserva(reserva);
        return "Reserva creada correctamente.\n" + reserva.imprimirDetalle();
    }
     
      public String cancelar(String codigo) throws IOException {
        HashMap<String, Reserva> reservas = reservaDao.cargarReservas(pasajeroDao, vehiculoDao, rutaDao, conductorDao);
        Reserva r = reservas.get(codigo);
        if (r == null) return "Error: reserva no encontrada.";
        if (r.getEstado() != Reserva.Estado.ACTIVA) return "Error: la reserva no esta activa.";
        r.setEstado(Reserva.Estado.CANCELADA);
        reservaDao.guardarReservas(reservas);
        return "Reserva cancelada correctamente.";
    }
      
      public ArrayList<Reserva> listarActivas() throws IOException {
        return reservaDao.cargarLista(pasajeroDao, vehiculoDao, rutaDao, conductorDao)
                .stream()
                .filter(r -> r.getEstado() == Reserva.Estado.ACTIVA)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
}
