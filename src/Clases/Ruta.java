
package Clases;

import java.time.LocalTime;

public class Ruta implements Comparable<Ruta> {
    private final int id;                 
    private final Municipio[] recorrido;  
    private double precio;                
    private String tipoVehiculo;          
    private String horaSalida;            // Formato: "HH:mm"
    private int cantidadAsientos;         

    public Ruta(int id, Municipio[] recorrido, double precio, String tipoVehiculo, String horaSalida, int cantidadAsientos) {
        this.id = id;
        this.recorrido = recorrido;
        this.precio = precio;
        this.tipoVehiculo = tipoVehiculo;
        this.horaSalida = horaSalida;
        this.cantidadAsientos = cantidadAsientos;
    }

    public int getId() {
        return id;
    }

    public Municipio[] getRecorrido() {
        return recorrido;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public void setCantidadAsientos(int cantidadAsientos) {
        this.cantidadAsientos = cantidadAsientos;
    }

    @Override
    public int compareTo(Ruta otra) {
        LocalTime t1 = LocalTime.parse(this.horaSalida);
        LocalTime t2 = LocalTime.parse(otra.horaSalida);
        return t1.compareTo(t2);
    }
}
