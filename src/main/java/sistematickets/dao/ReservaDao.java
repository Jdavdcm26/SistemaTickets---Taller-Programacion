/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import sistematickets.model.Reserva;
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
}
