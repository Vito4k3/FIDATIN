package Homepage.view;

import GestioneDottori.view.FrameDottori;
import GestionePazienti.view.InterfacciaPAZIENTI;
import GestionePrenotazioni.view.FramePrenotazioni;
import GestionePrescrizioni.view.SchermataPrescrizione;
import Homepage.GestioneFile;
import Login.view.FrameLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class FrameHomepage extends JFrame {
    private InterfacciaHomepage interfacciaHomepage;
    private InterfacciaPAZIENTI panelPazienti;
    private FrameDottori panelDottori;
    private FramePrenotazioni panelPrenotazioni;
    private SchermataPrescrizione panelPrescrizioni;
    private ImageIcon icon = new ImageIcon("src/Icon/logoFinestra.png");

    private GestioneFile gestioneFile = new GestioneFile();

    public FrameHomepage(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Fidatin");
        setIconImage(icon.getImage());

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

        interfacciaHomepage.setEvento(e -> {
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
        });

        panelPrenotazioni.getTab().getButtonEsci().addActionListener(e -> {
            interfacciaHomepage.setDatabasePrenotazioni(panelPrenotazioni.getController().getDatabase());
            cardLayout.show(cardPanel, "HOMEPAGE");
        });
        panelDottori.getTab().getButtonEsci().addActionListener(e -> {
            interfacciaHomepage.setDatabasePrenotazioni(panelPrenotazioni.getController().getDatabase());
            cardLayout.show(cardPanel, "HOMEPAGE");
        });
        panelPrescrizioni.getTab().getButtonEsci().addActionListener(e -> cardLayout.show(cardPanel, "HOMEPAGE"));
        panelPazienti.getTab().getButtonEsci().addActionListener(e -> {
            interfacciaHomepage.setDatabasePrenotazioni(panelPrenotazioni.getController().getDatabase());
            cardLayout.show(cardPanel, "HOMEPAGE");
        });

        interfacciaHomepage.getTab().getButtonEsci().setText("ESCI");
        interfacciaHomepage.getTab().getButtonEsci().addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(getContentPane(), "Sei sicuro di voler tornare al login?", "Conferma", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                salvaSuFile();
                FrameLogin frameLogin = new FrameLogin();
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
                    System.exit(0);
                }
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread() {     //in caso di chiusura improvvisa salva i file per evitare di perdere salvataggi
            public void run() {
               salvaSuFile();
            }
        });


        // Aggiunge il panel al contentPane del JFrame
        getContentPane().add(panel);
        setVisible(true);
    }

    public void caricaDaFile(){
        try {
            panelPrenotazioni.getController().getDatabase().caricaDaFile(gestioneFile.getPath());
            panelDottori.getController().getDatabase().caricaDaFile(gestioneFile.getPath());
            panelPazienti.getDatabasePazienti().caricaDaFile(gestioneFile.getPath());
            panelPrescrizioni.getGestionePrescrizione().caricaDaFile(gestioneFile.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvaSuFile(){
        try {
            panelPrenotazioni.getController().getDatabase().salvaSuFile(gestioneFile.getPath());
            panelDottori.getController().getDatabase().salvaSuFile(gestioneFile.getPath());
            panelPazienti.getDatabasePazienti().salvaSuFile(gestioneFile.getPath());
            panelPrescrizioni.getGestionePrescrizione().salvaSuFile(gestioneFile.getPath());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
