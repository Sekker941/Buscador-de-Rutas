
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
    private String horarioViaje;
    private String fecha;          // Formato: "yyyy-MM-dd"


    public Ruta(int id, Municipio[] recorrido, double precio, String tipoVehiculo, String horarioViaje, String horaSalida, int cantidadAsientos, String fecha) {
        this.id = id;
        this.recorrido = recorrido;
        this.precio = precio;
        this.tipoVehiculo = tipoVehiculo;
        this.horaSalida = horaSalida;
        this.horarioViaje = horarioViaje;
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

    public String getHorarioViaje() {
        return horarioViaje;
    }

    public void setHorarioViaje(String horarioViaje) {
        this.horarioViaje = horarioViaje;
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
    
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(";")
          .append(precio).append(";")
          .append(tipoVehiculo).append(";")
          .append(horarioViaje).append(";")
          .append(horaSalida).append(";")
          .append(cantidadAsientos).append(";")
          .append(fecha).append(";");

        for (int i = 0; i < recorrido.length; i++) {
            sb.append(recorrido[i].getNombre());
            if (i < recorrido.length - 1) sb.append("|");
        }
        return sb.toString();
    }
    
    public static Ruta fromCSV(String linea) {
        // limit 8 para no rebanar nombres de municipios
        String[] p = linea.split(";", 8);
        if (p.length != 8) return null;
        int id = Integer.parseInt(p[0]);
        double precio = Double.parseDouble(p[1]);
        String tipo = p[2];
        String horarioViaje = p[3];
        String horaSalida = p[4];
        int asientos = Integer.parseInt(p[5]);
        String fecha = p[6];
        String[] muniNombres = p[7].split("\\|");
        Municipio[] rec = new Municipio[muniNombres.length];
        for (int i = 0; i < muniNombres.length; i++) {
            rec[i] = new Municipio(muniNombres[i]);
        }
        return new Ruta(id, rec, precio, tipo, horarioViaje, horaSalida, asientos, fecha);
    }

    @Override
    public int compareTo(Ruta otra) {
        return this.horaSalida.compareTo(otra.horaSalida);
    }
}
