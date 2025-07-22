
package Clases;

import java.io.*;

public class SistemaRegistro {
    private final File fileViajeros = new File("viajeros.csv");
    private final File fileEmpresas = new File("empresas.csv");

    public SistemaRegistro() throws IOException {
        // Crea archivos si no existen
        if (!fileViajeros.exists()){
            fileViajeros.createNewFile();
        }
        if (!fileEmpresas.exists()){
            fileEmpresas.createNewFile();
        }
    }

    // Registro de viajero en CSV
    public boolean registrarViajero(String correo, String contraseña) throws IOException {
        if (buscarViajero(correo) != null){
            return false;
        }
        try (FileWriter fw = new FileWriter(fileViajeros, true);
            BufferedWriter bw = new BufferedWriter(fw)) {
            Viajero v = new Viajero(correo, contraseña);
            bw.write(v.toCSV());
            bw.newLine();
        }
        return true;
    }

    // Login de viajero
    public boolean loginViajero(String correo, String contraseña) throws IOException {
        Viajero v = buscarViajero(correo);
        return v != null && v.verificarCredenciales(correo, contraseña);
    }

    private Viajero buscarViajero(String correo) throws IOException {
        try (FileReader fr = new FileReader(fileViajeros);
            BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Viajero v = Viajero.fromCSV(linea);
                if (v != null && v.getUsuario().equals(correo)) {
                    return v;
                }
            }
        }
        return null;
    }

    // Registro de empresa en CSV
    public boolean registrarEmpresa(String nombre, String NIT, String correo, String telefono, String contraseña) throws IOException {
        if (buscarEmpresa(nombre) != null) return false;

        try (FileWriter fw = new FileWriter(fileEmpresas, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            Empresa e = new Empresa(correo, contraseña);
            bw.write(e.toCSV());
            bw.newLine();
        }

        return true;
    }

    // Login de empresa
    public boolean loginEmpresa(String correo, String contraseña) throws IOException {
        Empresa e = buscarEmpresa(correo);
        return e != null && e.verificarCredenciales(correo, contraseña);
    }

    private Empresa buscarEmpresa(String correo) throws IOException {
        try (FileReader fr = new FileReader(fileEmpresas);
             BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Empresa e = Empresa.fromCSV(linea);
                if (e != null && e.getCorreo().equals(correo)) {
                    return e;
                }
            }
        }
        return null;
    }
}
