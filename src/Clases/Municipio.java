
package Clases;

public class Municipio {
    String nombre;
    ListaAristas listaAdyacencia;

    public Municipio(String nombre) {
        this.nombre = nombre;
        this.listaAdyacencia = new ListaAristas();
    }

    public String getNombre() {
        return nombre;
    }

    public ListaAristas getListaAdyacencia() {
        return listaAdyacencia;
    }
}
