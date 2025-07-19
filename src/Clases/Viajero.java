
package Clases;

public class Viajero {
    private String usuario;
    private String contraseña;

    public Viajero(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean verificarCredenciales(String usuario, String contraseña) {
        return this.usuario.equals(usuario) && this.contraseña.equals(contraseña);
    }

    // Serializa como línea CSV: usuario,contraseña
    public String toCSV() {
        return usuario + "," + contraseña;
    }

    // Deserializa desde línea CSV
    public static Viajero fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 2) return null;
        return new Viajero(partes[0], partes[1]);
    }
}
