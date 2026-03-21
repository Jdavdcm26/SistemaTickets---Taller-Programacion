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
import java.time.LocalDateTime;
import java.util.HashMap;
import sistematickets.model.Pasajero;
import sistematickets.model.Reserva;
import sistematickets.model.Vehiculo;
import sistematickets.util.RutaArchivos;

/**
 *
 * @author josec
 */
public class ReservaDao {
  public void agregarReserva(Reserva r) throws IOException {
        File archivo = new File(RutaArchivos.RESERVAS);
        if (!archivo.exists()) archivo.createNewFile();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(r.toFile());
            bw.newLine();
        }
    }
public HashMap<String, Reserva> cargarReservas(PasajeroDao pasajeroDao, VehiculoDao vehiculoDao,
                                                    RutaDao rutaDao, ConductorDao conductorDao) throws IOException {
        HashMap<String, Reserva> reservas = new HashMap<>();
        File archivo = new File(RutaArchivos.RESERVAS);
        if (!archivo.exists()) return reservas;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");
                // p[0]=codigo, p[1]=cedulaPasajero, p[2]=placaVehiculo
                // p[3]=fechaCreacion, p[4]=fechaViaje, p[5]=estado
                Pasajero pasajero = pasajeroDao.buscarPorCedula(p[1]);
                Vehiculo vehiculo = vehiculoDao.buscarPorPlaca(p[2], rutaDao, conductorDao);
                LocalDateTime fechaCreacion = LocalDateTime.parse(p[3]);
                LocalDate fechaViaje = LocalDate.parse(p[4]);
                Reserva.Estado estado = Reserva.Estado.valueOf(p[5]);

                Reserva reserva = new Reserva(p[0], pasajero, vehiculo, fechaCreacion, fechaViaje, estado);
                reservas.put(reserva.getCodigo(), reserva);
            }
        }
        return reservas;
    }
public void guardarReservas(HashMap<String, Reserva> reservas) throws IOException {
        File archivo = new File(RutaArchivos.RESERVAS);
        if (!archivo.exists()) archivo.createNewFile();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Reserva r : reservas.values()) {
                bw.write(r.toFile());
                bw.newLine();
            }
        }
    }
}
