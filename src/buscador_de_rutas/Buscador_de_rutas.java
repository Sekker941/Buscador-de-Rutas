
package buscador_de_rutas;

import Clases.SistemaRegistro;
import GUI.mainFrame;
import java.io.IOException;

public class Buscador_de_rutas {

    public static void main(String[] args) throws IOException {
        SistemaRegistro sistema = new SistemaRegistro();
        mainFrame frame = new mainFrame();
        frame.setVisible(true);
    }
    
}
