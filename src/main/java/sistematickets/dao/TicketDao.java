/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import sistematickets.model.Pasajero;
import sistematickets.model.Ticket;
import sistematickets.model.Vehiculo;
import sistematickets.util.RutaArchivos;

/**
 *
 * @author jadis
 */
public class TicketDao {
    
    public void agregarTicket(Ticket t) throws IOException {
        File archivo = new File(RutaArchivos.TICKETS);
        if (!archivo.exists()) archivo.createNewFile();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(t.toFile());
            bw.newLine();
        }
    }

    public HashMap<String, Ticket> cargarTickets(PasajeroDao pasajeroDao, VehiculoDao vehiculoDao,
                                                  RutaDao rutaDao, ConductorDao conductorDao) throws IOException {
        HashMap<String, Ticket> tickets = new HashMap<>();
        File archivo = new File(RutaArchivos.TICKETS);
        if (!archivo.exists()) return tickets;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");
                // p[0]=numeroTicket, p[1]=cedulaPasajero, p[2]=placaVehiculo
                // p[3]=fechaCompra,  p[4]=valorFinal
                Pasajero pasajero = pasajeroDao.buscarPorCedula(p[1]);
                Vehiculo vehiculo = vehiculoDao.buscarPorPlaca(p[2], rutaDao, conductorDao);
                LocalDate fechaCompra = LocalDate.parse(p[3]);
                double valorFinal = Double.parseDouble(p[4]); // CAMBIO: lee el valor guardado

                // CAMBIO: pasa valorFinal directamente, no recalcula
                Ticket t = new Ticket(p[0], pasajero, vehiculo, fechaCompra, valorFinal);
                tickets.put(t.getNumeroTicket(), t);
            }
        }
        return tickets;
    }
    
    public ArrayList<Ticket> cargarLista(PasajeroDao pasajeroDao, VehiculoDao vehiculoDao,
                                          RutaDao rutaDao, ConductorDao conductorDao) throws IOException {
        return new ArrayList<>(cargarTickets(pasajeroDao, vehiculoDao, rutaDao, conductorDao).values());
    }
    
}
