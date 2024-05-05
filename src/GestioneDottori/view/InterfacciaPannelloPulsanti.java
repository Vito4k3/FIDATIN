package GestioneDottori.view;


import Eventi.Evento;
import Eventi.EventoEvent;
import Style.MyButtonStyle;
import Style.InterfacciaTab;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfacciaPannelloPulsanti extends JPanel implements ActionListener {
    private JButton buttonModifica, buttonAggiungi, buttonElimina;
    private JTextField fieldRicerca;
    private InterfacciaTab interfacciaTab;
    private JCheckBox visualizzaDottoriAttivi;
    private Evento evento;

    InterfacciaPannelloPulsanti(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel panelOpzioni = new JPanel(new BorderLayout());
        JPanel panelCheckBox = new JPanel(new FlowLayout());
        JPanel panelGestione = new JPanel(new FlowLayout());
        JPanel panelRicerca = new JPanel(new FlowLayout());
        interfacciaTab = new InterfacciaTab("Gestione Dottori");
        interfacciaTab.setColor(new Color(0x800000));

        buttonModifica = new MyButtonStyle("Modifica", new Color(0x800000));
        buttonModifica.addActionListener(this);
        buttonAggiungi= new MyButtonStyle("Aggiungi", new Color(0x800000));
        buttonAggiungi.addActionListener(this);
        buttonElimina= new MyButtonStyle("Elimina", new Color(0x800000));
        buttonElimina.addActionListener(this);

        visualizzaDottoriAttivi = new JCheckBox("Visualizza dottori attivi");
        visualizzaDottoriAttivi.setFocusable(false);
        visualizzaDottoriAttivi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    //per attivare l'evento anche premendo la checkbox
                EventoEvent eventoEvent = new EventoEvent(this, buttonAggiungi, buttonModifica, buttonElimina, visualizzaDottoriAttivi, null);
                evento.evento(eventoEvent);
            }
        });


        fieldRicerca = new JTextField(15);
        fieldRicerca.setPreferredSize(new Dimension(140,30));
        fieldRicerca.setBorder(new EmptyBorder(0,5,0,0));
        Border lineBorder = new LineBorder(Color.black, 2);
        Border emptyBorder = new EmptyBorder(0,0,0,30);
        Border compoundBorder = new CompoundBorder(lineBorder, emptyBorder);
        fieldRicerca.setBorder(compoundBorder);

        panelCheckBox.add(visualizzaDottoriAttivi);
        panelRicerca.add(fieldRicerca);
        panelGestione.add(buttonElimina);
        panelGestione.add(buttonModifica);
        panelGestione.add(buttonAggiungi);

        panelCheckBox.setBorder(new EmptyBorder(20,0,0,0));

        panelOpzioni.add(panelRicerca, BorderLayout.LINE_START);
        panelOpzioni.add(panelGestione, BorderLayout.LINE_END);
        panelOpzioni.add(Box.createHorizontalGlue());

        FlowLayout flowLayout2 = (FlowLayout) panelCheckBox.getLayout();
        flowLayout2.setAlignment(FlowLayout.LEFT);

        setBorder(new EmptyBorder(0,8,0,8));

        add(interfacciaTab);
        add(panelCheckBox);
        add(panelOpzioni);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton premuto = (JButton) e.getSource();
        EventoEvent eventoEvent = new EventoEvent(this, buttonAggiungi, buttonModifica, buttonElimina, visualizzaDottoriAttivi, premuto);

        evento.evento(eventoEvent);
    }

    public void setEvento(Evento e){
        this.evento = e;
    }

    public JTextField getFieldRicerca(){
        return fieldRicerca;
    }

    public InterfacciaTab getInterfacciaTab(){
        return this.interfacciaTab;
    }
}

