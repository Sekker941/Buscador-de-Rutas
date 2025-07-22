
package Clases;

public class Viajero {
    private String correo;
    private String contraseña;

    public Viajero(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return correo;
    }

    public boolean verificarCredenciales(String correo, String contraseña) {
        return this.correo.equals(correo) && this.contraseña.equals(contraseña);
    }

    // Serializa como línea CSV: correo,contraseña
    public String toCSV() {
        return correo + "," + contraseña;
    }

    // Deserializa desde línea CSV
    public static Viajero fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 2) return null;
        return new Viajero(partes[0], partes[1]);
    }
}
