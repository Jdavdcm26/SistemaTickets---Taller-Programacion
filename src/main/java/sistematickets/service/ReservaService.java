/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.service;

import sistematickets.dao.ConductorDao;
import sistematickets.dao.PasajeroDao;
import sistematickets.dao.ReservaDao;
import sistematickets.dao.RutaDao;
import sistematickets.dao.VehiculoDao;

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
    
}
