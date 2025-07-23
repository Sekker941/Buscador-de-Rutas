
package Clases;

public class Grafo {
    private Municipio[] municipios;  
    private int cantidad;          
    private int capacidadMaxima;   

    public Grafo(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.municipios = new Municipio[capacidadMaxima];
        this.cantidad = 0;
    }

    public void agregarMunicipio(String nombre) {
        if (cantidad < capacidadMaxima) {
            municipios[cantidad++] = new Municipio(nombre);
        }
    }
    
    private int buscarIndice(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (municipios[i].nombre.equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public Municipio buscarMunicipio(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (municipios[i].nombre.equals(nombre)) {
                return municipios[i];
            }
        }
        return null;
    }

    public void agregarArista(String origenNombre, String destinoNombre, int distancia, boolean bidireccional) {
        Municipio origen = buscarMunicipio(origenNombre);
        Municipio destino = buscarMunicipio(destinoNombre);

        if (origen != null && destino != null) {
            origen.listaAdyacencia.agregar(destino, distancia);
            if (bidireccional) {
                destino.listaAdyacencia.agregar(origen, distancia);
            }
        }
    }
    
    public Municipio[] rutaMasCorta(String origenNombre, String destinoNombre) {
        int n = cantidad;
        int[] dist = new int[n];
        boolean[] visitado = new boolean[n];
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            visitado[i] = false;
            prev[i] = -1;
        }

        int origenIdx = buscarIndice(origenNombre);
        int destinoIdx = buscarIndice(destinoNombre);
        if (origenIdx == -1 || destinoIdx == -1) {
            return new Municipio[0];
        }

        dist[origenIdx] = 0;

        for (int count = 0; count < n; count++) {
            // Encuentra no visitado con distancia mínima
            int u = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!visitado[i] && dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }
            if (u == -1) break;
            visitado[u] = true;

            // Relaja aristas salientes
            Arista actual = municipios[u].listaAdyacencia.obtenerCabeza();
            while (actual != null) { 
                int v = buscarIndice(actual.destino.nombre);
                if (!visitado[v] && dist[u] + actual.distancia < dist[v]) {
                    dist[v] = dist[u] + actual.distancia;
                    prev[v] = u;
                }
                actual = actual.siguiente;
            }
        }

        // Reconstruye la ruta
        if (dist[destinoIdx] == Integer.MAX_VALUE) {
            return new Municipio[0]; // No hay ruta
        }

        // Cuenta size de la ruta
        int len = 0;
        for (int at = destinoIdx; at != -1; at = prev[at]) {
            len++;
        }

        Municipio[] ruta = new Municipio[len];
        int idx = len - 1;
        for (int at = destinoIdx; at != -1; at = prev[at]) {
            ruta[idx--] = municipios[at];
        }

        return ruta;
    }
    
    //Comprobador de rutas
    public ArregloDinamico<Ruta> buscarRutasCoincidentes(Municipio[] recorridoBuscado, ArregloDinamico<Ruta> rutas) {
        ArregloDinamico<Ruta> coincidentes = new ArregloDinamico<>();

        for (int i = 0; i < rutas.size(); i++) {
            Ruta actual = rutas.obtener(i);
            Municipio[] recorridoActual = actual.getRecorrido();

            if (contieneSubsecuencia(recorridoActual, recorridoBuscado)) {
                coincidentes.agregar(actual);
            }
        }

        return coincidentes;
    }
    
    //Comprobador Subrutas
    private boolean contieneSubsecuencia(Municipio[] recorridoActual, Municipio[] recorridoBuscado) {
        int i = 0, j = 0;

        while (i < recorridoActual.length && j < recorridoBuscado.length) {
            if (recorridoActual[i].getNombre().equalsIgnoreCase(recorridoBuscado[j].getNombre())) {
                j++;
            }
            i++;
        }

        return j == recorridoBuscado.length;
    }
    
    //Filtro por tipo de vehiculo
    public ArregloDinamico<Ruta> filtrarPorTipoVehiculo(ArregloDinamico<Ruta> rutas, String tipoVehiculoDeseado) {
        ArregloDinamico<Ruta> filtradas = new ArregloDinamico<>();

        for (int i = 0; i < rutas.size(); i++) {
            Ruta actual = rutas.obtener(i);
            if (actual.getTipoVehiculo().equalsIgnoreCase(tipoVehiculoDeseado)) {
                filtradas.agregar(actual);
            }
        }

        return filtradas;
    }
    
    public ArregloDinamico<Ruta> filtrarPorPrecio(ArregloDinamico<Ruta> rutas) {
        ArregloDinamico<Ruta> filtradas = new ArregloDinamico<>();
    
    
        Ruta[] temp = new Ruta[rutas.size()];
        for (int i = 0; i < rutas.size(); i++) {
            temp[i] = rutas.obtener(i);
        }
    
    
        for (int i = 0; i < temp.length - 1; i++) {
            for (int j = 0; j < temp.length - i - 1; j++) {
                if (temp[j].getPrecio() > temp[j + 1].getPrecio()) {
                    
                    Ruta aux = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = aux;
                }
            }
        }
    
    
        for (int i = 0; i < temp.length; i++) {
            filtradas.agregar(temp[i]);
        }
    
        return filtradas;
    }

    public ArregloDinamico<Ruta> filtrarPorHorario(ArregloDinamico<Ruta> rutas, String horarioDeseado) {
        ArregloDinamico<Ruta> filtradas = new ArregloDinamico<>();

        for (int i = 0; i < rutas.size(); i++) {
            Ruta actual = rutas.obtener(i);
            if (actual.getHorarioViaje().equalsIgnoreCase(horarioDeseado)) {
                filtradas.agregar(actual);
            }
        }

        return filtradas;
    }

    public void mostrarGrafo() {
        for (int i = 0; i < cantidad; i++) {
            Municipio m = municipios[i];
            System.out.print(m.nombre + " -> ");
            m.listaAdyacencia.mostrar();
            System.out.println();
        }
    }
}