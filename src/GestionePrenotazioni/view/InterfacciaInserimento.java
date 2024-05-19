package GestionePrenotazioni.view;

import GestioneDottori.model.DatabaseDottori;
import GestioneDottori.model.Dottore;
import GestioneDottori.view.ModelloTabellaDottori;
import GestionePazienti.model.Paziente;
import GestionePazienti.view.ModelloTabellaPazienti;
import GestionePrenotazioni.model.Reparto;
import GestionePrenotazioni.model.TipoPrenotazione;
import Style.*;
import GestionePazienti.model.DatabasePazienti;
import org.intellij.lang.annotations.Flow;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    private DatabasePazienti databasePazienti;
    private MyComboBoxDottori sceltaDottore;
    //
    private JSpinner.DateEditor editor;

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

        databasePazienti = new DatabasePazienti();
        databaseDottori = new DatabaseDottori();

        listaPazienti = new ArrayList<>(databasePazienti.getPazienti());
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

        timeModel = new SpinnerDateModel();
        timeModel.setCalendarField(Calendar.MINUTE);

        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);

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
        MyButtonStyle buttonRicercaPazienti = new MyButtonStyle(MyButtonStyle.RICERCA);
        JPanel panelSceltaPaziente = new JPanel(new BorderLayout());
        panelSceltaPaziente.add(sceltaPaziente, BorderLayout.CENTER);
        panelSceltaPaziente.add(buttonRicercaPazienti, BorderLayout.LINE_END);
        panelBox1.add(panelSceltaPaziente);

        buttonRicercaPazienti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setLayout(new BorderLayout());

                JPanel panelRicerca = new JPanel(new FlowLayout());
                JLabel labelRicerca = new JLabel("Ricerca: ");
                JTextField fieldRicerca = new JTextField(15);

                List<Paziente> lista = new ArrayList<>();
                ModelloTabellaPazienti modello = new ModelloTabellaPazienti(lista);
                for(int i=0; i<sceltaPaziente.getItemCount(); i++){
                    lista.add(sceltaPaziente.getItemAt(i));
                }
                JTable tabellaRicerca = new MyTableStyle(modello);
                MyButtonStyle bottonSeleziona = new MyButtonStyle("Seleziona", Color.gray, Color.white);
                fieldRicerca.addKeyListener(new KeyListener() { //implementazione del keyListener sulla textField ricerca
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        AbstractTableModel dtb = (AbstractTableModel) tabellaRicerca.getModel();
                        TableRowSorter<AbstractTableModel> trs = new TableRowSorter<>(dtb);
                        tabellaRicerca.setRowSorter(trs);
                        trs.setRowFilter(RowFilter.regexFilter(fieldRicerca.getText()));

                    }
                });
                bottonSeleziona.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int rigaSelezionata = tabellaRicerca.getSelectedRow();
                        if(rigaSelezionata!=-1) {
                            int id = (int) tabellaRicerca.getValueAt(rigaSelezionata, 7);
                            for (int i = 0; i < sceltaPaziente.getItemCount() - 1; i++) {
                                if (sceltaPaziente.getItemAt(i).getId() == id) {
                                    sceltaPaziente.setSelectedIndex(i);
                                }
                            }
                            dialog.dispose();
                            setVisible(true);
                        }else{
                            JOptionPane.showMessageDialog(dialog, "Seleziona una riga", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                panelRicerca.add(labelRicerca);
                panelRicerca.add(fieldRicerca);
                dialog.add(panelRicerca, BorderLayout.PAGE_START);
                dialog.add(new MyScrollPane(tabellaRicerca), BorderLayout.CENTER);
                dialog.add(bottonSeleziona, BorderLayout.PAGE_END);

                dialog.setTitle("Seleziona");
                dialog.setModal(true);
                dialog.setSize(700, 500);
                dialog.setLocationRelativeTo(null);
                dialog.setResizable(false);
                dialog.setVisible(true);
            }
        });



        panelBox2.add(labelDottore);
        MyButtonStyle buttonRicercaDottori = new MyButtonStyle(MyButtonStyle.RICERCA);
        JPanel panelSceltaDottore = new JPanel(new BorderLayout());
        panelSceltaDottore.add(sceltaDottore, BorderLayout.CENTER);
        panelSceltaDottore.add(buttonRicercaDottori, BorderLayout.LINE_END);
        panelBox2.add(panelSceltaDottore);

        buttonRicercaDottori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setLayout(new BorderLayout());

                JPanel panelRicerca = new JPanel(new FlowLayout());
                JLabel labelRicerca = new JLabel("Ricerca: ");
                JTextField fieldRicerca = new JTextField(15);


                List<Dottore> lista = new ArrayList<>();
                ModelloTabellaDottori modello = new ModelloTabellaDottori();
                for(int i=0; i<sceltaDottore.getItemCount(); i++){
                    lista.add(sceltaDottore.getItemAt(i));
                }
                modello.setListaDottori(lista);
                JTable tabellaRicerca = new MyTableStyle(modello);
                MyButtonStyle bottonSeleziona = new MyButtonStyle("Seleziona", Color.gray, Color.white);

                fieldRicerca.addKeyListener(new KeyListener() { //implementazione del keyListener sulla textField ricerca
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        AbstractTableModel dtb = (AbstractTableModel) tabellaRicerca.getModel();
                        TableRowSorter<AbstractTableModel> trs = new TableRowSorter<>(dtb);
                        tabellaRicerca.setRowSorter(trs);
                        trs.setRowFilter(RowFilter.regexFilter(fieldRicerca.getText()));

                    }
                });
                bottonSeleziona.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int rigaSelezionata = tabellaRicerca.getSelectedRow();
                        if(rigaSelezionata!=-1) {
                            int id = (int) tabellaRicerca.getValueAt(rigaSelezionata, 6);
                            for (int i = 0; i < sceltaDottore.getItemCount() - 1; i++) {
                                if (sceltaDottore.getItemAt(i).getId() == id) {
                                    sceltaDottore.setSelectedIndex(i);
                                }
                            }
                            dialog.dispose();
                            setVisible(true);
                        }else{
                            JOptionPane.showMessageDialog(dialog, "Seleziona una riga", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                panelRicerca.add(labelRicerca);
                panelRicerca.add(fieldRicerca);
                dialog.add(panelRicerca, BorderLayout.PAGE_START);
                dialog.add(new MyScrollPane(tabellaRicerca), BorderLayout.CENTER);
                dialog.add(bottonSeleziona, BorderLayout.PAGE_END);

                dialog.setTitle("Seleziona");
                dialog.setModal(true);
                dialog.setSize(700, 500);
                dialog.setLocationRelativeTo(null);
                dialog.setResizable(false);
                dialog.setVisible(true);
            }
        });

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
        int index = listaDottori.indexOf(dottore); // Trova l'indice del dottore nella lista
        if (index != -1) { // Verifica se il dottore è stato trovato nella lista
            this.sceltaDottore.setSelectedIndex(index); // Imposta l'elemento selezionato nella combobox
        } else {
            // Se il dottore non è stato trovato, gestisci questo caso di conseguenza (ad esempio, stampa un messaggio di avviso)
            System.out.println("Dottore non trovato nella lista.");
        }
    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public JButton getButtonSalva(){return buttonSalva;}

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
        for(int i=0; i<listaPazienti.size(); i++){
            if(paziente.getId() == listaPazienti.get(i).getId()){
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


}