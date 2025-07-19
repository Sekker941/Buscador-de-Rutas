
package Clases;

public class Grafo {
    private Municipio[] vertices;  
    private int cantidad;          
    private int capacidadMaxima;   

    public Grafo(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.vertices = new Municipio[capacidadMaxima];
        this.cantidad = 0;
    }

    public void agregarMunicipio(String nombre) {
        if (cantidad < capacidadMaxima) {
            vertices[cantidad++] = new Municipio(nombre);
        }
    }

    public Municipio buscarMunicipio(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (vertices[i].nombre.equals(nombre)) {
                return vertices[i];
            }
        }
        return null;
    }

    public void agregarArista(String origenNombre, String destinoNombre, int peso, boolean bidireccional) {
        Municipio origen = buscarMunicipio(origenNombre);
        Municipio destino = buscarMunicipio(destinoNombre);

        if (origen != null && destino != null) {
            origen.listaAdyacencia.agregar(destino, peso);
            if (bidireccional) {
                destino.listaAdyacencia.agregar(origen, peso);
            }
        }
    }

    public void mostrarGrafo() {
        for (int i = 0; i < cantidad; i++) {
            Municipio m = vertices[i];
            System.out.print(m.nombre + " -> ");
            m.listaAdyacencia.mostrar();
            System.out.println();
        }
    }
}
