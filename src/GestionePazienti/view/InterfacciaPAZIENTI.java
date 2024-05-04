package GestionePazienti.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import GestionePrenotazioni.model.Prenotazione;
import GestionePrescrizioni.model.DatabasePrescrizioni;

import GestionePazienti.model.DatabasePazienti;
import GestionePazienti.model.Paziente;
import GestionePrenotazioni.model.DatabasePrenotazione;
import GestionePrescrizioni.model.Prescrizione;
import Style.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InterfacciaPAZIENTI extends JPanel{
    private DefaultTableModel tableModel;
    private JTable table;
    private InterfacciaTab interfacciaTab;
    private DatabasePazienti g = new DatabasePazienti();
    private File file = g.file;
    private Color verde;
    private JTextField fieldRicerca;
    private DatabasePrenotazione databasePrenotazione;
    private DatabasePrescrizioni databasePrescrizioni;
    private CartellaClinica cartellaClinica;
    private JSpinner dataDiNascitaSpinner;

    public InterfacciaPAZIENTI() {
        setSize(900, 700);
        setLayout(new BorderLayout());

        databasePrenotazione = new DatabasePrenotazione();
        databasePrescrizioni = new DatabasePrescrizioni();


        verde = new Color(48, 115, 81);
        String[] colonne = { "Nome", "Cognome", "Data di nascita", "Codice Fiscale", "Sesso", "Residenza", "Cap" };

        tableModel = new DefaultTableModel(colonne, 0){

            @Override
            public boolean isCellEditable(int row, int column) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public int getRowCount() {
                return g.getPazienti().size();
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Paziente paziente= g.getPazienti().get(rowIndex);
                switch(columnIndex){
                    case 0:
                        return paziente.getNome();
                    case 1:
                        return paziente.getCognome();
                    case 2:
                        return paziente.getDataNascita();
                    case 3:
                        return paziente.getCodiceFiscale();
                    case 4:
                        return paziente.getSesso();
                    case 5:
                        return paziente.getResidenza();
                    case 6:
                        return paziente.getCap();
                    default:
                        return null;
                }
            }
            
        };


        JPanel mainPanel = new JPanel(new BorderLayout());

        interfacciaTab = new InterfacciaTab("Gestione Pazienti");
        interfacciaTab.setColor(new Color(48, 115, 81));

        cartellaClinica = new CartellaClinica();

        // Creazione della tabella dei pazienti

        table = new MyTableStyle(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        table.setRowHeight(table.getRowHeight()+10);//per aumentare la dimensione della table
        table.getTableHeader().setReorderingAllowed(false); //per non far si che le colonne si spostino
        table.getTableHeader().setResizingAllowed(false); //per non far si che si modifichi il paziente direttamente dal table

        // Pulsanti per aggiunta e modifica dei pazienti
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton aggiungiButton = new MyButtonStyle("+ Crea Paziente", verde);
        aggiungiButton.setPreferredSize(new Dimension(110,40));
        JButton modificaButton = new MyButtonStyle("Modifica", verde);
        JButton eliminaButton = new MyButtonStyle("Elimina", verde);
        JButton apriCartellaButton = new MyButtonStyle("Apri Cartella Clinica", verde);
        apriCartellaButton.setPreferredSize(new Dimension(130,40));

        fieldRicerca = new JTextField(15);
        fieldRicerca.setPreferredSize(new Dimension(140,30));
        Border lineBorder = new LineBorder(Color.black, 2);
        Border emptyBorder = new EmptyBorder(0,0,0,30);
        Border compoundBorder = new CompoundBorder(lineBorder, emptyBorder);
        fieldRicerca.setBorder(compoundBorder);

        fieldRicerca.addKeyListener(new KeyListener() { //implementazione del keyListener sulla textField ricerca
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                AbstractTableModel dtb = (AbstractTableModel) tableModel;
                TableRowSorter<AbstractTableModel> trs = new TableRowSorter<>(dtb);
                table.setRowSorter(trs);
                trs.setRowFilter(RowFilter.regexFilter(fieldRicerca.getText()));

            }
        });


        aggiungiButton.addActionListener(e -> {
            // Aggiunta del paziente
            AggiungiPazienteDialog dialog = new AggiungiPazienteDialog();
            dialog.setVisible(true);
        });

        modificaButton.addActionListener(e -> {
            // Modifica del paziente
            int selectedRiga = table.getSelectedRow();
            if (selectedRiga != -1) {
                ModificaPazienteDialog dialog2 = new ModificaPazienteDialog(selectedRiga);
                dialog2.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, "Seleziona un paziente da modificare.",
                        "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        eliminaButton.addActionListener(e -> {
            int rigaSelezionata = table.getSelectedRow();
            if (rigaSelezionata != -1) {
                int scelta= JOptionPane.showConfirmDialog(this, "Sei sicuro di voler eliminare questo paziente?", "Conferma", JOptionPane.OK_CANCEL_OPTION);
                if(scelta == JOptionPane.OK_OPTION){
                    g.getPazienti().remove(rigaSelezionata);
                    tableModel.fireTableDataChanged();
                    JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, "Paziente eliminato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                }
                table.clearSelection();
            } else {
                JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, "Seleziona una riga da eliminare.", "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        apriCartellaButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Paziente paziente = g.getPazienti().get(selectedRow);

                JTabbedPane tabbedPane = new JTabbedPane();
                JPanel panel1 = new JPanel(new BorderLayout());
                JPanel panel2 = new JPanel(new BorderLayout());
                JPanel panel3 = new JPanel(new BorderLayout());

                // Simulazione dei documenti
                JLabel l1 = new JLabel("Dati di " + paziente.getNome() + " " + paziente.getCognome());
                l1.setOpaque(false);
                l1.setBackground(Color.white);

                JTextArea t1 = new JTextArea();
                //t1.setOpaque(false);
                t1.setEditable(false);
                t1.setFocusable(false);
                t1.setText(paziente.getCartella().getDati());
                MyScrollPane scroll = new MyScrollPane(t1);
                scroll.setBackground(Color.white);

                JLabel l2 = new JLabel("Prescrizioni di " + paziente.getNome() + " " + paziente.getCognome());

                ArrayList<Prescrizione> listaPrescrizioniPaziente = new ArrayList<>(cartellaClinica.caricaPrescrizioniPaziente(paziente));

                String[] colonnePrescrizioni;
                if(!listaPrescrizioniPaziente.isEmpty()){
                    colonnePrescrizioni = new String[]{"Dottore", "Oggetto Prescrizione"};
                }else{
                    colonnePrescrizioni = new String[]{"Nessuna prescrizione"};
                }
                DefaultTableModel tableModelPrescrizioniPaziente = new DefaultTableModel(colonnePrescrizioni, 0){

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        // TODO Auto-generated method stub
                        return false;
                    }

                    @Override
                    public int getRowCount() {
                        return listaPrescrizioniPaziente.size();
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        Prescrizione prescrizione= listaPrescrizioniPaziente.get(rowIndex);
                        switch(columnIndex){
                            case 0:
                                return prescrizione.getDottore().getNome() + " " + prescrizione.getDottore().getCognome();
                            case 1:
                                return prescrizione.getOggettoPrescrizione();
                            default:
                                return null;
                        }
                    }

                };

                JTable tablePrescrizioniPaziente = new JTable(tableModelPrescrizioniPaziente);

                tablePrescrizioniPaziente.getTableHeader().setReorderingAllowed(false);
                tablePrescrizioniPaziente.getTableHeader().setResizingAllowed(false);

                JLabel l3 = new JLabel("Prenotazioni di " + paziente.getNome() + " " + paziente.getCognome());

                ArrayList<Prenotazione> listaPrenotazioniPaziente = new ArrayList<>(cartellaClinica.caricaPrenotazioniPaziente(paziente));

                String[] colonnePrenotazioniPaziente;
                if(!listaPrenotazioniPaziente.isEmpty()){
                    colonnePrenotazioniPaziente = new String[]{"Dottore", "Data", "Ora", "Reparto", "Tipo"};
                }else{
                    colonnePrenotazioniPaziente = new String[]{"Nessuna prenotazione"};
                }

                DefaultTableModel tableModelPrenotazioniPaziente = new DefaultTableModel(colonnePrenotazioniPaziente, 0){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        // TODO Auto-generated method stub
                        return false;
                    }

                    @Override
                    public int getRowCount() {
                        return listaPrenotazioniPaziente.size();
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        Prenotazione prenotazione= listaPrenotazioniPaziente.get(rowIndex);
                        switch(columnIndex){
                            case 0:
                                return prenotazione.getDottore().getNome() + " " + prenotazione.getDottore().getCognome();
                            case 1:
                                return prenotazione.getData();
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

                JTable tablePrenotazioniPaziente = new JTable(tableModelPrenotazioniPaziente);

                tablePrenotazioniPaziente.getTableHeader().setReorderingAllowed(false);
                tablePrenotazioniPaziente.getTableHeader().setResizingAllowed(false);

                panel1.add(l1, BorderLayout.PAGE_START);    //dati
                panel1.add(scroll, BorderLayout.CENTER);    //dati

                panel2.add(l2, BorderLayout.PAGE_START); //Prescrizioni
                panel2.add(new MyScrollPane(tablePrescrizioniPaziente), BorderLayout.CENTER);

                panel3.add(l3, BorderLayout.PAGE_START); //prenotazioni
                panel3.add(new MyScrollPane(tablePrenotazioniPaziente));

                tabbedPane.addTab("Dati", panel1);
                tabbedPane.addTab("Prescrizioni", panel2);
                tabbedPane.addTab("Prenotazioni", panel3);

                tabbedPane.setPreferredSize(new Dimension(550, 300));

                JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, tabbedPane,
                        "Cartella Clinica di " + paziente.getNome() + " " + paziente.getCognome(), JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, "Seleziona un paziente.", "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel ricercaPanel = new JPanel(new FlowLayout());
        ricercaPanel.add(fieldRicerca);

        buttonPanel.add(apriCartellaButton);
        buttonPanel.add(eliminaButton);
        buttonPanel.add(modificaButton);
        buttonPanel.add(aggiungiButton);

        JPanel upperPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());

        upperPanel.add(buttonPanel, BorderLayout.LINE_END);
        upperPanel.add(ricercaPanel, BorderLayout.LINE_START);
        upperPanel.setBorder(new EmptyBorder(25,0,0,0));

        panel.add(interfacciaTab, BorderLayout.NORTH);
        panel.add(upperPanel);

        mainPanel.add(panel, BorderLayout.NORTH);


        add(mainPanel);
        setVisible(true);
    }

    private void loadFromFile(File percorsoFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(percorsoFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                if (data.length == 7) {
                    tableModel.addRow(data);
                } else {
                    JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, "Errore nel formato dei dati.", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aggiungiPaziente(Object[] DatoRiga) {
        tableModel.addRow(DatoRiga);
        aggiuntaAlFile(DatoRiga);
    }

    public void modificaPaziente(int riga, Object[] DatoRiga) {
        for (int i = 0; i < DatoRiga.length; i++) {
            tableModel.setValueAt(DatoRiga[i], riga, i);
        }
        modificaAlFile(riga, DatoRiga);
    }

    public void aggiuntaAlFile(Object[] DatoRiga) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (Object dato : DatoRiga) {
                writer.write(dato.toString());
                writer.append("\t"); // da un tab dopo l'attributo
            }
            writer.newLine(); // Vai a capo per il prossimo paziente
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modificaAlFile(int riga, Object[] DatoRiga) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder fileContent = new StringBuilder();
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == riga) {
                    StringBuilder sb = new StringBuilder();
                    for (Object dato : DatoRiga) {
                        sb.append(dato).append("\t");
                    }
                    sb.deleteCharAt(sb.length() - 1); // Rimuovi l'ultima virgola
                    line = sb.toString();
                }
                fileContent.append(line).append("\n");
                count++;
            }
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(fileContent.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class AggiungiPazienteDialog extends JDialog implements Serializable{
        private JTextField nomeField, cognomeField, residenzaField, capField, sessoField,
                codiceFiscaleField;

        public AggiungiPazienteDialog() {
            setSize(800, 400);
            setModal(true);
            setLocationRelativeTo(null);
            setTitle("Aggiungi paziente");

            JPanel panel=new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            Color green = new Color(48, 115, 81); // Verde

            // Dati del Paziente
            JPanel DatiPazientePanel = new JPanel(new GridLayout(3, 2, 0, 0));

            JPanel datipaz = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel l1 = new JLabel("    Dati Paziente: ");
            datipaz.add(l1);
            l1.setFont(new Font("Calibri", Font.BOLD, 16));
            l1.setForeground(Color.WHITE);
            datipaz.setPreferredSize(new Dimension(getWidth(), 45));
            datipaz.setBackground(green);
            panel.add(datipaz);

            DatiPazientePanel.add(new JLabel("Nome:"));
            nomeField = new JTextField(30);
            DatiPazientePanel.add(nomeField);

            DatiPazientePanel.add(new JLabel("Cognome:"));
            cognomeField = new JTextField(30);
            DatiPazientePanel.add(cognomeField);

            panel.add(DatiPazientePanel, BorderLayout.NORTH);

            // Informazioni Personali
            JPanel InformazioniPersonaliPanel = new JPanel(new GridLayout(3, 2, 0, 0));

            JPanel infPer = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel l3 = new JLabel("    Informazioni Personali: ");
            infPer.add(l3);
            l3.setFont(new Font("Calibri", Font.BOLD, 16));
            l3.setForeground(Color.WHITE);
            infPer.setPreferredSize(new Dimension(getWidth(), 45));
            infPer.setBackground(green);
            panel.add(infPer);

            InformazioniPersonaliPanel.add(new JLabel("Data di Nascita:"));

            Calendar calendar = Calendar.getInstance();
            Date now= calendar.getTime();
            calendar.add(Calendar.YEAR, -100);
            Date startDate= calendar.getTime();

            SpinnerDateModel spinnerDateModel = new SpinnerDateModel(now, startDate, now, Calendar.YEAR);

            dataDiNascitaSpinner  = new JSpinner(spinnerDateModel);
            String format = "dd MMM yy";

            JSpinner.DateEditor editor = new JSpinner.DateEditor(dataDiNascitaSpinner, format);
            dataDiNascitaSpinner.setEditor(editor);
            dataDiNascitaSpinner.setPreferredSize(new Dimension(140, 25));

            InformazioniPersonaliPanel.add(dataDiNascitaSpinner);

            InformazioniPersonaliPanel.add(new JLabel("Codice Fiscale:"));
            codiceFiscaleField = new JTextField(30);
            InformazioniPersonaliPanel.add(codiceFiscaleField);

            InformazioniPersonaliPanel.add(new JLabel("Sesso:"));
            sessoField = new JTextField(30);
            InformazioniPersonaliPanel.add(sessoField);

            panel.add(InformazioniPersonaliPanel, BorderLayout.CENTER);

            // Indirizzo di Residenza
            JPanel IndirizzoDiResidenzaPanel = new JPanel(new GridLayout(2, 2, 0, 0));

            JPanel indRes = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel l2 = new JLabel("    Indirizzo di Residenza: ");
            indRes.add(l2);
            l2.setFont(new Font("Calibri", Font.BOLD, 16));
            l2.setForeground(Color.WHITE);
            indRes.setPreferredSize(new Dimension(getWidth(), 45));
            indRes.setBackground(green);
            panel.add(indRes);

            IndirizzoDiResidenzaPanel.add(new JLabel("Residenza:"));
            residenzaField = new JTextField(30);
            IndirizzoDiResidenzaPanel.add(residenzaField);

            IndirizzoDiResidenzaPanel.add(new JLabel("CAP:"));
            capField = new JTextField(30);
            IndirizzoDiResidenzaPanel.add(capField);

            panel.add(IndirizzoDiResidenzaPanel, BorderLayout.SOUTH);

            JButton aggiungiButton = new MyButtonStyle("Salva", new Color(48, 115, 81));
            aggiungiButton.setPreferredSize(new Dimension(120,40));
            JPanel panelBottone = new JPanel(new FlowLayout());
            panelBottone.add(aggiungiButton);
            panel.add(panelBottone);

            aggiungiButton.addActionListener(e -> {

                if (nomeField.getText().isEmpty() || cognomeField.getText().isEmpty() ||
                        codiceFiscaleField.getText().isEmpty() || sessoField.getText().isEmpty()
                        || residenzaField.getText().isEmpty() ||
                        capField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, "Devi inserire tutti i campi.", "Errore", JOptionPane.ERROR_MESSAGE);

                } else {
                    Object[] rowData = { nomeField.getText(), cognomeField.getText(), getDataSpinner().getValue(),
                            codiceFiscaleField.getText(), sessoField.getText(), residenzaField.getText(),
                            capField.getText(),
                    };

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                    String data= sdf.format(getDataSpinner().getValue());

                    cartellaClinica.setDati("Data di Nascita: " + data + "\nCodice Fiscale: " + codiceFiscaleField.getText() +
                            "\nSesso: " + sessoField.getText() + "\nResidenza: " + residenzaField.getText() + "\nCAP: " + capField.getText());

                    Paziente paziente = new Paziente(nomeField.getText(), cognomeField.getText(), data,
                            codiceFiscaleField.getText(), sessoField.getText(), residenzaField.getText(),
                            capField.getText(),cartellaClinica);

                    g.AggiungiPaziente(paziente);
                    tableModel.fireTableDataChanged();

                    dispose();
                    JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, "Paziente aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);

                }

            });

            //panel.add(aggiungiButton, BorderLayout.PAGE_END);
            add(panel);
        }
    }

    class ModificaPazienteDialog extends JDialog {
        private int riga;

        public ModificaPazienteDialog( int riga) {
            this.riga = riga;
            setSize(700, 400);
            setModal(true);
            setLocationRelativeTo(null);
            setTitle("Modifica paziente");

            setLayout(new BorderLayout());

            JPanel panel= new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            Color darkerGreen = verde.darker(); // verde più scuro

            // Dati del Paziente
            JPanel DatiPazientePanel = new JPanel(new GridLayout(2, 2, 0, 0));

            JPanel datipaz = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel l1 = new JLabel("    Dati Paziente: ");
            datipaz.add(l1);
            l1.setFont(new Font("Calibri", Font.BOLD, 16));
            l1.setForeground(Color.WHITE);
            datipaz.setPreferredSize(new Dimension(getWidth(), 45));
            datipaz.setBackground(darkerGreen);
            panel.add(datipaz);

            DatiPazientePanel.add(new JLabel("Nome:"));
            JTextField nomeField = new JTextField(30); // Imposta la dimensione iniziale a 30 caratteri
            String text = (String) tableModel.getValueAt(riga, 0);
            nomeField.setText(text);
            DatiPazientePanel.add(nomeField);

            DatiPazientePanel.add(new JLabel("Cognome:"));
            JTextField cognomeField = new JTextField(30); // Imposta la dimensione iniziale a 30 caratteri
            String text1 = (String) tableModel.getValueAt(riga, 1);
            cognomeField.setText(text1);
            DatiPazientePanel.add(cognomeField);

            panel.add(DatiPazientePanel);

            // Informazioni Personali
            JPanel InformazioniPersonaliPanel = new JPanel(new GridLayout(3, 2, 0, 0));

            JPanel infPer = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel l3 = new JLabel("    Informazioni Personali: ");
            infPer.add(l3);
            l3.setFont(new Font("Calibri", Font.BOLD, 16));
            l3.setForeground(Color.WHITE);
            infPer.setPreferredSize(new Dimension(getWidth(), 45));
            infPer.setBackground(darkerGreen);
            panel.add(infPer);

            InformazioniPersonaliPanel.add(new JLabel("Data di Nascita:"));
            Calendar calendar = Calendar.getInstance();
            Date now= calendar.getTime();
            calendar.add(Calendar.YEAR, -100);
            Date startDate= calendar.getTime();

            SpinnerDateModel spinnerDateModel = new SpinnerDateModel(now, startDate, now, Calendar.YEAR);


            dataDiNascitaSpinner  = new JSpinner(spinnerDateModel);
            String format = "dd MMM yy";

            JSpinner.DateEditor editor = new JSpinner.DateEditor(dataDiNascitaSpinner, format);
            dataDiNascitaSpinner.setEditor(editor);
            dataDiNascitaSpinner.setPreferredSize(new Dimension(140, 25));
            InformazioniPersonaliPanel.add(dataDiNascitaSpinner);

            InformazioniPersonaliPanel.add(new JLabel("Codice Fiscale:"));
            JTextField codiceFiscaleField = new JTextField(30); // Imposta la dimensione iniziale a 30 caratteri
            String text6 = (String) tableModel.getValueAt(riga, 3);
            codiceFiscaleField.setText(text6);
            InformazioniPersonaliPanel.add(codiceFiscaleField);

            InformazioniPersonaliPanel.add(new JLabel("Sesso:"));
            JTextField sessoField = new JTextField(30); // Imposta la dimensione iniziale a 30 caratteri
            String text5 = (String) tableModel.getValueAt(riga, 4);
            sessoField.setText(text5);
            InformazioniPersonaliPanel.add(sessoField);

            panel.add(InformazioniPersonaliPanel, BorderLayout.CENTER);

            // Indirizzo di Residenza
            JPanel IndirizzoDiResidenzaPanel = new JPanel(new GridLayout(2, 2, 0, 0));

            JPanel indRes = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel l2 = new JLabel("    Indirizzo di Residenza: ");
            indRes.add(l2);
            l2.setFont(new Font("Calibri", Font.BOLD, 16));
            l2.setForeground(Color.WHITE);
            indRes.setPreferredSize(new Dimension(getWidth(), 45));
            indRes.setBackground(darkerGreen);
            panel.add(indRes);

            IndirizzoDiResidenzaPanel.add(new JLabel("Residenza:"));
            JTextField residenzaField = new JTextField(30); // Imposta la dimensione iniziale a 30 caratteri
            String text2 = (String) tableModel.getValueAt(riga, 5);
            residenzaField.setText(text2);
            IndirizzoDiResidenzaPanel.add(residenzaField);

            IndirizzoDiResidenzaPanel.add(new JLabel("CAP:"));
            JTextField capField = new JTextField(30); // Imposta la dimensione iniziale a 30 caratteri
            String text3 = (String) tableModel.getValueAt(riga, 6);
            capField.setText(text3);
            IndirizzoDiResidenzaPanel.add(capField);

            panel.add(IndirizzoDiResidenzaPanel, BorderLayout.NORTH);


            JButton modificaButton = new MyButtonStyle("Modifica", darkerGreen);
            modificaButton.setPreferredSize(new Dimension(120,40));
            JPanel panelBottone = new JPanel(new FlowLayout());
            panelBottone.add(modificaButton);
            panel.add(panelBottone);


            modificaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] rowData = { nomeField.getText(), cognomeField.getText(),getDataSpinner(),
                            codiceFiscaleField.getText(), sessoField.getText(), residenzaField.getText(),
                            capField.getText() };

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                    String data= sdf.format(getDataSpinner().getValue());

                    CartellaClinica cartellaClinica = new CartellaClinica();
                    cartellaClinica.setDati("Data di Nascita: " + data + "\nCodice Fiscale: " + codiceFiscaleField.getText() +
                            "\nSesso: " + sessoField.getText() + "\nResidenza: " + residenzaField.getText() + "\nCAP: " + capField.getText());


                    Paziente paziente = new Paziente(nomeField.getText(), cognomeField.getText(), data,
                            codiceFiscaleField.getText(), sessoField.getText(), residenzaField.getText(),
                            capField.getText(),cartellaClinica);

                    Paziente vecchioPaziente = g.getPazienti().get(riga);

                    databasePrenotazione.AggiornaPazientePrenotazione(vecchioPaziente, paziente);
                    databasePrescrizioni.AggiornaPazientePrenotazione(vecchioPaziente, paziente);

                    g.getPazienti().set(riga, paziente);

                    tableModel.fireTableDataChanged();
                    dispose();
                    JOptionPane.showMessageDialog(null, "Paziente modificato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            //panel.add(modificaButton, BorderLayout.PAGE_END);
            add(panel);
        }

    }

    public InterfacciaTab getTab(){
        return interfacciaTab;
    }
    public void setDatabasePrenotazioni(DatabasePrenotazione database){
        this.databasePrenotazione = database;
        cartellaClinica.setListaPrenotazioni(databasePrenotazione.getPrenotazioni());
    }
    public void setDatabasePrescrizioni(DatabasePrescrizioni prescrizioni){
        this.databasePrescrizioni = prescrizioni;
        cartellaClinica.setListaPrescrizioni(databasePrescrizioni.getPrescrizioni());
    }

    public JSpinner getDataSpinner() {
        return dataDiNascitaSpinner;
    }

    public void setSpinner(String dataStringa) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Date data = null;
        try {
            data = sdf.parse(dataStringa);
            this.dataDiNascitaSpinner.setValue(data);
        } catch (ParseException e) {
            System.out.println("Errore nella conversione della stringa data: " + e.getMessage());
        }

    }

    public DatabasePazienti getDatabasePazienti(){
        return g;
    }

}
