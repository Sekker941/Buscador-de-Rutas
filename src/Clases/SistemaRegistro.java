
package Clases;

import java.io.*;

public class SistemaRegistro {
    private final File fileViajeros = new File("viajeros.csv");
    private final File fileEmpresas = new File("empresas.csv");
    private final File fileViajerosInfo = new File("viajeros_info.csv");
    private final File fileEmpresasInfo = new File("empresas_info.csv");

    public SistemaRegistro() throws IOException {
        // Crea archivos si no existen
        if (!fileViajeros.exists()){
            fileViajeros.createNewFile();
        }
        if (!fileEmpresas.exists()){
            fileEmpresas.createNewFile();
        }
        if (!fileViajerosInfo.exists()){
            fileViajerosInfo.createNewFile();
        }
        if (!fileEmpresasInfo.exists()){
            fileEmpresasInfo.createNewFile();
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
    
    //Registro de informacion del viajero
    public boolean registrarViajeroInfo(String nombre, String apellido, String correo, String contraseña) throws IOException {
        if (buscarViajeroInfo(correo) != null) return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileViajerosInfo, true))) {
            bw.write(nombre + ";" + apellido + ";" + correo + ";" + contraseña);
            bw.newLine();
        }
        return true;
    }
    
    public Viajero buscarViajeroInfo(String correo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileViajerosInfo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",");
                if (p.length != 4) continue;
                if (p[2].trim().equalsIgnoreCase(correo)) {
                    return new Viajero(p[0].trim(), p[1].trim(), p[2].trim(), p[3].trim());
                }
            }
        }
        return null;
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
    public boolean registrarEmpresa(String correo, String contraseña) throws IOException {
        if (buscarEmpresa(correo) != null) return false;

        try (FileWriter fw = new FileWriter(fileEmpresas, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            Empresa e = new Empresa(correo, contraseña);
            bw.write(e.toCSV());
            bw.newLine();
        }

        return true;
    }
    
    public boolean registrarEmpresaInfo(String nombre, String NIT, String correo, String telefono, String contraseña) throws IOException {
        if (buscarEmpresaInfo(correo) != null) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileEmpresasInfo, true))) {
            bw.write(nombre + "," + NIT + "," + correo + "," + telefono + "," + contraseña);
            bw.newLine();
        }
        return true;
    }
    
    public Empresa buscarEmpresaInfo(String correo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileEmpresasInfo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",");
                if (p.length != 5) continue;  // línea malformada
                if (p[2].trim().equalsIgnoreCase(correo)) {
                    return new Empresa(
                        p[0].trim(),
                        p[1].trim(),
                        p[2].trim(),
                        p[3].trim(),
                        p[4].trim()
                    );
                }
            }
        }
        return null;
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
