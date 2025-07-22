
package Clases;

public class ArregloDinamico<T> {
    private Object[] elementos;
    private int longitud;

    public ArregloDinamico() {
        elementos = new Object[2];
        longitud = 0;
    }

    public void agregar(T elemento) {
        if (longitud == elementos.length) {
            redimensionar();
        }
        elementos[longitud++] = elemento;
    }

    @SuppressWarnings("unchecked")
    public T obtener(int indice) {
        if (indice < 0 || indice >= longitud) {
            return null;
        }
        return (T) elementos[indice];
    }

    public int size() {
        return longitud;
    }

    private void redimensionar() {
        Object[] nuevo = new Object[elementos.length * 2];
        for (int i = 0; i < elementos.length; i++) {
            nuevo[i] = elementos[i];
        }
        elementos = nuevo;
    }

    public boolean eliminar(int indice) {
        if (indice < 0 || indice >= longitud) {
            return false;
        }
        for (int i = indice; i < longitud - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[--longitud] = null;
        return true;
    }

    public boolean actualizar(int indice, T elemento) {
        if (indice < 0 || indice >= longitud) {
            return false;
        }
        elementos[indice] = elemento;
        return true;
    }
}
