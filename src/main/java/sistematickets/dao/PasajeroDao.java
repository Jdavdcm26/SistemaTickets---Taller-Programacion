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
import sistematickets.model.PasajeroAdultoMayor;
import sistematickets.model.PasajeroEstudiante;
import sistematickets.model.PasajeroRegular;
import sistematickets.util.RutaArchivos;

/**
 *
 * @author jadis
 */
public class PasajeroDao {
    
    public void guardarPasajeros(HashMap<String, Pasajero> pasajeros) throws IOException {
        File archivo = new File(RutaArchivos.PASAJEROS);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Pasajero p : pasajeros.values()) {
                bw.write(p.toFile());
                bw.newLine();
            }
        }
    }

    public HashMap<String, Pasajero> cargarPasajeros() throws IOException {
        HashMap<String, Pasajero> pasajeros = new HashMap<>();
        File archivo = new File(RutaArchivos.PASAJEROS);
        if (!archivo.exists()) {
            return pasajeros;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");
                String cedula = p[0];
                String nombre = p[1];
                LocalDate fechaNacimiento = LocalDate.parse(p[2]);
                String tipo = p[3];

                Pasajero pasajero = switch (tipo) {
                    case "Estudiante"   -> new PasajeroEstudiante(cedula, nombre, fechaNacimiento);
                    case "Adulto Mayor" -> new PasajeroAdultoMayor(cedula, nombre, fechaNacimiento);
                    default             -> new PasajeroRegular(cedula, nombre, fechaNacimiento);
                };
                pasajeros.put(cedula, pasajero);
            }
        }
        return pasajeros;
    }
    
    public void agregarPasajero(Pasajero p) throws IOException {
        File archivo = new File(RutaArchivos.PASAJEROS);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(p.toFile());
            bw.newLine();
        }
    }

    public Pasajero buscarPorCedula(String cedula) throws IOException {
        return cargarPasajeros().get(cedula);
    }

    public ArrayList<Pasajero> cargarLista() throws IOException {
        return new ArrayList<>(cargarPasajeros().values());
    }
    
}
