package GestionePrenotazioni.view;

import GestioneDottori.model.DatabaseDottori;
import GestioneDottori.model.Dottore;
import GestionePazienti.model.Paziente;
import GestionePrenotazioni.model.Reparto;
import GestionePrenotazioni.model.TipoPrenotazione;
import Style.MyButtonStyle;
import Style.MyLabelStyle;
import Style.MyComboBox;
import Style.MyComboBoxDottori;
import GestionePazienti.model.GestionePazienti;
import Style.MyComboBoxPaziente;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class InterfacciaInserimento extends JPanel {

    private JPanel mainPanel, panelFlowTitle, panelFlow1, panelFlow2, panelFlow4, panelFlow5, panelBox1, panelBox2, panelBox3,
            panelBox4, panelBox5, panelBox6;
    private JLabel title, labelTipologiaAppuntamento, labelPaziente, labelDottore, labelReparto, labelData, labelOra;
    private JComboBox boxTipoPrenotazione, boxReparto;
    private MyComboBoxPaziente sceltaPaziente;
    private JButton buttonSalva;
    private SpinnerModel spinnerDateModel;
    private JSpinner spinner , timeSpinner;
    private Calendar calendar;
    private Date now, startDate, endDate;
    private SpinnerDateModel timeModel;
    //
    private ArrayList<Paziente> listaPazienti;
    private DatabaseDottori databaseDottori;
    private ArrayList<Dottore> listaDottori;
    private GestionePazienti gestionePazienti;
    private MyComboBoxDottori sceltaDottore;
    //
    private JSpinner.DateEditor editor;
    private File fileDatabaseDottori = new File(System.getProperty("user.home"), "databaseDottori.dat");
    private File fileDatabasePazienti = new File(System.getProperty("user.home"), "databasePazienti.dat");

    public void aggiornaFileDottori(){
        try {
            databaseDottori.caricaDaFile(fileDatabaseDottori);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        listaDottori.clear();
        listaDottori.addAll(databaseDottori.getDottori());

        sceltaDottore.sostituisciLista(listaDottori);
    }
    public InterfacciaInserimento(){
        setLayout(new BorderLayout());
        setBackground(Color.white);

        gestionePazienti = new GestionePazienti();
        databaseDottori = new DatabaseDottori();

        try {
            gestionePazienti.caricaDaFile(fileDatabasePazienti);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        listaPazienti = new ArrayList<>(gestionePazienti.getPazienti());
        listaDottori = new ArrayList<>(databaseDottori.getDottori());

        // Inizializzazione panel
        mainPanel = new JPanel();
        panelBox1= new JPanel();
        panelBox2= new JPanel();
        panelBox3= new JPanel();
        panelBox4= new JPanel();
        panelBox5= new JPanel();
        panelBox6= new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        panelFlowTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFlow1 = new JPanel(new FlowLayout());
        panelFlow2 = new JPanel(new FlowLayout());
        panelFlow4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelFlow5 = new JPanel(new FlowLayout());
        panelBox1.setLayout(new BoxLayout(panelBox1, BoxLayout.Y_AXIS));
        panelBox2.setLayout(new BoxLayout(panelBox2, BoxLayout.Y_AXIS));
        panelBox3.setLayout(new BoxLayout(panelBox3, BoxLayout.Y_AXIS));
        panelBox4.setLayout(new BoxLayout(panelBox4, BoxLayout.Y_AXIS));
        panelBox5.setLayout(new BoxLayout(panelBox5, BoxLayout.Y_AXIS));
        panelBox6.setLayout(new BoxLayout(panelBox6, BoxLayout.Y_AXIS));

        // Inizializzazione label
        title = new JLabel("Inserisci Prenotazione");
        labelTipologiaAppuntamento = new MyLabelStyle("Tipologia Appuntamento:");
        labelPaziente = new MyLabelStyle("Paziente:");
        labelDottore = new MyLabelStyle("Dottore:");
        labelReparto = new MyLabelStyle("Reparto:");
        labelData = new MyLabelStyle("Data:");
        labelOra = new MyLabelStyle("Ora:");

        TipoPrenotazione[] tipiPrenotazioni = TipoPrenotazione.values();
        Reparto[] reparti = Reparto.values();

        // Inizializzazione dei campi di testo
        boxTipoPrenotazione = new MyComboBox(tipiPrenotazioni);

        sceltaPaziente = new MyComboBoxPaziente(listaPazienti);
        sceltaDottore = new MyComboBoxDottori(listaDottori);

        boxReparto = new MyComboBox(reparti);

        // Inizializzazione del pulsante
        buttonSalva = new MyButtonStyle("Salva", new Color(0x1A5690));

        // Aggiunta delle etichette e dei campi di testo ai pannelli
        panelFlowTitle.add(title);

        panelBox6.add(labelTipologiaAppuntamento);
        panelBox6.add(boxTipoPrenotazione);

        panelFlow1.add(panelBox6);

        panelBox6.setPreferredSize(new Dimension(610,50));
        panelBox3.setPreferredSize(new Dimension(200,50));
        panelBox4.setPreferredSize(new Dimension(200,50));
        panelBox5.setPreferredSize(new Dimension(200,50));
        panelBox1.setPreferredSize(new Dimension(305,50));
        panelBox2.setPreferredSize(new Dimension(300,50));
        buttonSalva.setPreferredSize(new Dimension(80,40));
        title.setFont(new Font(null, Font.BOLD, 20));

        calendar = Calendar.getInstance();
        now= calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        startDate= calendar.getTime();
        calendar.add(Calendar.YEAR, 3);
        endDate=calendar.getTime();

        spinnerDateModel = new SpinnerDateModel(now, startDate, endDate, Calendar.YEAR);

        spinner  = new JSpinner(spinnerDateModel);
        String format = "dd MMM yy";

        editor = new JSpinner.DateEditor(spinner, format);
        spinner.setEditor(editor);
        spinner.setPreferredSize(new Dimension(140, 25));
        spinner.setBorder(new LineBorder(new Color(0x1A5690), 3));

        editor.getTextField().addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                try {
                    // Prende il valore dallo spinner e lo converte in una data
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy", Locale.ITALIAN);
                    Date date = dateFormat.parse(editor.getTextField().getText());
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yy", Locale.ITALIAN);
                    String dataString = dateFormat2.format(date);

                    editor.commitEdit();
                    setSpinner(dataString);

                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        timeModel = new SpinnerDateModel();
        timeModel.setCalendarField(Calendar.MINUTE);

        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);

        timeEditor.getTextField().addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                try {
                    // Prende il valore dallo spinner e lo converte in una data
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.ITALIAN);
                    String time = dateFormat.format(dateFormat.parse(timeEditor.getTextField().getText()));

                    timeEditor.commitEdit();
                    setTimeSpinner(time);

                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        timeSpinner.setBorder(new LineBorder(new Color(0x1A5690), 3));

        panelFlow4.add(buttonSalva);

        panelBox3.add(labelReparto);
        panelBox3.add(boxReparto);

        panelBox4.add(labelData);
        panelBox4.add(spinner);

        panelBox5.add(labelOra);
        panelBox5.add(timeSpinner);

        panelFlow2.add(panelBox3);
        panelFlow2.add(panelBox4);
        panelFlow2.add(panelBox5);

        panelBox1.add(labelPaziente);
        panelBox1.add(sceltaPaziente);
        panelBox2.add(labelDottore);
        panelBox2.add(sceltaDottore);

        panelFlow5.add(panelBox1);
        panelFlow5.add(panelBox2);

        // Aggiunta dei pannelli al pannello principale
        mainPanel.add(panelFlowTitle, FlowLayout.LEFT);
        mainPanel.add(panelFlow1);
        mainPanel.add(panelFlow5);
        mainPanel.add(panelFlow2);
        mainPanel.add(panelFlow4);

        // Aggiunta del pannello principale al frame
        add(mainPanel);

    }

    public Dottore getDottoreSelezionato() {
        return (Dottore) sceltaDottore.getSelectedItem();
    }
    public void setBoxDottore(Dottore dottore) {
        int ris = 0;
        for(int i=0; i<listaDottori.size(); i++){
            if(dottore.getNome().equals(listaDottori.get(i).getNome()) && dottore.getCognome().equals(listaDottori.get(i).getCognome())){
                ris=i;
            }
        }
        this.sceltaDottore.setSelectedItem(listaDottori.get(ris));
    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public JButton getButtonSalva(){return buttonSalva;}

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JComboBox getBoxTipoPrenotazione() {
        return boxTipoPrenotazione;
    }

    public void setBoxTipoPrenotazione(TipoPrenotazione tp) {
        this.boxTipoPrenotazione.setSelectedItem(tp);
    }

    public JComboBox getBoxReparto() {
        return boxReparto;
    }

    public void setBoxReparto(Reparto boxReparto) {
        this.boxReparto.setSelectedItem(boxReparto);
    }

    public Paziente getPazienteSelezionato() {
        int index= sceltaPaziente.getSelectedIndex();
        return listaPazienti.get(index);
    }
    public JComboBox getBoxPaziente(){
        return sceltaPaziente;
    }
    public JComboBox getBoxDottore(){
        return sceltaDottore;
    }
    public void setBoxPaziente(Paziente paziente) {
        int ris = 0;
        for(int i=0; i<listaDottori.size(); i++){
            if(paziente.getNome().equals(listaDottori.get(i).getNome()) && paziente.getCognome().equals(listaDottori.get(i).getCognome())){
                ris=i;
            }
        }
        this.sceltaPaziente.setSelectedItem(listaPazienti.get(ris));
    }

    public JSpinner getSpinner() {
        return spinner;
    }

    public void setSpinner(String dataStringa) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Date data = null;
        try {
            data = sdf.parse(dataStringa);
            this.spinner.setValue(data);
        } catch (ParseException e) {
            System.out.println("Errore nella conversione della stringa data: " + e.getMessage());
        }

    }

    public JSpinner getTimeSpinner() {
        return timeSpinner;
    }

    public void setTimeSpinner(String oraStringa) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date ora = null;
        try {
            ora = sdf.parse(oraStringa);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.timeSpinner.setValue(ora);
    }

    public JSpinner.DateEditor getEditorSpinner(){
        return this.editor;
    }
    public List<Dottore> getDottori(){
        return listaDottori;
    }
}