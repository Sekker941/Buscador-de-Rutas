
package Clases;

public class Municipio {
    String nombre; // Ej: "Medellín"
    ListaAristas listaAdyacencia;

    public Municipio(String nombre) {
        this.nombre = nombre;
        this.listaAdyacencia = new ListaAristas();
    }
}
