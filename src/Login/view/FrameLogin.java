package Login.view;

/*
    Coded by Vito Francesco Cosola
 */

import Homepage.view.FrameHomepage;
import Login.Eventi.Evento;
import Login.Eventi.EventoEvent;
import Login.model.IDandPassword;

import javax.swing.*;
import java.awt.*;

public class FrameLogin extends JFrame {
    private IDandPassword iDandPassword;

    public FrameLogin(){
        setLayout(new GridLayout(1,2));
        iDandPassword = new IDandPassword();
        InterfacciaLogin interfacciaLogin = new InterfacciaLogin();
        PanelloLogo pannelloLogo= new PanelloLogo();

        interfacciaLogin.setEvento(ev -> {
            String insertId = ev.getInsertId();
            String insertPassword = ev.getInsertPassword();
            JLabel labelMessaggi = ev.getLabelMessaggi();

            if(insertId.isEmpty() || insertPassword.isEmpty() ){
                labelMessaggi.setForeground(Color.red);
                labelMessaggi.setText("Devi compilare tutti i campi!");
            }else if(iDandPassword.getLoginData().containsKey(insertId)){
                if(iDandPassword.getLoginData().get(insertId).equals(insertPassword)){
                    labelMessaggi.setForeground(Color.green);
                    labelMessaggi.setText("Accesso in corso...");
                    FrameHomepage frameHomepage= new FrameHomepage();
                    frameHomepage.setLocation(getLocation());
                    frameHomepage.setSize(getSize());
                    dispose();
                }else{
                    labelMessaggi.setForeground(Color.red);
                    labelMessaggi.setText("Password sbagliata! ");
                }
            }else{
                labelMessaggi.setForeground(Color.red);
                labelMessaggi.setText("Username sbagliato! ");
            }
        });



        add(pannelloLogo);
        add(interfacciaLogin);

        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Login");
        setVisible(true);
    }


}