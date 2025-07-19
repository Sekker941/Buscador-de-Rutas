
package Clases;

public class Arista {
    public Municipio destino;  // Enlace directo al vértice destino
    public int distancia;
    public Arista siguiente;

    public Arista(Municipio destino, int distancia) {
        this.destino = destino;
        this.distancia = distancia;
        this.siguiente = null;
    }
}
