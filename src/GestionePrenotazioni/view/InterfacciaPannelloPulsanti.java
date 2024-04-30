package GestionePrenotazioni.view;


import Style.InterfacciaTab;
import Style.MyButtonStyle;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Eventi.Evento;
import Eventi.EventoEvent;

public class InterfacciaPannelloPulsanti extends JPanel implements ActionListener {
    private JButton buttonModifica, buttonAggiungi, buttonOrdina, buttonElimina;
    private JTextField fieldRicerca;
    private InterfacciaTab interfacciaTab;
    private Evento evento;
    private InterfacciaPannelloPulsanti interfacciaPannelloPulsanti;

    InterfacciaPannelloPulsanti(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel panelOrdinamento = new JPanel(new FlowLayout());
        JPanel panelGestione = new JPanel(new FlowLayout());
        JPanel panelRicerca = new JPanel(new FlowLayout());
        JPanel panelOpzioni = new JPanel(new BorderLayout());
        interfacciaTab = new InterfacciaTab("Gestione Prenotazioni");

        buttonModifica = new MyButtonStyle("Modifica", new Color(0x1A5690));
        buttonModifica.addActionListener(this);
        buttonAggiungi= new MyButtonStyle("Aggiungi", new Color(0x1A5690));
        buttonAggiungi.addActionListener(this);
        buttonOrdina= new MyButtonStyle("Ordina", new Color(0x1A5690));
        buttonOrdina.addActionListener(this);
        buttonElimina= new MyButtonStyle("Elimina", new Color(0x1A5690));
        buttonElimina.addActionListener(this);

        fieldRicerca = new JTextField(15);
        fieldRicerca.setPreferredSize(new Dimension(140,30));
        Border lineBorder = new LineBorder(Color.black, 2);
        Border emptyBorder = new EmptyBorder(0,0,0,30);
        Border compoundBorder = new CompoundBorder(lineBorder, emptyBorder);
        fieldRicerca.setBorder(compoundBorder);

        panelOrdinamento.add(buttonOrdina);
        panelRicerca.add(fieldRicerca);
        panelGestione.add(buttonElimina);
        panelGestione.add(buttonModifica);
        panelGestione.add(buttonAggiungi);

        panelOpzioni.add(panelRicerca, BorderLayout.LINE_START);
        panelOpzioni.add(panelGestione, BorderLayout.LINE_END);
        panelOpzioni.add(Box.createHorizontalGlue());

        FlowLayout flowLayout2 = (FlowLayout) panelOrdinamento.getLayout();
        flowLayout2.setAlignment(FlowLayout.RIGHT);

        panelOrdinamento.setBorder(new EmptyBorder(25,0,0,0));
        panelOpzioni.setBorder(new EmptyBorder(0,0,0,0));

        setBorder(new EmptyBorder(0,8,0,8));

        add(interfacciaTab);
        add(panelOrdinamento);
        add(panelOpzioni);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton premuto = (JButton) e.getSource();
        EventoEvent eventoEvent = new EventoEvent(this, buttonAggiungi, buttonModifica, buttonElimina, buttonOrdina, premuto);

        evento.evento(eventoEvent);
    }

    public void setEvento(Evento e){
        this.evento = e;
    }

    public JTextField getFieldRicerca(){
        return this.fieldRicerca;
    }
    public InterfacciaTab getInterfacciaTab(){
        return this.interfacciaTab;
    }
}

