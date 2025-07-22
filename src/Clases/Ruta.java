
package Clases;

import java.time.LocalDate;
import java.time.LocalTime;

public class Ruta implements Comparable<Ruta> {
    private final int id;
    private final Municipio[] recorrido;
    private double precio;
    private String tipoVehiculo;
    private String horaSalida;     // Formato: "HH:mm"
    private int cantidadAsientos;
    private String fecha;          // Formato sugerido: "yyyy-MM-dd"

    public Ruta(int id, Municipio[] recorrido, double precio, String tipoVehiculo, String horaSalida, int cantidadAsientos, String fecha) {
        this.id = id;
        this.recorrido = recorrido;
        this.precio = precio;
        this.tipoVehiculo = tipoVehiculo;
        this.horaSalida = horaSalida;
        this.cantidadAsientos = cantidadAsientos;
        this.fecha = fecha;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public int compareTo(Ruta otra) {
        return this.horaSalida.compareTo(otra.horaSalida);
    }
}
