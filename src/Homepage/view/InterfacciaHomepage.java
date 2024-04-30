package Homepage.view;

import Homepage.Eventi.Evento;
import Homepage.Eventi.EventoEvent;
import Style.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InterfacciaHomepage extends JPanel implements ActionListener{

    final int larghezza = 800;
    final int altezza = 600;
    private JPanel containerJp;
    private JPanel toolbarJp;
    private JPanel southJp;
    private JLabel southJl;
    private JPanel ovestJp;
    private JPanel estJp;
    private JPanel centerJp;
    private JLabel lblTxtBenvenuto = new JLabel("FIDATIN");
    private JLabel homepage = new JLabel("  HOMEPAGE");
    private JLabel gestione = new JLabel("  GESTIONE");
    private JLabel amministrazione = new JLabel("  AMMINISTRAZIONE");
    private JButton DashBoard = new MyButtonStyle("DashBoard", Color.white, Color.black);
    private JButton Pazienti = new MyButtonStyle("Pazienti", Color.white, Color.black);
    private JButton Prenotazioni = new MyButtonStyle("Prenotazioni", Color.white, Color.black);
    private JButton Prescrizioni = new MyButtonStyle("Prescrizioni", Color.white, Color.black);
    private JButton Dottori = new MyButtonStyle("Dottori", Color.white, Color.black);
    private JButton Bacheca = new JButton("Bacheca");
    private Evento evento;
    private InterfacciaTab interfacciaTab;


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
        JPanel gridContainer = new JPanel(new GridLayout(1,3));
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

        interfacciaTab.getButtonIcon().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gridContainer.getComponent(0)!=ovestJp) {
                    gridContainer.add(ovestJp, 0);
                    revalidate();
                    repaint();
                }else {
                    gridContainer.remove(ovestJp);
                    remove(ovestJp);
                    revalidate();
                    repaint();
                }
            }
        });

        // EST
        estJp = new JPanel(new GridLayout(2, 1, 10, 5));
        JTextArea jtxprenotazioniDiOggi = new MyTextArea();
        estJp.add(jtxprenotazioniDiOggi);
        // add to container panel

        gridContainer.add(new MyScrollPane(estJp));


        centerJp = new JPanel(new GridLayout(2, 1, 10, 5));

        JTextArea jtxbacheca = new MyTextArea();
        centerJp.add(jtxbacheca);

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
        EventoEvent eventoEvent = new EventoEvent(this, Prenotazioni, Dottori, Prescrizioni, Pazienti, premuto);
        evento.evento(eventoEvent);
    }

    public InterfacciaTab getTab(){
        return interfacciaTab;
    }
}
