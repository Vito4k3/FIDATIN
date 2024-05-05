/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionePrescrizioni.view;
import GestioneDottori.model.Dottore;
import GestionePrenotazioni.view.MyOptionPane;
import GestionePrescrizioni.model.DatabasePrescrizioni;
import GestionePrescrizioni.model.Prescrizione;
import Style.*;
import GestionePazienti.model.Paziente;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SchermataPrescrizione extends JPanel implements ActionListener{
    private JButton pulsanteCreaPrescrizione, pulsanteStampa, pulsanteModifica, pulsanteElimina;
    private JPanel containerJp;
    private JPanel toolbarJp;
    private JPanel ovestJp;
    private JPanel estJp;
    private JPanel centerJp;
    private JTable table;
    private JLabel vuota = new JLabel(" ");
    private JLabel vuota1 = new JLabel(" ");
    private JLabel vuota2 = new JLabel(" ");
    private JLabel vuota3  = new JLabel(" ");
    private final Object[] colonne = {"Paziente","Dottore","Oggetto"};
    private InterfacciaTab interfacciaTab;
    private InterfacciaInserimento interfacciaInserimento, interfacciaInserimentoModifica;
    private DatabasePrescrizioni databasePrescrizioni;
    private Dialog prescrizioneDialog ,prescrizioneDialog2;
    private DefaultTableModel tableModel;
    private JButton cancella;
    private JFileChooser fileChooser;
    private JTextField fieldRicerca;
    public SchermataPrescrizione(){
        init();
    }
    
    private void init(){

        setLayout(new BorderLayout());
        setVisible(true);

        fileChooser = new JFileChooser();

        fileChooser.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    String filename = file.getName().toLowerCase();
                    return filename.endsWith(".pdf");
                }
            }

            public String getDescription() {
                return "File PDF (*.pdf)";
            }

        });
        fileChooser.setAcceptAllFileFilterUsed(false);

        databasePrescrizioni = new DatabasePrescrizioni();

        cancella= new JButton();
        tableModel = new DefaultTableModel(colonne, 0){

            @Override
            public boolean isCellEditable(int row, int column) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public int getRowCount() {
                return databasePrescrizioni.getPrescrizioni().size();
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Prescrizione prescrizione= databasePrescrizioni.getPrescrizioni().get(rowIndex);
                switch(columnIndex){
                    case 0:
                        return prescrizione.getPaziente().getNome() + " " + prescrizione.getPaziente().getCognome();
                    case 1:
                        return prescrizione.getDottore().getNome() + " " + prescrizione.getDottore().getCognome();
                    case 2:
                        return prescrizione.getOggettoPrescrizione();
                    default:
                        return null;
                }
            }

        };

       
        containerJp = new JPanel(new BorderLayout(20, 10));
        interfacciaTab = new InterfacciaTab("Gestione Prescrizioni");
        interfacciaTab.setColor(new Color(54, 159, 148));

        interfacciaInserimento = new InterfacciaInserimento();
        interfacciaInserimentoModifica = new InterfacciaInserimento();

        fieldRicerca = new JTextField(15);
        fieldRicerca.setPreferredSize(new Dimension(110,20));
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
                AbstractTableModel dtb =  tableModel;
                TableRowSorter<AbstractTableModel> trs = new TableRowSorter<>(dtb);
                table.setRowSorter(trs);
                trs.setRowFilter(RowFilter.regexFilter(fieldRicerca.getText()));

            }
        });

        prescrizioneDialog = new JDialog();
        prescrizioneDialog2 = new JDialog();

        // NORTH
        toolbarJp = new JPanel(new BorderLayout());
        toolbarJp.setBorder(new EmptyBorder(0,0,40,0));
        toolbarJp.add(interfacciaTab);
        
      
        containerJp.add(toolbarJp, BorderLayout.NORTH);


        
        // OVEST:
        ovestJp = new JPanel();
        ovestJp.setBorder(new EmptyBorder(0,10,0,0));
        ovestJp.setLayout(new GridLayout(11,1));

        pulsanteCreaPrescrizione = new MyButtonStyle ("Crea Prescrizione", new Color(54, 159, 148));
        pulsanteCreaPrescrizione.addActionListener(this);
        pulsanteCreaPrescrizione.setFont(new Font("Arial", Font.BOLD,30));
        pulsanteCreaPrescrizione.setPreferredSize(new Dimension(270,30));

        ovestJp.add(fieldRicerca);
        ovestJp.add(vuota3);

        ovestJp.add(pulsanteCreaPrescrizione);
        ovestJp.add(vuota);

        pulsanteStampa = new MyButtonStyle("Stampa", new Color(54, 159, 148));
        pulsanteStampa .setFont(new Font("Arial", Font.BOLD,30));
        pulsanteStampa.addActionListener(this);

        ovestJp.add(pulsanteStampa);
        ovestJp.add(vuota1);

        pulsanteModifica = new MyButtonStyle ("Modifica", new Color(54, 159, 148));
        pulsanteModifica .setFont(new Font("Arial", Font.BOLD,30));
        pulsanteModifica.addActionListener(this);

        ovestJp.add(pulsanteModifica);

        pulsanteElimina = new MyButtonStyle("Elimina", new Color(54, 159, 148));
        pulsanteElimina .setFont(new Font("Arial", Font.BOLD,30));
        pulsanteElimina.addActionListener(this);

        ovestJp.add(vuota2);
        ovestJp.add(pulsanteElimina);

      
        containerJp.add(ovestJp, BorderLayout.WEST);
        
        // EST
        estJp = new JPanel();
        // add to container panel
        containerJp.add(estJp, BorderLayout.EAST);
        
        //CENTER
        centerJp = new MyPanel(new GridLayout(1, 1, 10, 5));
        centerJp.setBorder(new EmptyBorder(0,0,30,0));

         
        table = new MyTableStyle(tableModel);
        JScrollPane scrollPane = new MyScrollPane(table);
        centerJp.add(scrollPane);
        containerJp.add(centerJp);
        
        add(containerJp);

        setVisible(true);
    }


    public static void main(String[] args) {
        new SchermataPrescrizione();
    }

    public InterfacciaTab getTab(){
        return interfacciaTab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton premuto = (JButton) e.getSource();
        if(premuto.equals(pulsanteModifica)){   //pulsante modifica
            int rigaSelezionata = -1;
            rigaSelezionata = table.getSelectedRow();
            if(rigaSelezionata!=-1) {
                Prescrizione prescrizione = databasePrescrizioni.getPrescrizioni().get(rigaSelezionata);
                if (!prescrizioneDialog.isVisible()) {
                    if (interfacciaInserimentoModifica.getButtonSalva().getActionListeners().length == 0) {
                        interfacciaInserimentoModifica.getButtonSalva().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                prescrizioneDialog.setVisible(false);
                                processaEventoModifica();
                            }
                        });
                    }

                    interfacciaInserimentoModifica.getSceltaDottore().setSelectedItem(prescrizione.getDottore());
                    interfacciaInserimentoModifica.getSceltaPaziente().setSelectedItem(prescrizione.getPaziente());
                    interfacciaInserimentoModifica.getTextAreaOggetto().setText(prescrizione.getOggettoPrescrizione());

                    prescrizioneDialog.add(interfacciaInserimentoModifica);
                    prescrizioneDialog.setModal(true);
                    prescrizioneDialog.setSize(700, 500);
                    prescrizioneDialog.setLocationRelativeTo(null);
                    prescrizioneDialog.setResizable(false);
                    prescrizioneDialog.setVisible(true);
                }
            }else{
                JOptionPane.showMessageDialog(prescrizioneDialog, "Seleziona una prescrizione", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }else if(premuto.equals(pulsanteStampa)){   //pulsante stampa
            int rigaSelezionata = -1;
            rigaSelezionata = table.getSelectedRow();
            if(rigaSelezionata != -1) {
                if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String filePath = fileToSave.getAbsolutePath();

                    // controlla se il nome del file termina con l'estensione .pdf
                    if (!filePath.endsWith(".pdf")) {
                        fileToSave = new File(filePath + ".pdf");
                    }

                    try {
                        databasePrescrizioni.salvaPrescrizioneSuFile(rigaSelezionata, fileToSave);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Impossibile stampare la prenotazione", "Errore di battitura", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(prescrizioneDialog, "Seleziona una prescrizione", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }else if(premuto.equals(pulsanteCreaPrescrizione)){     //pulsante aggiungi
            if(!prescrizioneDialog2.isVisible()) {
                if (interfacciaInserimento.getButtonSalva().getActionListeners().length == 0) {
                    interfacciaInserimento.getButtonSalva().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(interfacciaInserimento.getSceltaDottore().getSelectedItem() != null && interfacciaInserimento.getSceltaPaziente().getSelectedItem() != null
                                    && !interfacciaInserimento.getTextAreaOggetto().getText().isEmpty()){
                                prescrizioneDialog2.setVisible(false);
                                processaEventoAggiungi();
                            }else{
                                JOptionPane.showMessageDialog(null, "Inserisci tutti i campi!", "Errore", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    });
                }
                prescrizioneDialog2.add(interfacciaInserimento);
                prescrizioneDialog2.setModal(true);
                prescrizioneDialog2.setSize(700, 500);
                prescrizioneDialog2.setLocationRelativeTo(null);
                prescrizioneDialog2.setResizable(false);
                prescrizioneDialog2.setVisible(true);
            }
        }else if(premuto.equals(pulsanteElimina)){  //pulsante elimina
            int rigaSelezionata= -1;
            rigaSelezionata = table.getSelectedRow();
            if(rigaSelezionata!=-1) {
                int sceltaUtente= MyOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare questa prenotazione?", "Conferma eliminazione", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
                if (sceltaUtente == JOptionPane.YES_OPTION) {
                    databasePrescrizioni.rimuoviPrescrizione(rigaSelezionata);
                    tableModel.fireTableDataChanged();
                    JOptionPane.showMessageDialog(null, "Prescrizione eliminata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);

                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleziona una prescrizione", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void processaEventoAggiungi(){
        table.clearSelection();

        Paziente paziente = (Paziente) interfacciaInserimento.getSceltaPaziente().getSelectedItem();
        Dottore dottore = (Dottore) interfacciaInserimento.getSceltaDottore().getSelectedItem();
        String oggettoPrescrizione = interfacciaInserimento.getTextAreaOggetto().getText();

        Prescrizione prescrizione= new Prescrizione(paziente, dottore, oggettoPrescrizione);
        databasePrescrizioni.InserimentoPrescrizione(prescrizione);

        prescrizioneDialog.dispose();
        tableModel.fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Prescrizione aggiunta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        interfacciaInserimento.getTextAreaOggetto().setText("");

    }

    private void processaEventoModifica(){
        int rigaSelezionata= table.getSelectedRow();
        table.clearSelection();

        Prescrizione prescrizione = databasePrescrizioni.getPrescrizioni().get(rigaSelezionata);

        Dottore dottore = (Dottore) interfacciaInserimentoModifica.getSceltaDottore().getSelectedItem();
        Paziente paziente = (Paziente) interfacciaInserimentoModifica.getSceltaPaziente().getSelectedItem();
        String text = interfacciaInserimentoModifica.getTextAreaOggetto().getText();

        prescrizione.setDottore(dottore);
        prescrizione.setPaziente(paziente);
        prescrizione.setOggettoPrescrizione(text);

        tableModel.fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Prescrizione modificata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);

    }

    public InterfacciaInserimento getInterfacciaInserimento(){
        return this.interfacciaInserimento;
    }
    public DatabasePrescrizioni getGestionePrescrizione (){
        return this.databasePrescrizioni;
    }

    public InterfacciaInserimento getInserimentoModifica(){
        return this.interfacciaInserimentoModifica;
    }

}

