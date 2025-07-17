
package Clases;

public class Arista {
    public Municipio destino;  // Enlace directo al vértice destino
    public int distancia;
    public Arista siguiente;

    public Arista(Municipio destino, int peso) {
        this.destino = destino;
        this.distancia = peso;
        this.siguiente = null;
    }
}
