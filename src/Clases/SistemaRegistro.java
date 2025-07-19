
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
    public boolean registrarViajero(String usuario, String contraseña) throws IOException {
        if (buscarViajero(usuario) != null){
            return false;
        }
        try (FileWriter fw = new FileWriter(fileViajeros, true);
            BufferedWriter bw = new BufferedWriter(fw)) {
            Viajero v = new Viajero(usuario, contraseña);
            bw.write(v.toCSV());
            bw.newLine();
        }
        return true;
    }

    // Login de viajero
    public boolean loginViajero(String usuario, String contraseña) throws IOException {
        Viajero v = buscarViajero(usuario);
        return v != null && v.verificarCredenciales(usuario, contraseña);
    }

    private Viajero buscarViajero(String usuario) throws IOException {
        try (FileReader fr = new FileReader(fileViajeros);
            BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Viajero v = Viajero.fromCSV(linea);
                if (v != null && v.getUsuario().equals(usuario)) {
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
            Empresa e = new Empresa(nombre, NIT, correo, telefono, contraseña);
            bw.write(e.toCSV());
            bw.newLine();
        }
        return true;
    }

    // Login de empresa
    public boolean loginEmpresa(String nombre, String contraseña) throws IOException {
        Empresa e = buscarEmpresa(nombre);
        return e != null && e.verificarCredenciales(nombre, contraseña);
    }

    private Empresa buscarEmpresa(String nombre) throws IOException {
        try (FileReader fr = new FileReader(fileEmpresas);
            BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Empresa e = Empresa.fromCSV(linea);
                if (e != null && e.getNombre().equals(nombre)) {
                    return e;
                }
            }
        }
        return null;
    }
}
