package Homepage.view;

import GestioneDottori.view.FrameDottori;
import GestionePazienti.view.InterfacciaPAZIENTI;
import GestionePrenotazioni.view.FramePrenotazioni;
import GestionePrescrizioni.SchermataPrescrizione;
import Homepage.Eventi.Evento;
import Login.view.FrameLogin;
import Style.InterfacciaTab;
import Homepage.Eventi.EventoEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FrameHomepage extends JFrame {
    private InterfacciaHomepage interfacciaHomepage;
    private JPanel panel;


    public FrameHomepage(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,750);
        setLocationRelativeTo(null);

        interfacciaHomepage = new InterfacciaHomepage();
        InterfacciaPAZIENTI panelPazienti = new InterfacciaPAZIENTI();
        FrameDottori panelDottori = new FrameDottori();
        FramePrenotazioni panelPrenotazioni = new FramePrenotazioni();
        SchermataPrescrizione panelPrescrizioni = new SchermataPrescrizione();

        // Crea il layout a card e aggiunge i pannelli al layout associandoli a stringhe univoche
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.add(interfacciaHomepage, "HOMEPAGE");
        cardPanel.add(panelPazienti, "PAZIENTI");
        cardPanel.add(panelDottori, "DOTTORI");
        cardPanel.add(panelPrenotazioni, "PRENOTAZIONI");
        cardPanel.add(panelPrescrizioni, "PRESCRIZIONI");

        // Imposta il layout manager del panel principale a un CardLayout
        JPanel panel = new JPanel(new CardLayout());
        // Aggiunge il cardPanel al panel principale
        panel.add(cardPanel, "CARDS");

        interfacciaHomepage.setEvento(new Evento() {
            @Override
            public void evento(EventoEvent e) {
                JButton pulsantePrenotazione = e.getButtonPrenotazioni();
                JButton pulsantePremuto = e.getPremuto();
                JButton pulsanteDottori = e.getButtonDottori();
                JButton pulsantePrescrizioni= e.getButtonPrescrizioni();
                JButton pulsantePazienti = e.getButtonPazienti();

                if(pulsantePremuto.equals(pulsantePrenotazione)){
                    panelPrenotazioni.getInterfacciaInserimento().aggiornaFileDottori();
                    cardLayout.show(cardPanel, "PRENOTAZIONI");
                }else if(pulsantePremuto.equals(pulsanteDottori)){
                    cardLayout.show(cardPanel, "DOTTORI");
                }else if(pulsantePremuto.equals(pulsantePrescrizioni)){
                    panelPrescrizioni.getInterfacciaInserimento().aggiornaFileDottori();
                    cardLayout.show(cardPanel, "PRESCRIZIONI");
                }else if(pulsantePremuto.equals(pulsantePazienti)){
                    cardLayout.show(cardPanel, "PAZIENTI");
                }
            }
        });

        panelPrenotazioni.getTab().getButtonEsci().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "HOMEPAGE");
            }
        });
        panelDottori.getTab().getButtonEsci().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "HOMEPAGE");
            }
        });
        panelPrescrizioni.getTab().getButtonEsci().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "HOMEPAGE");
            }
        });
        panelPazienti.getTab().getButtonEsci().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "HOMEPAGE");
            }
        });

        interfacciaHomepage.getTab().getButtonEsci().setText("ESCI");
        interfacciaHomepage.getTab().getButtonEsci().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameLogin frameLogin= new FrameLogin();
                frameLogin.setSize(getSize());
                frameLogin.setLocation(getLocation());
                dispose();
            }
        });

        // Aggiunge il panel al contentPane del JFrame
        getContentPane().add(panel);
        setVisible(true);
    }

    public JPanel getPanel(){
        return panel;
    }
}
