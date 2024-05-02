/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionePrescrizioni;
import GestioneDottori.model.Dottore;
import GestionePrenotazioni.view.MyOptionPane;
import GestionePrescrizioni.model.GestionePrescrizioni;
import GestionePrescrizioni.model.Prescrizione;
import Style.*;
import GestionePazienti.model.Paziente;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.naming.BinaryRefAddr;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

public class SchermataPrescrizione extends JPanel implements ActionListener{
    
 final int larghezza = 800;
    final int altezza = 600;
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
    private final Object colonne[] = {"Paziente","Dottore","Oggetto"};
    private InterfacciaTab interfacciaTab;
    private InterfacciaInserimento interfacciaInserimento;
    private GestionePrescrizioni gestionePrescrizioni;
    private Dialog aggiungiPrescrizioneDialog;
    private DefaultTableModel tableModel;
    private JButton cancella;
    private JFileChooser fileChooser;
    private File file = new File(System.getProperty("user.home"), "databasePrescrizioni.dat");
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

        gestionePrescrizioni = new GestionePrescrizioni();

        cancella= new JButton();
        tableModel = new DefaultTableModel(colonne, 0){

            @Override
            public boolean isCellEditable(int row, int column) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public int getRowCount() {
                return gestionePrescrizioni.getPrescrizioni().size();
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Prescrizione prescrizione= gestionePrescrizioni.getPrescrizioni().get(rowIndex);
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

        gestionePrescrizioni.caricaFile(file);

       
        containerJp = new JPanel(new BorderLayout(20, 10));
        interfacciaTab = new InterfacciaTab("Gestione Prescrizioni");
        interfacciaTab.setColor(new Color(54, 159, 148));

        interfacciaInserimento = new InterfacciaInserimento();

        aggiungiPrescrizioneDialog = new JDialog();
        
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
        pulsanteCreaPrescrizione.setFont(new Font("Arial",1,30));
        pulsanteCreaPrescrizione.setPreferredSize(new Dimension(270,30));

        ovestJp.add(pulsanteCreaPrescrizione);
        ovestJp.add(vuota);

        pulsanteStampa = new MyButtonStyle("Stampa", new Color(54, 159, 148));
        pulsanteStampa .setFont(new Font("Arial",1,30));
        pulsanteStampa.addActionListener(this);

        ovestJp.add(pulsanteStampa);
        ovestJp.add(vuota1);

        pulsanteModifica = new MyButtonStyle ("Modifica", new Color(54, 159, 148));
        pulsanteModifica .setFont(new Font("Arial",1,30));
        pulsanteModifica.addActionListener(this);

        ovestJp.add(pulsanteModifica);

        pulsanteElimina = new MyButtonStyle("Elimina", new Color(54, 159, 148));
        pulsanteElimina .setFont(new Font("Arial",1,30));
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

        }else if(premuto.equals(pulsanteStampa)){   //pulsante stampa

            int rigaSelezionata = table.getSelectedRow();
            if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                // controlla se il nome del file termina con l'estensione .pdf
                if (!filePath.endsWith(".pdf")) {
                    fileToSave = new File(filePath + ".pdf");
                }

                try {
                    gestionePrescrizioni.salvaPrescrizioneSuFile(fileToSave, rigaSelezionata);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Impossibile stampare la prenotazione" , "Errore di battitura", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(premuto.equals(pulsanteCreaPrescrizione)){     //pulsante aggiungi
            if(!aggiungiPrescrizioneDialog.isVisible()) {
                if (interfacciaInserimento.getButtonSalva().getActionListeners().length == 0) {
                    interfacciaInserimento.getButtonSalva().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            processaEventoAggiungi();
                        }
                    });
                }
                aggiungiPrescrizioneDialog.add(interfacciaInserimento);
                aggiungiPrescrizioneDialog.setModal(true);
                aggiungiPrescrizioneDialog.setSize(700, 500);
                aggiungiPrescrizioneDialog.setLocationRelativeTo(null);
                aggiungiPrescrizioneDialog.setResizable(false);
                aggiungiPrescrizioneDialog.setVisible(true);
            }
        }else if(premuto.equals(pulsanteElimina)){  //pulsante elimina
            int rigaSelezionata= -1;
            rigaSelezionata = table.getSelectedRow();
            if(rigaSelezionata!=-1) {
                int sceltaUtente= MyOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare questa prenotazione?", "Conferma eliminazione", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
                if (sceltaUtente == JOptionPane.YES_OPTION) {
                    gestionePrescrizioni.rimuoviPrescrizione(rigaSelezionata);
                    tableModel.fireTableDataChanged();
                    try {
                        gestionePrescrizioni.salvaSuFile(file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }

    private void processaEventoAggiungi(){
        Paziente paziente = (Paziente) interfacciaInserimento.getSceltaPaziente().getSelectedItem();
        Dottore dottore = (Dottore) interfacciaInserimento.getSceltaDottore().getSelectedItem();
        String oggettoPrescrizione = interfacciaInserimento.getTextAreaOggetto().getText();

        Prescrizione prescrizione= new Prescrizione(paziente, dottore, oggettoPrescrizione);
        gestionePrescrizioni.InserimentoPrescrizione(prescrizione);

        aggiungiPrescrizioneDialog.dispose();
        try {
            gestionePrescrizioni.salvaSuFile(file);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        tableModel.fireTableDataChanged();
    }

    public InterfacciaInserimento getInterfacciaInserimento(){
        return this.interfacciaInserimento;
    }


}

