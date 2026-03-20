/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import sistematickets.dao.ConductorDao;
import sistematickets.dao.PasajeroDao;
import sistematickets.model.Pasajero;
import sistematickets.model.PasajeroAdultoMayor;
import sistematickets.model.PasajeroEstudiante;
import sistematickets.model.PasajeroRegular;

/**
 *
 * @author jadis
 */
public class PasajeroService {
    
    private PasajeroDao pasajeroDao = new PasajeroDao();
    private ConductorDao conductorDao = new ConductorDao();

    public boolean agregar(String cedula, String nombre, LocalDate fechaNacimiento, String tipo) {
        try {
            if (pasajeroDao.buscarPorCedula(cedula) != null || conductorDao.buscarPorCedula(cedula) != null) {
                return false;
            }
            // Taller 2: calcular edad automáticamente
            int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
            Pasajero p;
            if (edad >= 60) {
                p = new PasajeroAdultoMayor(cedula, nombre, fechaNacimiento);
            } else {
                p = switch (tipo.toLowerCase()) {
                    case "estudiante" -> new PasajeroEstudiante(cedula, nombre, fechaNacimiento);
                    default           -> new PasajeroRegular(cedula, nombre, fechaNacimiento);
                };
            }
            pasajeroDao.agregarPasajero(p);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Pasajero buscarPorCedula(String cedula) {
        try {
            return pasajeroDao.buscarPorCedula(cedula);
        } catch (IOException e) {
            return null;
        }
    }
    
}
