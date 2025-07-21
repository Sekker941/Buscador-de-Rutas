
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

    public String getNombre() {
        return nombre;
    }

    public boolean verificarCredenciales(String nombre, String contraseña) {
        return this.nombre.equals(nombre) && this.contraseña.equals(contraseña);
    }

    // Crea y agrega una nueva ruta, asignando ID automáticamente
    public Ruta crearRuta(Municipio[] recorrido, double precio, String tipoVehiculo, String horaSalida, int cantidadAsientos) {
        Ruta ruta = new Ruta(siguienteId++, recorrido, precio, tipoVehiculo, horaSalida, cantidadAsientos);
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
        return nombre + "," + NIT + "," + correo + "," + telefono + "," + contraseña;
    }

    // Deserializa una empresa (sin rutas) desde CSV
    public static Empresa fromCSV(String linea) {
        String[] parts = linea.split(",");
        if (parts.length != 5) return null;
        return new Empresa(parts[0], parts[1], parts[2], parts[3], parts[4]);
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
