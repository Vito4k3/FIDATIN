package Homepage.view;

import GestioneDottori.view.FrameDottori;
import GestionePazienti.view.InterfacciaPAZIENTI;
import GestionePrenotazioni.view.FramePrenotazioni;
import GestionePrescrizioni.view.SchermataPrescrizione;
import Homepage.Eventi.Evento;
import Login.view.FrameLogin;
import Homepage.Eventi.EventoEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class FrameHomepage extends JFrame {
    private InterfacciaHomepage interfacciaHomepage;
    private InterfacciaPAZIENTI panelPazienti;
    private FrameDottori panelDottori;
    private FramePrenotazioni panelPrenotazioni;
    private SchermataPrescrizione panelPrescrizioni;
    private JPanel panel;


    public FrameHomepage(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1200,750);
        setLocationRelativeTo(null);
        setTitle("Fidatin");

        panelPazienti = new InterfacciaPAZIENTI();
        panelDottori = new FrameDottori();
        panelPrenotazioni = new FramePrenotazioni();
        panelPrescrizioni = new SchermataPrescrizione();
        interfacciaHomepage = new InterfacciaHomepage();

        caricaDaFile();

        panelDottori.setDatabasePrenotazioni(panelPrenotazioni.getController().getDatabase());
        panelDottori.setDatabasePrescrizioni(panelPrescrizioni.getGestionePrescrizione());
        panelPazienti.setDatabasePrenotazioni(panelPrenotazioni.getController().getDatabase());
        panelPazienti.setDatabasePrescrizioni(panelPrescrizioni.getGestionePrescrizione());
        interfacciaHomepage.setDatabasePrenotazioni(panelPrenotazioni.getController().getDatabase());

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
                JButton pulsanteHomepage = e.getButtonHomepage();

                if(pulsantePremuto.equals(pulsantePrenotazione)){
                    panelPrenotazioni.getInterfacciaInserimento().aggiornaFile(panelDottori.getController().getDatabase(), panelPazienti.getDatabasePazienti());
                    panelPrenotazioni.getInterfacciaInserimentoAggiungi().aggiornaFile(panelDottori.getController().getDatabase(), panelPazienti.getDatabasePazienti());
                    cardLayout.show(cardPanel, "PRENOTAZIONI");
                }else if(pulsantePremuto.equals(pulsanteDottori)){
                    cardLayout.show(cardPanel, "DOTTORI");
                }else if(pulsantePremuto.equals(pulsantePrescrizioni)){
                    panelPrescrizioni.getInserimentoModifica().aggiornaFile(panelDottori.getController().getDatabase(), panelPazienti.getDatabasePazienti());
                    panelPrescrizioni.getInterfacciaInserimento().aggiornaFile(panelDottori.getController().getDatabase(), panelPazienti.getDatabasePazienti());
                    cardLayout.show(cardPanel, "PRESCRIZIONI");
                }else if(pulsantePremuto.equals(pulsantePazienti)){
                    cardLayout.show(cardPanel, "PAZIENTI");
                }else if(pulsantePremuto.equals(pulsanteHomepage)){
                    interfacciaHomepage.rimuoviPannelloScelta();
                }
            }
        });

        panelPrenotazioni.getTab().getButtonEsci().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interfacciaHomepage.setDatabasePrenotazioni(panelPrenotazioni.getController().getDatabase());
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

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Mostra un messaggio di conferma prima di chiudere la finestra
                int choice = JOptionPane.showConfirmDialog(getContentPane(), "Sei sicuro di voler uscire?", "Conferma", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    salvaSuFile();
                    System.exit(0);
                }
            }
        });


        // Aggiunge il panel al contentPane del JFrame
        getContentPane().add(panel);
        setVisible(true);
    }

    public void caricaDaFile(){
        try {
            panelPrenotazioni.getController().getDatabase().caricaDaFile();
            panelDottori.getController().getDatabase().caricaDaFile();
            panelPazienti.getDatabasePazienti().caricaDaFile();
            panelPrescrizioni.getGestionePrescrizione().caricaDaFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvaSuFile(){
        try {
            panelPrenotazioni.getController().getDatabase().salvaSuFile();
            panelDottori.getController().getDatabase().salvaSuFile();
            panelPazienti.getDatabasePazienti().salvaSuFile();
            panelPrescrizioni.getGestionePrescrizione().salvaSuFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    public JPanel getPanel(){
        return panel;
    }
}
