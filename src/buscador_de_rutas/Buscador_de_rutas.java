
package buscador_de_rutas;

import Clases.Grafo;

public class Buscador_de_rutas {

    public static void main(String[] args) {
        Grafo grafo = new Grafo(10); // Capacidad para 10 municipios

        // Definición de municipios
        grafo.agregarVertice("Medellín");
        grafo.agregarVertice("Bogotá");
        grafo.agregarVertice("Cali");
        grafo.agregarVertice("Bucaramanga");

        // Conexiones (vías) entre municipios
        grafo.agregarArista("Medellín", "Bogotá", 8, true);
        grafo.agregarArista("Medellín", "Cali", 12, true);
        grafo.agregarArista("Bogotá", "Bucaramanga", 4, true);

        // Mostrar la estructura
        grafo.mostrarGrafo();
    }
    
}
