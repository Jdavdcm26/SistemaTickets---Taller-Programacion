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
import java.util.ArrayList;
import java.util.HashMap;
import sistematickets.model.Conductor;
import sistematickets.util.RutaArchivos;

/**
 *
 * @author jadis
 */
public class ConductorDao {
    
    public void guardarConductores(HashMap<String, Conductor> conductores) throws IOException {
        File archivo = new File(RutaArchivos.CONDUCTORES);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Conductor c : conductores.values()) {
                bw.write(c.toFile());
                bw.newLine();
            }
        }
    }

    public HashMap<String, Conductor> cargarConductores() throws IOException {
        HashMap<String, Conductor> conductores = new HashMap<>();
        File archivo = new File(RutaArchivos.CONDUCTORES);
        if (!archivo.exists()) {
            return conductores;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");
                Conductor c = new Conductor(p[0], p[1], p[2], p[3]);
                conductores.put(c.getCedula(), c);
            }
        }
        return conductores;
    }
    
    public void agregarConductor(Conductor c) throws IOException {
        File archivo = new File(RutaArchivos.CONDUCTORES);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(c.toFile());
            bw.newLine();
        }
    }

    public Conductor buscarPorCedula(String cedula) throws IOException {
        return cargarConductores().get(cedula);
    }

    public ArrayList<Conductor> cargarLista() throws IOException {
        return new ArrayList<>(cargarConductores().values());
    }
    
}
