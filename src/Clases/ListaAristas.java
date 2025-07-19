
package Clases;

public class ListaAristas {
    Arista cabeza;

    public void agregar(Municipio destino, int peso) {
        Arista nueva = new Arista(destino, peso);
        nueva.siguiente = cabeza;
        cabeza = nueva;
    }

    public Arista obtenerCabeza() {
        return cabeza;
    }

    public void mostrar() {
        Arista actual = cabeza;
        while (actual != null) {
            System.out.print(actual.destino.nombre + "(" + actual.distancia + ") ");
            actual = actual.siguiente;
        }
    }
}
