
package Clases;

public class Empresa {
    private String nombre;
    private String NIT;
    private String correo;
    private String telefono;
    private String contraseña;
    private int siguienteId;

    public Empresa(String nombre, String NIT, String correo, String telefono, String contraseña) {
        this.nombre = nombre;
        this.NIT = NIT;
        this.correo = correo;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.siguienteId = 1;
    }
    
    public Empresa(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public boolean verificarCredenciales(String correo, String contraseña) {
        return this.correo.equals(correo) && this.contraseña.equals(contraseña);
    }

    // Crea y agrega una nueva ruta, asignando ID automáticamente
    public Ruta crearRuta(int id, Municipio[] recorrido, double precio, String tipoVehiculo, String horarioViaje, String horaSalida, int cantidadAsientos, String fecha) {
        Ruta ruta = new Ruta(siguienteId++, recorrido, precio, tipoVehiculo, horaSalida, horarioViaje, cantidadAsientos, fecha);
        return ruta;
    }

    // Serializa la empresa y sus rutas a CSV
    public String toCSV() {
        return correo + ";" + contraseña;
    }

    // Deserializa una empresa (sin rutas) desde CSV
    public static Empresa fromCSV(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 2) return null;
        return new Empresa(partes[0], partes[1]);
    }
    
    public int calcularAsientos(String tipo){
        switch(tipo){
            case "2 pisos":
                return 100;
            case "normal":
                return 60;
            case "aerovan":
                return 20;
            default:
                return 0;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getNIT() {
        return NIT;
    }

    public String getTelefono() {
        return telefono;
    }
    
}