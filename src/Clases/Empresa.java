
package Clases;

public class Empresa {
    private String nombre;
    private String NIT;
    private String correo;
    private String telefono;
    private String contraseña;
    private ArregloDinamico<Ruta> rutas;
    private int siguienteId;

    public Empresa(String nombre, String NIT, String correo, String telefono, String contraseña) {
        this.nombre = nombre;
        this.NIT = NIT;
        this.correo = correo;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.rutas = new ArregloDinamico<>();
        this.siguienteId = 1;
    }
    
    public Empresa(String correo, String contraseña) {
        this("", "", correo, "", contraseña); // Llama al constructor completo
    }

    public String getCorreo() {
        return correo;
    }

    public boolean verificarCredenciales(String correo, String contraseña) {
        return this.nombre.equals(correo) && this.contraseña.equals(contraseña);
    }

    // Crea y agrega una nueva ruta, asignando ID automáticamente
    public Ruta crearRuta(int id, Municipio[] recorrido, double precio, String tipoVehiculo, String horarioViaje, String horaSalida, int cantidadAsientos, String fecha) {
        Ruta ruta = new Ruta(siguienteId++, recorrido, precio, tipoVehiculo, horaSalida, horarioViaje, cantidadAsientos, fecha);
        rutas.agregar(ruta);
        return ruta;
    }

    // Elimina la ruta con el ID dado
    public boolean eliminarRutaPorId(int id) {
        for (int i = 0; i < rutas.size(); i++) {
            Ruta r = rutas.obtener(i);
            if (r.getId() == id) {
                return rutas.eliminar(i);
            }
        }
        return false;
    }

    // Modifica los atributos de la ruta con el ID dado
    public boolean modificarRutaPorId(int id, double nuevoPrecio, String nuevoTipoVehiculo, String nuevaHoraSalida, int nuevaCantidadAsientos) {
        for (int i = 0; i < rutas.size(); i++) {
            Ruta r = rutas.obtener(i);
            if (r.getId() == id) {
                r.setPrecio(nuevoPrecio);
                r.setTipoVehiculo(nuevoTipoVehiculo);
                r.setHoraSalida(nuevaHoraSalida);
                r.setCantidadAsientos(nuevaCantidadAsientos);
                return true;
            }
        }
        return false;
    }

    // Serializa la empresa y sus rutas a CSV
    public String toCSV() {
        return correo + ";" + contraseña;
    }

    // Deserializa una empresa (sin rutas) desde CSV
    public static Empresa fromCSV(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 2) return null;
        return new Empresa(partes[0].trim(), partes[1].trim());
    }

    // Obtiene todas las rutas
    public Ruta[] obtenerRutas() {
        Ruta[] lista = new Ruta[rutas.size()];
        for (int i = 0; i < rutas.size(); i++) {
            lista[i] = rutas.obtener(i);
        }
        return lista;
    }
}