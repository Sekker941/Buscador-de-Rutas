
package buscador_de_rutas;

import Clases.Grafo;

public class Buscador_de_rutas {

    public static void main(String[] args) {
        Grafo grafo = new Grafo(10);

        grafo.agregarMunicipio("Medellín");
        grafo.agregarMunicipio("Bogotá");
        grafo.agregarMunicipio("Cali");
        grafo.agregarMunicipio("Bucaramanga");

        grafo.agregarArista("Medellín", "Bogotá", 8, true);
        grafo.agregarArista("Medellín", "Cali", 12, true);
        grafo.agregarArista("Bogotá", "Bucaramanga", 4, true);

        grafo.mostrarGrafo();
    }
    
}
