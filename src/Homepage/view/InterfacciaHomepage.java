package Homepage.view;

import GestionePrenotazioni.model.DatabasePrenotazione;
import GestionePrenotazioni.model.Prenotazione;
import GestionePrenotazioni.view.FramePrenotazioni;
import GestionePrenotazioni.view.ModelloTabellaPrenotazioni;
import Homepage.Eventi.Evento;
import Homepage.Eventi.EventoEvent;
import Style.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class InterfacciaHomepage extends JPanel implements ActionListener {
    private JPanel bachecaPanel, gridContainer, prenotazioniGiornalierePanel, menuPanel, toolbarJp, containerJp;
    private JLabel labelHomepage = new JLabel("  HOMEPAGE");
    private JLabel labelGestione = new JLabel("  GESTIONE");
    private JLabel labelAmministrazione = new JLabel("  AMMINISTRAZIONE");
    private JButton buttonHomepage = new MyButtonStyle("DashBoard", Color.white, Color.black);
    private JButton buttonPazienti = new MyButtonStyle("Pazienti", Color.white, Color.black);
    private JButton buttonPrenotazioni = new MyButtonStyle("Prenotazioni", Color.white, Color.black);
    private JButton buttonPrescrizioni = new MyButtonStyle("Prescrizioni", Color.white, Color.black);
    private JButton buttonDottori = new MyButtonStyle("Dottori", Color.white, Color.black);
    private Evento evento;
    private InterfacciaTab interfacciaTab;
    private FramePrenotazioni framePrenotazioni= new FramePrenotazioni();
    private DefaultTableModel tableModel;
    private JTable prenotazioniDiOggi;
    private DatabasePrenotazione databasePrenotazione;
    private List<Prenotazione> prenotazioniGiornaliere;
    private String[] colonne;
    JTextArea textAreaBacheca;
    JPanel panel1, mainPanel;
    ModelloTabellaPrenotazioni modelloPrenotazioniGiornaliere;

    public InterfacciaHomepage(){
        init();
    }

    private void init(){

        setLayout(new BorderLayout());
        JPanel paginaHome = new JPanel(new GridLayout(1,2, 10, 10));    //panel che racchiude tabella prenotazioni e bacheca
        interfacciaTab = new InterfacciaTab("", 1);

        //Inizio configurazione bacheca
        bachecaPanel = new MyPanel(new GridLayout(2, 1, 10, 5));    //panel Bacheca
        bachecaPanel.setBorder(new EmptyBorder(5,5,5,10));
        textAreaBacheca = new MyTextArea();
        textAreaBacheca.setEditable(false);
        textAreaBacheca.setFocusable(false);
        textAreaBacheca.setText("Ampliamento della sede centrale\nSi rappresenta a quanti in indirizzo, ognuno per le proprie " +
                "competenze e responsabilità,\nche i lavori di ampliamento della sede sono giunti in fase più interferenziale\n" +
                "con la vita sanitaria\n\n" + "Nuovo primario! è subito una grande festa, sabato 10 maggio cornetti per tutti\n nella sala dottori!");
        JLabel labelBacheca = new JLabel("Bacheca");
        labelBacheca.setBorder(new EmptyBorder(0,5,0,0));
        JPanel panelBacheca = new MyPanel(new BorderLayout());
        panelBacheca.setBackground(Color.white);
        panelBacheca.setBorder(new EmptyBorder(5,5,5,5));
        MyScrollPane scrollPaneBacheca = new MyScrollPane(textAreaBacheca);
        scrollPaneBacheca.setPreferredSize(new Dimension(textAreaBacheca.getWidth(), textAreaBacheca.getHeight()));
        scrollPaneBacheca.setBackground(Color.white);
        textAreaBacheca.setBorder(new EmptyBorder(10,10,10,10));
        textAreaBacheca.setOpaque(false);
        panelBacheca.add(labelBacheca, BorderLayout.PAGE_START);
        panelBacheca.add(scrollPaneBacheca);
        bachecaPanel.add(panelBacheca);
        //Fine configurazione bacheca

        //Inizio configurazione tabella Prenotazioni giornaliere
        prenotazioniGiornalierePanel = new MyPanel(new GridLayout(2, 1, 10, 5));
        prenotazioniGiornaliere = framePrenotazioni.getController().getDatabase().prenotazioniGiornaliere();    //prenotazioni giornaliere
        modelloPrenotazioniGiornaliere = new ModelloTabellaPrenotazioni();
        modelloPrenotazioniGiornaliere.setListaPrenotazioni(prenotazioniGiornaliere);
        modelloPrenotazioniGiornaliere.setColumnCount(5);
        JTable tabellaPrenotazioniGiornaliere = new MyTableStyle(modelloPrenotazioniGiornaliere);   //tabella prenotazioni giornaliere
        MyScrollPane scrollPane = new MyScrollPane(tabellaPrenotazioniGiornaliere); //Scrollpane
        scrollPane.setBorder(new EmptyBorder(10,10,10,10));
        scrollPane.setOpaque(false);
        MyPanel panel = new MyPanel(new BorderLayout());    //pannello bianco per bordi dietro tabella
        panel.setBackground(Color.white);
        prenotazioniGiornalierePanel.setBorder(new EmptyBorder(5,10,10,0));
        JLabel labelPrenotazioniGiornaliere = new JLabel("Prenotazioni giornaliere");
        labelPrenotazioniGiornaliere.setBorder(new EmptyBorder(0,5,0,0));
        panel.add(labelPrenotazioniGiornaliere, BorderLayout.PAGE_START);
        panel.add(scrollPane);
        prenotazioniGiornalierePanel.add(panel);
        //Fine configurazione tabella prenotazioni giornaliere


        //Inizio configurazione menuPanel
        menuPanel = new MyPanel(new GridLayout(11,1, 10,10));
        menuPanel.setBackground(new Color(0x1A5690));
        menuPanel.setBorder(new EmptyBorder(0,10,0,10));

        labelHomepage.setForeground(Color.white);
        labelGestione.setForeground(Color.white);
        labelAmministrazione.setForeground(Color.white);
        MyButtonStyle logo = new MyButtonStyle(interfacciaTab.getLogo());

        menuPanel.add(labelHomepage);
        menuPanel.add(buttonHomepage);
        menuPanel.add(labelGestione);
        menuPanel.add(buttonPrenotazioni);
        menuPanel.add(buttonPrescrizioni);
        menuPanel.add(labelAmministrazione);
        menuPanel.add(buttonPazienti);
        menuPanel.add(buttonDottori);
        menuPanel.add(new JLabel(" "));
        menuPanel.add(logo);

        buttonHomepage.addActionListener(this);
        buttonPazienti.addActionListener(this);
        buttonPrenotazioni.addActionListener(this);
        buttonPrescrizioni.addActionListener(this);
        buttonDottori.addActionListener(this);

        interfacciaTab.getButtonIcon().addActionListener(e -> {
            if(mainPanel.getComponent(0)!=panel1) {
                mainPanel.add(panel1, BorderLayout.LINE_START, 0);
            }else {
                mainPanel.remove(panel1);
                remove(panel1);
            }
            revalidate();
            repaint();
        });


        panel1 = new MyPanel(new BorderLayout());
        panel1.setPreferredSize(new Dimension(300,1));
        panel1.setMaximumSize(new Dimension(400,1));
        panel1.setBorder(new EmptyBorder(20,10,20,10));
        panel1.add(menuPanel);

        paginaHome.add(prenotazioniGiornalierePanel);
        paginaHome.add(bachecaPanel);

        mainPanel = new JPanel(new BorderLayout());
        paginaHome.setBorder(new EmptyBorder(30,0,0,0));
        add(interfacciaTab, BorderLayout.PAGE_START);
        mainPanel.add(paginaHome, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);


    }


    public void setEvento(Evento e){
        this.evento = e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton premuto = (JButton) e.getSource();
        EventoEvent eventoEvent = new EventoEvent(this, buttonPrenotazioni, buttonDottori, buttonPrescrizioni, buttonPazienti, buttonHomepage, premuto);
        evento.evento(eventoEvent);
    }
    

    public InterfacciaTab getTab(){
        return interfacciaTab;
    }
    public void setDatabasePrenotazioni(DatabasePrenotazione database){
        this.databasePrenotazione = database;
        prenotazioniGiornaliere = databasePrenotazione.prenotazioniGiornaliere();
        modelloPrenotazioniGiornaliere.setListaPrenotazioni(prenotazioniGiornaliere);
        System.out.print(prenotazioniGiornaliere.get(0));
    }
    public void rimuoviPannelloScelta(){
        mainPanel.remove(panel1);
        remove(panel1);
        revalidate();
        repaint();
    }
}
