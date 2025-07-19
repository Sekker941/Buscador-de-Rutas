
package Clases;

public class ListaAristas {
    Arista cabeza;

    public void agregar(Municipio destino, int distancia) {
        Arista nueva = new Arista(destino, distancia);
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
