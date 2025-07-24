
package Clases;

import java.io.*;

public class SistemaRegistro {
    private final File fileViajeros = new File("viajeros.csv");
    private final File fileEmpresas = new File("empresas.csv");
    private final File fileViajerosInfo = new File("viajeros_info.csv");
    private final File fileEmpresasInfo = new File("empresas_info.csv");
    private final File fileRutasGlobal = new File("rutas_global.csv");

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
        if (!fileRutasGlobal.exists()) {
            fileRutasGlobal.createNewFile();
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
                String[] p = linea.split(";");
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
                if (v != null && v.getCorreo().equals(correo)) {
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
            bw.write(nombre + ";" + NIT + ";" + correo + ";" + telefono + ";" + contraseña);
            bw.newLine();
        }
        return true;
    }
    
    public Empresa buscarEmpresaInfo(String correo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileEmpresasInfo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");
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
    
    private static String historialFilename(String correo) {
        // limpia caracteres inválidos en nombre de archivo
        String base = correo.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
        return "historial_" + base + ".csv";
    }
    
    public void guardarHistorial(Viajero v, Ruta r) throws IOException {
        String fichero = historialFilename(v.getCorreo());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true))) {
            bw.write(r.toCSV());
            bw.newLine();
        }
    }
    
    public ArregloDinamico<Ruta> cargarHistorial(Viajero v) throws IOException {
        ArregloDinamico<Ruta> historico = new ArregloDinamico<>();
        String fichero = historialFilename(v.getCorreo());
        File f = new File(fichero);
        if (!f.exists()) {
            return historico;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Ruta ruta = Ruta.fromCSV(linea);
                if (ruta != null) {
                    historico.agregar(ruta);
                }
            }
        }
        return historico;
    }
    
    private String rutasFilename(String correo) {
        String base = correo.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
        return "rutas_" + base + ".csv";
    }
    
    public void guardarRutasEmpresa(Empresa e, Ruta r) throws IOException {
        String fichero = rutasFilename(e.getCorreo());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true))) {
            bw.write(r.toCSV());
            bw.newLine();
        }
        try (BufferedWriter bwg = new BufferedWriter(new FileWriter(fileRutasGlobal, true))) {
            // prefijo para identificar empresa: correo; luego el resto de toCSV()
            bwg.write(e.getCorreo() + ";" + r.toCSV());
            bwg.newLine();
        }
    }
    
    public ArregloDinamico<Ruta> cargarRutasEmpresa(Empresa e) throws IOException {
        ArregloDinamico<Ruta> lista = new ArregloDinamico<>();
        String fichero = rutasFilename(e.getCorreo());
        File f = new File(fichero);
        if (!f.exists()) {
            return lista;  // ninguna ruta registrada aún
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Ruta ruta = Ruta.fromCSV(linea);
                if (ruta != null) {
                    lista.agregar(ruta);
                }
            }
        }
        return lista;
    }
    
    public Ruta buscarRutaEmpresaPorId(Empresa e, int id) throws IOException {
        String fichero = rutasFilename(e.getCorreo());
        File file = new File(fichero);
        if (!file.exists()) return null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Ruta r = Ruta.fromCSV(linea);
                if (r != null && r.getId() == id) {
                    return r;
                }
            }
        }
        return null;
    }
    
    public boolean modificarRutaEmpresaPorId(Empresa e, int id, double nuevoPrecio, String nuevoTipoVehiculo, String nuevoHorarioViaje, String nuevaHoraSalida, String nuevaFecha ) throws IOException {
        String fichero = rutasFilename(e.getCorreo());
        File file = new File(fichero);
        if (!file.exists()) return false;

        // Acumula todas las líneas en un ArregloDinamico
        ArregloDinamico<String> todasLineas = new ArregloDinamico<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Ruta r = Ruta.fromCSV(linea);
                if (r != null && r.getId() == id) {
                    // Creamos la ruta modificada
                    Ruta rutaMod = new Ruta(
                        r.getId(),
                        r.getRecorrido(),
                        nuevoPrecio,
                        nuevoTipoVehiculo,
                        nuevoHorarioViaje,
                        nuevaHoraSalida,
                        r.getCantidadAsientos(),
                        nuevaFecha
                    );
                    todasLineas.agregar(rutaMod.toCSV());
                    encontrado = true;
                } else {
                    todasLineas.agregar(linea);
                }
            }
        }

        if (!encontrado) {
            // No existe ruta con ese ID
            return false;
        }

        // Sobrescribir el archivo con las líneas actualizadas
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (int i = 0; i < todasLineas.size(); i++) {
                bw.write(todasLineas.obtener(i));
                bw.newLine();
            }
            modificarRutaGlobalPorId(e, id, nuevoPrecio,nuevoTipoVehiculo, nuevoHorarioViaje, nuevaHoraSalida, nuevaFecha);
        }

        return true;
    }
    
    public boolean modificarRutaGlobalPorId(Empresa e, int id, double nuevoPrecio, String nuevoTipoVehiculo, String nuevoHorarioViaje, String nuevaHoraSalida, String nuevaFecha) throws IOException {
        // Leemos todo en un ArregloDinamico<String>
        ArregloDinamico<String> lineas = new ArregloDinamico<>();
        boolean modificado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fileRutasGlobal))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // formato: correo;id,precio,...
                String[] partes = linea.split(";", 2);
                if (partes.length < 2) {
                    lineas.agregar(linea);
                    continue;
                }
                String correo = partes[0];
                Ruta r = Ruta.fromCSV(partes[1]);
                if (correo.equals(e.getCorreo()) && r != null && r.getId() == id) {
                    // reconstruimos línea nueva
                    Ruta rutaMod = new Ruta(
                        r.getId(), r.getRecorrido(),
                        nuevoPrecio, nuevoTipoVehiculo,
                        nuevoHorarioViaje, nuevaHoraSalida,
                        r.getCantidadAsientos(), nuevaFecha
                    );
                    lineas.agregar(correo + ";" + rutaMod.toCSV());
                    modificado = true;
                } else {
                    lineas.agregar(linea);
                }
            }
        }

        if (!modificado) return false;

        // Sobrescribimos
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileRutasGlobal, false))) {
            for (int i = 0; i < lineas.size(); i++) {
                bw.write(lineas.obtener(i));
                bw.newLine();
            }
        }

        return true;
    }
    
    public boolean eliminarRutaPorId(Empresa empresa, int id) {
        String correo = empresa.getCorreo();
        File archivoRutas = new File("data/rutas_" + correo + ".csv");
        File archivoTemporal = new File("data/temp_rutas_" + correo + ".csv");
        boolean eliminada = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoRutas));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                Ruta ruta = Ruta.fromCSV(linea);
                if (ruta != null && ruta.getId() == id) {
                    eliminada = true; // Saltamos esta línea
                    continue;
                }
                bw.write(linea);
                bw.newLine();
            }
            eliminarRutaGlobalPorId(empresa, id);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Reemplazamos el archivo original
        if (!archivoRutas.delete() || !archivoTemporal.renameTo(archivoRutas)) {
            System.err.println("Error al reemplazar el archivo de rutas.");
            return false;
        }

        return eliminada;
    }
    
    public boolean eliminarRutaGlobalPorId(Empresa e, int id) throws IOException {
        ArregloDinamico<String> lineas = new ArregloDinamico<>();
        boolean eliminado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fileRutasGlobal))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";", 2);
                if (partes.length == 2) {
                    String correo = partes[0];
                    Ruta r = Ruta.fromCSV(partes[1]);
                    if (correo.equals(e.getCorreo()) && r != null && r.getId() == id) {
                        eliminado = true;
                        continue;  // omitimos esta línea
                    }
                }
                lineas.agregar(linea);
            }
        }

        if (!eliminado) return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileRutasGlobal, false))) {
            for (int i = 0; i < lineas.size(); i++) {
                bw.write(lineas.obtener(i));
                bw.newLine();
            }
        }

        return true;
    }
}
