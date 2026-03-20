/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import sistematickets.dao.ConductorDao;
import sistematickets.dao.PasajeroDao;
import sistematickets.dao.RutaDao;
import sistematickets.dao.TicketDao;
import sistematickets.dao.VehiculoDao;

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
    
}
