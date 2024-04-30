package GestionePrenotazioni.view;

import GestioneDottori.model.Dottore;
import GestioneDottori.view.FrameDottori;
import GestionePazienti.model.Paziente;
import GestionePrenotazioni.controller.Controller;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import GestionePrenotazioni.model.Prenotazione;
import GestionePrenotazioni.model.Reparto;
import GestionePrenotazioni.model.TipoPrenotazione;
import Eventi.Evento;
import Eventi.EventoEvent;
import Homepage.view.FrameHomepage;
import Style.InterfacciaTab;

public class FramePrenotazioni extends JPanel{
    private InterfacciaInserimento interfacciaInserimento, interfacciaInserimentoAggiungi;
    private InterfacciaPannelloPulsanti interfacciaPannelloPulsanti;
    private InterfacciaTabella interfacciaTabella;
    private Controller controller;
    private File file;
    private JPanel boxPanel, homePanel;
    private JDialog dialog, dialog2;
    private int ordinamento= 0;

    public FramePrenotazioni(){
        setLayout(new BorderLayout());

        String userHome = System.getProperty("user.home");
        file= new File(userHome, "databasePrenotazioni.dat");

        interfacciaInserimento = new InterfacciaInserimento();

        interfacciaInserimentoAggiungi = new InterfacciaInserimento();
        controller= new Controller();
        interfacciaPannelloPulsanti = new InterfacciaPannelloPulsanti();
        interfacciaTabella = new InterfacciaTabella();

        homePanel= new JPanel(new BorderLayout());

        dialog = new JDialog();
        dialog2= new JDialog();


        interfacciaPannelloPulsanti.setEvento(new Evento() {
            @Override
            public void evento(EventoEvent e) {

                JButton buttonAggiungi = e.getButtonAggiungi();
                JButton buttonModifica = e.getButtonModifica();
                JButton buttonElimina = e.getButtonElimina();
                JButton buttonOrdina = e.getButtonOrdina();
                JButton premuto = e.getButtonPremuto();

                if(premuto.equals(buttonAggiungi)){     //Pulsante Aggiungi

                    if(!dialog.isVisible()) {
                        if (interfacciaInserimentoAggiungi.getButtonSalva().getActionListeners().length == 0) {
                            interfacciaInserimentoAggiungi.getButtonSalva().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    processaEventoAggiungi();
                                }
                            });
                        }
                        interfacciaInserimentoAggiungi.setTitle("Inserisci Prenotazione");
                        dialog.add(interfacciaInserimentoAggiungi);
                        dialog.setModal(true);
                        dialog.setSize(700, 500);
                        dialog.setLocationRelativeTo(null);
                        dialog.setResizable(false);
                        dialog.setVisible(true);
                    }
                }else if(premuto.equals(buttonElimina)){        //pulsante Elimina
                    int rigaSelezionata= -1;
                    rigaSelezionata = interfacciaTabella.getTable().getSelectedRow();
                    if(rigaSelezionata!=-1) {
                        int sceltaUtente= MyOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare questa prenotazione?", "Conferma eliminazione", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
                        if (sceltaUtente == JOptionPane.YES_OPTION) {
                            controller.getDatabase().rimuoviPrenotazione(rigaSelezionata);
                            interfacciaTabella.aggiorna();
                            try {
                                controller.salvaSuFile(file);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }else if(premuto.equals(buttonModifica)){       //Pulsante Modifica
                    JTable table = interfacciaTabella.getTable();
                    int rigaSelezionata= -1;
                    rigaSelezionata = table.getSelectedRow();
                    if(rigaSelezionata!=-1) {
                        if (interfacciaInserimento.getButtonSalva().getActionListeners().length == 0) {
                            interfacciaInserimento.getButtonSalva().addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    dialog2.setVisible(false);
                                    processaEventoModifica();
                                }
                            });
                        }
                        if (!dialog2.isVisible()) {
                            Prenotazione prenotazione = controller.getDatabase().getPrenotazioni().get(rigaSelezionata);

                            Paziente paziente = prenotazione.getPaziente();
                            Dottore dottore = prenotazione.getDottore();
                            String ora = prenotazione.getOra();
                            String data = prenotazione.getData();
                            Reparto reparto = prenotazione.getReparto();
                            TipoPrenotazione tipoPrenotazione = prenotazione.getTipoPrenotazione();

                            interfacciaInserimento.setBoxPaziente(paziente);
                            interfacciaInserimento.setBoxDottore(dottore);
                            interfacciaInserimento.setBoxReparto(reparto);
                            interfacciaInserimento.setBoxTipoPrenotazione(tipoPrenotazione);
                            interfacciaInserimento.setSpinner(data);
                            interfacciaInserimento.setTimeSpinner(ora);
                            interfacciaInserimento.setTitle("Modifica Prenotazione");

                            dialog2.add(interfacciaInserimento);
                            dialog2.setModal(true);
                            dialog2.setSize(700, 500);
                            dialog2.setLocationRelativeTo(null);
                            dialog2.setResizable(false);
                            dialog2.setVisible(true);
                        }

                    }
                }else if(premuto.equals(buttonOrdina)){
                    ordinamento++;
                    if(ordinamento%2==0){
                        controller.getDatabase().ordinaPerDataDecrescente();
                    }else{
                        controller.getDatabase().ordinaPerDataCrescente();
                    }
                    interfacciaTabella.aggiorna();
                }
            }
        });

        interfacciaPannelloPulsanti.getFieldRicerca().addKeyListener(new KeyListener() { //implementazione del keyListener sulla textField ricerca
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                AbstractTableModel dtb = (AbstractTableModel) interfacciaTabella.getTable().getModel();
                TableRowSorter<AbstractTableModel> trs = new TableRowSorter<>(dtb);
                interfacciaTabella.getTable().setRowSorter(trs);
                trs.setRowFilter(RowFilter.regexFilter(interfacciaPannelloPulsanti.getFieldRicerca().getText()));

            }
        });



        this.caricaFile();   //crea e carica il file

        //Viene passato la lista di prenotazioni nella classe interfaccia Tabella
        interfacciaTabella.setDati(controller.getPrenotazioni());

        homePanel.add(interfacciaPannelloPulsanti, BorderLayout.PAGE_START);
        homePanel.add(interfacciaTabella, BorderLayout.CENTER);


        add(homePanel);

        setSize(1200,750);
        setMinimumSize(new Dimension(1000,600));
        setVisible(true);

    }

    public void caricaFile(){
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File creato!");
            }else{
                controller.caricaDaFile(file);
                controller.setContatore(controller.getPrenotazioni().size());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processaEventoAggiungi() {
        if(interfacciaInserimento.getBoxPaziente()!=null && interfacciaInserimento.getBoxDottore()!=null) {
            Date dataPrenotazione = (Date) interfacciaInserimentoAggiungi.getSpinner().getValue();
            Date time = (Date) interfacciaInserimentoAggiungi.getTimeSpinner().getValue();
            LocalTime ora = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault()).toLocalTime();
            Paziente paziente = interfacciaInserimentoAggiungi.getPazienteSelezionato();
            Dottore dottore = interfacciaInserimentoAggiungi.getDottoreSelezionato();
            int reparto = interfacciaInserimentoAggiungi.getBoxReparto().getSelectedIndex();
            int tipoPrenotazione = interfacciaInserimentoAggiungi.getBoxTipoPrenotazione().getSelectedIndex();

            controller.addPrenotazione(paziente, dottore, dataPrenotazione, ora, reparto, tipoPrenotazione);
            interfacciaTabella.aggiorna();
            dialog.setVisible(false);

            try {
                controller.salvaSuFile(file);   //salva il file ad ogni aggiunta
            } catch (IOException ef) {
                throw new RuntimeException(ef);
            }
        }else{
            String[] options = {"OK"};
            JOptionPane.showOptionDialog(null, "Perfavore, inserisci tutti i campi!", "Errore", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        }
    }

    private void processaEventoModifica() {
        JTable table = interfacciaTabella.getTable();
        int rigaSelezionata= table.getSelectedRow();
        table.clearSelection();

        Paziente paziente = interfacciaInserimento.getPazienteSelezionato();

        Date time = (Date) interfacciaInserimento.getTimeSpinner().getValue();
        LocalTime localOra = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault()).toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String ora = localOra.format(formatter);

        Date dataDate = (Date) interfacciaInserimento.getSpinner().getValue();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        String data = df.format(dataDate);

        Reparto reparto = (Reparto) interfacciaInserimento.getBoxReparto().getSelectedItem();
        TipoPrenotazione tipoPrenotazione = (TipoPrenotazione) interfacciaInserimento.getBoxTipoPrenotazione().getSelectedItem();

        Dottore dottore = interfacciaInserimento.getDottoreSelezionato();

        Prenotazione nuovaPrenotazione = new Prenotazione(paziente, dottore, data, ora, reparto, tipoPrenotazione);

        controller.getDatabase().sostituisciPrenotazione(rigaSelezionata, nuovaPrenotazione);
        interfacciaTabella.aggiorna();
        try {
            controller.salvaSuFile(file);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public InterfacciaTab  getTab(){
        return interfacciaPannelloPulsanti.getInterfacciaTab();
    }

}
