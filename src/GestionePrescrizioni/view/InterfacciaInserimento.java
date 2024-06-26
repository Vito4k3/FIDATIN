package GestionePrescrizioni.view;

import GestioneDottori.model.DatabaseDottori;
import GestioneDottori.model.Dottore;
import GestionePazienti.model.DatabasePazienti;
import GestionePazienti.model.Paziente;
import Style.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;


public class InterfacciaInserimento extends JPanel {
    private JPanel mainPanel, panelFlowTitle, panelFlow1, panelFlow2, panelGrid, panelBox1, panelBox2, panelBox3;
    private JLabel title;
    private MyLabelStyle labelOggettoPrescrizione, labelPaziente, labelDottore;
    private MyTextArea textAreaOggetto;
    private MyComboBoxPaziente sceltaPaziente;
    private JButton buttonSalva;
    private DatabaseDottori databaseDottori;
    private DatabasePazienti databasePazienti;
    private ArrayList<Paziente> listaPazienti;
    private ArrayList<Dottore> listaDottori;
    private MyComboBoxDottori sceltaDottore;

    public void aggiornaFile(DatabaseDottori databaseDottori, DatabasePazienti databasePazienti){
        listaDottori.clear();
        listaDottori.addAll(databaseDottori.getDottori());

        sceltaDottore.sostituisciLista(listaDottori);

        listaPazienti.clear();
        listaPazienti.addAll(databasePazienti.getPazienti());

        sceltaPaziente.sostituisciLista(listaPazienti);
    }
    public InterfacciaInserimento(){
        setLayout(new BorderLayout());
        setBackground(Color.white);

        databaseDottori = new DatabaseDottori();
        databasePazienti = new DatabasePazienti();


        listaPazienti = new ArrayList<>(databasePazienti.getPazienti());
        listaDottori = new ArrayList<>(databaseDottori.getDottori());

        // Inizializzazione panel
        mainPanel = new JPanel();
        panelBox1= new JPanel();
        panelBox2 = new JPanel();
        panelBox3 = new JPanel();
        panelGrid = new JPanel(new BorderLayout());

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        panelFlowTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFlow1 = new JPanel(new FlowLayout());
        panelFlow2 = new JPanel(new FlowLayout());
        panelBox1.setLayout(new BoxLayout(panelBox1, BoxLayout.Y_AXIS));
        panelBox2.setLayout(new BoxLayout(panelBox2, BoxLayout.Y_AXIS));
        panelBox3.setLayout(new BoxLayout(panelBox3, BoxLayout.Y_AXIS));

        // Inizializzazione label
        title = new JLabel("Crea Prescrizione");
        panelFlowTitle.add(title);

        labelPaziente = new MyLabelStyle("Paziente:");
        labelDottore = new MyLabelStyle("Dottore:");

        labelOggettoPrescrizione = new MyLabelStyle("Oggetto:");
        labelOggettoPrescrizione.setBorder(new EmptyBorder(0,0,10,0));
        textAreaOggetto = new MyTextArea();

        // Inizializzazione dei campi di testo

        sceltaDottore = new MyComboBoxDottori(listaDottori);
        sceltaPaziente = new MyComboBoxPaziente(listaPazienti);

        // Inizializzazione del pulsante
        buttonSalva = new MyButtonStyle("Salva", new Color(0x1A5690));

        // Aggiunta delle etichette e dei campi di testo ai pannelli


        panelBox3.setPreferredSize(new Dimension(200,50));
        panelBox1.setPreferredSize(new Dimension(305,50));
        panelBox2.setPreferredSize(new Dimension(300,50));
        panelBox3.setPreferredSize(new Dimension(200,200));
        panelBox3.setBorder(new EmptyBorder(20,30,20,30));
        buttonSalva.setPreferredSize(new Dimension(80,40));
        title.setFont(new Font(null, Font.BOLD, 20));

        panelBox1.add(labelPaziente);
        panelBox1.add(sceltaPaziente);
        panelBox2.add(labelDottore);
        panelBox2.add(sceltaDottore);
        panelFlow1.add(panelBox1);
        panelFlow1.add(panelBox2);

        panelBox3.add(labelOggettoPrescrizione);
        panelBox3.add(new MyScrollPane(textAreaOggetto));
        panelGrid.add(panelBox3);

        panelFlow2.add(buttonSalva);


        // Aggiunta dei pannelli al pannello principale
        mainPanel.add(panelFlowTitle, FlowLayout.LEFT);
        mainPanel.add(panelFlow1);
        mainPanel.add(panelGrid);
        mainPanel.add(panelFlow2);

        // Aggiunta del pannello principale al frame
        add(mainPanel);

        setVisible(true);
        setSize(700,500);
    }

    public MyTextArea getTextAreaOggetto() {
        return textAreaOggetto;
    }

    public void setTextAreaOggetto(MyTextArea textAreaOggetto) {
        this.textAreaOggetto = textAreaOggetto;
    }

    public MyComboBoxPaziente getSceltaPaziente() {
        return sceltaPaziente;
    }

    public void setSceltaPaziente(MyComboBoxPaziente sceltaPaziente) {
        this.sceltaPaziente = sceltaPaziente;
    }

    public void setButtonSalva(JButton buttonSalva) {
        this.buttonSalva = buttonSalva;
    }

    public MyComboBoxDottori getSceltaDottore() {
        return sceltaDottore;
    }

    public void setSceltaDottore(MyComboBoxDottori sceltaDottore) {
        this.sceltaDottore = sceltaDottore;
    }

    public JButton getButtonSalva(){
        return this.buttonSalva;
    }

}
