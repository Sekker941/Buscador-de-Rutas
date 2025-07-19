
package Clases;

public class Empresa {
    private String nombre;
    private String NIT;
    private String correo;
    private String telefono;
    private String contraseña;

    public Empresa(String nombre, String NIT, String correo, String telefono, String contraseña) {
        this.nombre = nombre;
        this.NIT = NIT;
        this.correo = correo;
        this.telefono = telefono;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean verificarCredenciales(String nombre, String contraseña) {
        return this.nombre.equals(nombre) && this.contraseña.equals(contraseña);
    }

    // Serializa como CSV: nombre,NIT,correo,telefono,contraseña
    public String toCSV() {
        return nombre + "," + NIT + "," + correo + "," + telefono + "," + contraseña;
    }

    // Deserializa desde CSV
    public static Empresa fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 5) return null;
        return new Empresa(partes[0], partes[1], partes[2], partes[3], partes[4]);
    }
}
