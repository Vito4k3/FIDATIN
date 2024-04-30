package GestionePazienti.view;

import javax.print.ServiceUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import GestioneDottori.model.Dottore;
import GestionePazienti.model.GestionePazienti;
import GestionePazienti.model.Paziente;
import Homepage.view.FrameHomepage;
import Style.InterfacciaTab;
import Style.MyButtonStyle;
import Style.MyTableStyle;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class InterfacciaPAZIENTI extends JPanel{
    private DefaultTableModel tableModel;
    private JTable table;
    private InterfacciaTab interfacciaTab;
    private GestionePazienti g = new GestionePazienti();
    private File file = g.file;
    private Color verde;

    public InterfacciaPAZIENTI() {
        setSize(900, 700);
        setLayout(new BorderLayout());

        this.caricaFile();

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
                        return paziente.getCodiceFiscale();
                    case 3:
                        return paziente.getResidenza();
                    case 4:
                        return paziente.getDataNascita();
                    case 5:
                        return paziente.getCodiceFiscale();
                    case 6:
                        return paziente.getResidenza();
                    default:
                        return null;
                }
            }
            
        };

        JPanel mainPanel = new JPanel(new BorderLayout());

        interfacciaTab = new InterfacciaTab("Gestione Pazienti");
        interfacciaTab.setColor(new Color(48, 115, 81));

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


        aggiungiButton.addActionListener(e -> {
            // Aggiunta del paziente
            AggiungiPazienteDialog dialog = new AggiungiPazienteDialog();
            dialog.setVisible(true);
        });

        modificaButton.addActionListener(e -> {
            // Modifica del paziente
            int selectedRiga = table.getSelectedRow();
            if (selectedRiga != -1) {
                ModificaPazienteDialog dialog = new ModificaPazienteDialog(selectedRiga);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, "Seleziona un paziente da modificare.",
                        "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        eliminaButton.addActionListener(e -> {
            int rigaSelezionata = table.getSelectedRow();
            if (rigaSelezionata != -1) {
                g.getPazienti().remove(rigaSelezionata);
                tableModel.fireTableDataChanged();
                try {
                    g.salvaSuFile(file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, "Seleziona una riga da eliminare.", "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        apriCartellaButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String nomePaziente = (String) table.getValueAt(selectedRow, 0);
                String cognomePaziente = (String) table.getValueAt(selectedRow, 1);

                JTabbedPane tabbedPane = new JTabbedPane();
                JPanel panel1 = new JPanel();
                JPanel panel2 = new JPanel();
                JPanel panel3 = new JPanel();

                // Simulazione dei documenti
                JLabel l1 = new JLabel("Dati di " + nomePaziente + " " + cognomePaziente);
                JLabel l2 = new JLabel("Prescrizioni di " + nomePaziente + " " + cognomePaziente);
                JLabel l3 = new JLabel("Prenotazioni di " + nomePaziente + " " + cognomePaziente);
                panel1.add(l1);
                panel2.add(l2);
                panel3.add(l3);

                tabbedPane.addTab("Dati", panel1);
                tabbedPane.addTab("Prescrizioni", panel2);
                tabbedPane.addTab("Prenotazioni", panel3);

                tabbedPane.setSize(600, 400);

                JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, tabbedPane,
                        "Cartella Clinica di " + nomePaziente + " " + cognomePaziente, JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, "Seleziona un paziente.", "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(apriCartellaButton);
        buttonPanel.add(eliminaButton);
        buttonPanel.add(modificaButton);
        buttonPanel.add(aggiungiButton);

        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.add(interfacciaTab, BorderLayout.NORTH);
        upperPanel.add(buttonPanel, BorderLayout.LINE_END);

        mainPanel.add(upperPanel, BorderLayout.NORTH);


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

    public void eliminaDalFile(int riga) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
                BufferedWriter writer = new BufferedWriter(new FileWriter(file + ".tmp"))) {

            StringBuilder sb = new StringBuilder();

            for (int col = 0; col < tableModel.getColumnCount(); col++) {
                Object value = tableModel.getValueAt(riga, col);
                if (value != null) {
                    sb.append(value).append("\t");
                } else {
                    sb.append("\t");
                }
            }

            String lineToRemove = sb.toString().trim();

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove))
                    continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            // Chiusura dei buffer
            writer.close();
            reader.close();

            // Elimina il file originale e rinomina quello temporaneo
            File tempFile = new File(file + ".tmp");
            if (file.delete()) {
                if (!tempFile.renameTo(file)) {
                    throw new IOException("Impossibile rinominare il file temporaneo");
                }
            } else {
                throw new IOException("Impossibile eliminare il file originale");
            }

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
        private JTextField nomeField, cognomeField, residenzaField, capField, dataDiNascitaField, sessoField,
                codiceFiscaleField;

        public AggiungiPazienteDialog() {
            setSize(800, 400);
            setModal(true);
            setLocationRelativeTo(null);

            JPanel panel=new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            Color green = new Color(48, 115, 81); // Verde

            // Dati del Paziente
            JPanel DatiPazientePanel = new JPanel(new GridLayout(3, 2, 0, 0));

            JPanel datipaz = new JPanel(new BorderLayout());
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

            JPanel infPer = new JPanel(new BorderLayout());
            JLabel l3 = new JLabel("    Informazioni Personali: ");
            infPer.add(l3);
            l3.setFont(new Font("Calibri", Font.BOLD, 16));
            l3.setForeground(Color.WHITE);
            infPer.setPreferredSize(new Dimension(getWidth(), 45));
            infPer.setBackground(green);
            panel.add(infPer);

            InformazioniPersonaliPanel.add(new JLabel("Data di Nascita:"));
            dataDiNascitaField = new JTextField(30);
            InformazioniPersonaliPanel.add(dataDiNascitaField);

            InformazioniPersonaliPanel.add(new JLabel("Codice Fiscale:"));
            codiceFiscaleField = new JTextField(30);
            InformazioniPersonaliPanel.add(codiceFiscaleField);

            InformazioniPersonaliPanel.add(new JLabel("Sesso:"));
            sessoField = new JTextField(30);
            InformazioniPersonaliPanel.add(sessoField);

            panel.add(InformazioniPersonaliPanel, BorderLayout.CENTER);

            // Indirizzo di Residenza
            JPanel IndirizzoDiResidenzaPanel = new JPanel(new GridLayout(2, 2, 0, 0));

            JPanel indRes = new JPanel(new BorderLayout());
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

                if (nomeField.getText().isEmpty() || cognomeField.getText().isEmpty()
                        || dataDiNascitaField.getText().isEmpty() ||
                        codiceFiscaleField.getText().isEmpty() || sessoField.getText().isEmpty()
                        || residenzaField.getText().isEmpty() ||
                        capField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(InterfacciaPAZIENTI.this, "Devi inserire tutti i campi.", "Errore", JOptionPane.ERROR_MESSAGE);

                } else {
                    Object[] rowData = { nomeField.getText(), cognomeField.getText(), dataDiNascitaField.getText(),
                            codiceFiscaleField.getText(), sessoField.getText(), residenzaField.getText(),
                            capField.getText(),
                    };


                    Paziente paziente = new Paziente(nomeField.getText(), cognomeField.getText(), dataDiNascitaField.getText(),
                            codiceFiscaleField.getText(), sessoField.getText(), residenzaField.getText(),
                            capField.getText(),null);
                    g.AggiungiPaziente(paziente);
                    tableModel.fireTableDataChanged();

                    //((InterfacciaPAZIENTI) parent).aggiungiPaziente(rowData);
                    dispose();

                    try {
                        g.salvaSuFile(file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

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
            setSize(600, 400);
            setModal(true);
            setLocationRelativeTo(null);

            setLayout(new BorderLayout());

            // (String) ((InterfacciaPAZIENTI) parent).tableModel.getValueAt(riga, 0)
            JPanel panel = new JPanel(new FlowLayout());

            Color darkerGreen = verde.darker(); // Rosso più scuro

            // Dati del Paziente
            JPanel DatiPazientePanel = new JPanel(new GridLayout(2, 2, 0, 0));

            JPanel datipaz = new JPanel(new BorderLayout());
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

            panel.add(DatiPazientePanel, BorderLayout.NORTH);

            // Informazioni Personali
            JPanel InformazioniPersonaliPanel = new JPanel(new GridLayout(3, 2, 0, 0));

            JPanel infPer = new JPanel(new BorderLayout());
            JLabel l3 = new JLabel("    Informazioni Personali: ");
            infPer.add(l3);
            l3.setFont(new Font("Calibri", Font.BOLD, 16));
            l3.setForeground(Color.WHITE);
            infPer.setPreferredSize(new Dimension(getWidth(), 45));
            infPer.setBackground(darkerGreen);
            panel.add(infPer);

            InformazioniPersonaliPanel.add(new JLabel("Data di Nascita:"));
            JTextField dataDiNascitaField = new JTextField(30); // Imposta la dimensione iniziale a 30 caratteri
            String text4 = (String) tableModel.getValueAt(riga, 2);
            dataDiNascitaField.setText(text4);
            InformazioniPersonaliPanel.add(dataDiNascitaField);

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

            JPanel indRes = new JPanel(new BorderLayout());
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

            modificaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] rowData = { nomeField.getText(), cognomeField.getText(), dataDiNascitaField.getText(),
                            codiceFiscaleField.getText(), sessoField.getText(), residenzaField.getText(),
                            capField.getText() };

                    Paziente paziente = new Paziente(nomeField.getText(), cognomeField.getText(), dataDiNascitaField.getText(),
                            codiceFiscaleField.getText(), sessoField.getText(), residenzaField.getText(),
                            capField.getText(),null);
                    g.getPazienti().set(riga, paziente);
                    tableModel.fireTableDataChanged();
                    //((InterfacciaPAZIENTI) parent).modificaPaziente(riga, rowData);
                    dispose();
                    try {
                        g.salvaSuFile(file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            panel.add(modificaButton, BorderLayout.PAGE_END);
            add(panel);
        }

    }
    public void caricaFile(){
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File creato!");
            }else{
                g.caricaDaFile(file);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InterfacciaTab getTab(){
        return interfacciaTab;
    }

}
