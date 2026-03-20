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
import sistematickets.model.Ruta;
import sistematickets.util.RutaArchivos;

/**
 *
 * @author josec
 */
public class RutaDao {
      public void guardarRutas(HashMap<String, Ruta> rutas) throws IOException {
    File archivo = new File(RutaArchivos.RUTAS); // PRIMERO el archivo
    if (!archivo.exists()) {
        archivo.createNewFile();
    }
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
        for (Ruta r : rutas.values()) {
            bw.write(r.toFile());
            bw.newLine();
        }
    }
}

public HashMap<String, Ruta> cargarRutas() throws IOException {
    HashMap<String, Ruta> rutas = new HashMap<>();
    File archivo = new File(RutaArchivos.RUTAS); // PRIMERO el archivo
    if (!archivo.exists()) {
        return rutas;
    }
    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] p = linea.split(";");
            Ruta r = new Ruta(p[0], p[1], p[2], Integer.parseInt(p[3]), Integer.parseInt(p[4]));
            rutas.put(r.getCodRuta(), r);
        }
    }
    return rutas;
} 
        public Ruta buscarPorCodigo(String codRuta) throws IOException {
            return cargarRutas().get(codRuta);
         }
         public void agregarRuta(Ruta r) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(RutaArchivos.RUTAS, true));
        bw.write(r.toFile());
        bw.newLine();
        bw.close();
    }
          public ArrayList<Ruta> cargarLista() throws IOException {
            return new ArrayList<>(cargarRutas().values());
          }
}
