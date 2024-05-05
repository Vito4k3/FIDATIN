package Homepage.view;

import GestionePrenotazioni.model.DatabasePrenotazione;
import GestionePrenotazioni.model.Prenotazione;
import GestionePrenotazioni.view.FramePrenotazioni;
import Homepage.Eventi.Evento;
import Homepage.Eventi.EventoEvent;
import Style.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class InterfacciaHomepage extends JPanel implements ActionListener{
    private JPanel centerJp, gridContainer, estJp, ovestJp, toolbarJp, containerJp;
    private JLabel homepage = new JLabel("  HOMEPAGE");
    private JLabel gestione = new JLabel("  GESTIONE");
    private JLabel amministrazione = new JLabel("  AMMINISTRAZIONE");
    private JButton DashBoard = new MyButtonStyle("DashBoard", Color.white, Color.black);
    private JButton Pazienti = new MyButtonStyle("Pazienti", Color.white, Color.black);
    private JButton Prenotazioni = new MyButtonStyle("Prenotazioni", Color.white, Color.black);
    private JButton Prescrizioni = new MyButtonStyle("Prescrizioni", Color.white, Color.black);
    private JButton Dottori = new MyButtonStyle("Dottori", Color.white, Color.black);
    private Evento evento;
    private InterfacciaTab interfacciaTab;
    private FramePrenotazioni framePrenotazioni= new FramePrenotazioni();
    private DefaultTableModel tableModel;
    private JTable prenotazioniDiOggi;
    private DatabasePrenotazione databasePrenotazione;
    private List<Prenotazione> prenotazioniGiornaliere;
    private String[] colonne;

    public InterfacciaHomepage(){
        init();
    }

    private void init(){

        setLayout(new BorderLayout());

        Font font = new Font(null, Font.BOLD, 15);
        homepage.setFont(font);
        homepage.setForeground(Color.white);
        amministrazione.setBorder(new EmptyBorder(20,0,0,0));
        amministrazione.setFont(font);
        amministrazione.setForeground(Color.white);
        gestione.setFont(font);
        gestione.setForeground(Color.white);
        gestione.setBorder(new EmptyBorder(20,0,0,0));

        containerJp = new JPanel(new BorderLayout(20, 10));
        gridContainer = new JPanel(new GridLayout(1,3));
        gridContainer.setBorder(new EmptyBorder(0,0,20,0));
        interfacciaTab = new InterfacciaTab("FIDATIN", 1);
        interfacciaTab.setColor(new Color(11, 103, 164));

        // NORTH
        toolbarJp = new JPanel(new BorderLayout());
        toolbarJp.setBorder(new EmptyBorder(0,0,10,0));
        toolbarJp.add(interfacciaTab);


        containerJp.add(toolbarJp, BorderLayout.NORTH);

        // OVEST:
        ovestJp = new MyPanel(new GridLayout(11,1,10,10));
        ovestJp.setBorder(new EmptyBorder(0,10,0,10));

        ovestJp.setBackground(new Color(11, 103, 164));
        ovestJp.add(homepage);
        ovestJp.add(DashBoard);
        ovestJp.add(gestione);
        ovestJp.add(Pazienti);
        ovestJp.add(Prenotazioni);
        ovestJp.add(Prescrizioni);
        ovestJp.add(amministrazione);
        ovestJp.add(Dottori);

        DashBoard.addActionListener(this);
        Pazienti.addActionListener(this);
        Prenotazioni.addActionListener(this);
        Prescrizioni.addActionListener(this);
        Dottori.addActionListener(this);

        //gridContainer.add(ovestJp);

        interfacciaTab.getButtonIcon().addActionListener(e -> {
            if(gridContainer.getComponent(0)!=ovestJp) {
                gridContainer.add(ovestJp, 0);
            }else {
                gridContainer.remove(ovestJp);
                remove(ovestJp);
            }
            revalidate();
            repaint();
        });

        // EST
        estJp = new MyPanel(new GridLayout(2, 1, 10, 5));

        prenotazioniGiornaliere = framePrenotazioni.getController().getDatabase().prenotazioniGiornaliere();

        colonne = new String[]{"Paziente", "Dottore", "Orario", "Reparto", "Tipo"};

        tableModel = new DefaultTableModel(colonne, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public int getRowCount() {
                return prenotazioniGiornaliere.size();
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Prenotazione prenotazione= prenotazioniGiornaliere.get(rowIndex);
                switch(columnIndex){
                    case 0:
                        return prenotazione.getPaziente().getNome() + " " + prenotazione.getPaziente().getCognome();
                    case 1:
                        return prenotazione.getDottore().getNome() + " " + prenotazione.getDottore().getCognome();
                    case 2:
                        return prenotazione.getOra();
                    case 3:
                        return prenotazione.getReparto();
                    case 4:
                        return prenotazione.getTipoPrenotazione();
                    default:
                        return null;
                }
            }

        };
        prenotazioniDiOggi = new MyTableStyle(tableModel);
        if(!prenotazioniGiornaliere.isEmpty()){
            prenotazioniDiOggi.getColumnModel().getColumn(1).setPreferredWidth(100);
            prenotazioniDiOggi.getColumnModel().getColumn(2).setPreferredWidth(25);
        }
        MyScrollPane scrollPane = new MyScrollPane(prenotazioniDiOggi);
        scrollPane.setBorder(new EmptyBorder(10,10,10,10));
        scrollPane.setOpaque(false);
        MyPanel panel = new MyPanel(new BorderLayout());
        panel.setBackground(Color.white);
        estJp.setBorder(new EmptyBorder(5,10,10,0));
        JLabel labelPrenotazioniGiornaliere = new JLabel("Prenotazioni giornaliere");
        labelPrenotazioniGiornaliere.setBorder(new EmptyBorder(0,5,0,0));
        panel.add(labelPrenotazioniGiornaliere, BorderLayout.PAGE_START);
        panel.add(scrollPane);
        estJp.add(panel);
        // add to container panel


        gridContainer.add(estJp);

        centerJp = new JPanel(new GridLayout(2, 1, 10, 5));

        JTextArea bacheca = new MyTextArea();
        JLabel labelBacheca = new JLabel("Bacheca");
        labelBacheca.setBorder(new EmptyBorder(0,5,0,0));

        JPanel panelBacheca = new MyPanel(new BorderLayout());
        panelBacheca.setBackground(Color.white);
        panelBacheca.setBorder(new EmptyBorder(5,5,5,5));
        MyScrollPane scrollPaneBacheca = new MyScrollPane(bacheca);
        scrollPaneBacheca.setBackground(Color.white);
        scrollPane.setBorder(new EmptyBorder(10,10,10,10));
        scrollPane.setOpaque(false);


        panelBacheca.add(labelBacheca, BorderLayout.PAGE_START);
        panelBacheca.add(scrollPaneBacheca);
        centerJp.add(panelBacheca);

        gridContainer.add(new MyScrollPane(centerJp));

        containerJp.add(gridContainer, BorderLayout.CENTER);
        add(containerJp);

    }

    public void setEvento(Evento e){
        this.evento = e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton premuto = (JButton) e.getSource();
        EventoEvent eventoEvent = new EventoEvent(this, Prenotazioni, Dottori, Prescrizioni, Pazienti, DashBoard, premuto);
        evento.evento(eventoEvent);
    }

    public InterfacciaTab getTab(){
        return interfacciaTab;
    }
    public void setDatabasePrenotazioni(DatabasePrenotazione database){
        this.databasePrenotazione = database;
        prenotazioniGiornaliere = databasePrenotazione.prenotazioniGiornaliere();
    }
    public void rimuoviPannelloScelta(){
        gridContainer.remove(ovestJp);
        remove(ovestJp);
        revalidate();
        repaint();
    }
}
