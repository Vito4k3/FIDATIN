package GestioneDottori.view;

import javax.swing.*;
import java.awt.*;

public class CambiaPannello {
    private JPanel cambiaPannello;
    CambiaPannello(){
        cambiaPannello = new JPanel(new CardLayout());


    }

    public void sostituisciPannello(JPanel panel1, JPanel panel2){
        cambiaPannello.add(panel1, "panel1");
        cambiaPannello.add(panel2, "panel2");
        CardLayout cardLayout = (CardLayout) cambiaPannello.getLayout();
        cardLayout.show(cambiaPannello, "panel2");

    }
}
