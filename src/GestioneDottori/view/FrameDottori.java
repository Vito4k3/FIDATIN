package GestioneDottori.view;

import Eventi.Evento;
import Eventi.EventoEvent;
import GestioneDottori.controller.Controller;
import GestioneDottori.model.Dottore;
import GestioneDottori.model.Status;
import GestioneDottori.model.TipoOperatori;
import GestionePrenotazioni.model.DatabasePrenotazione;
import GestionePrescrizioni.model.DatabasePrescrizioni;
import Style.InterfacciaTab;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FrameDottori extends JPanel{
    private InterfacciaInserimento interfacciaInserimento, interfacciaInserimentoAggiungi;
    private InterfacciaPannelloPulsanti interfacciaPannelloPulsanti;
    private InterfacciaTabella interfacciaTabella;
    private Controller controller;
    private JPanel mainPanel, homePanel;
    private JDialog dialog, dialog2;
    private DatabasePrenotazione databasePrenotazioni;
    private DatabasePrescrizioni databasePrescrizioni;

    public FrameDottori(){
        setLayout(new BorderLayout());

        interfacciaInserimento = new InterfacciaInserimento();
        interfacciaInserimentoAggiungi = new InterfacciaInserimento();
        controller= new Controller();
        interfacciaPannelloPulsanti = new InterfacciaPannelloPulsanti();
        interfacciaTabella = new InterfacciaTabella();

        homePanel= new JPanel(new BorderLayout());
        mainPanel = new JPanel(new CardLayout());

        dialog = new JDialog();
        dialog2= new JDialog();


        interfacciaPannelloPulsanti.setEvento(new Evento() {
            @Override
            public void evento(EventoEvent e) {

                JButton buttonAggiungi = e.getButtonAggiungi();
                JButton buttonModifica = e.getButtonModifica();
                JButton buttonElimina = e.getButtonElimina();
                JCheckBox checkDottoriAttivi = e.getVisualizzaDottoriAttivi();
                JButton premuto = e.getButtonPremuto();

                if(premuto!=null) {
                    if (premuto.equals(buttonAggiungi)) {     //Pulsante Aggiungi

                        if (!dialog.isVisible()) {
                            if (interfacciaInserimentoAggiungi.getButtonSalva().getActionListeners().length == 0) {
                                interfacciaInserimentoAggiungi.getButtonSalva().addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(!(interfacciaInserimentoAggiungi.getFieldNome().getText().isEmpty() && interfacciaInserimentoAggiungi.getFieldCognome().getText().isEmpty())) {
                                            dialog.setVisible(false);
                                            processaEventoAggiungi();
                                        }else{
                                            JOptionPane.showMessageDialog(interfacciaInserimentoAggiungi, "Inserisci tutti i campi", "Errore", JOptionPane.WARNING_MESSAGE);
                                        }
                                    }
                                });
                            }
                            interfacciaInserimentoAggiungi.setTitle("Inserisci Dottore");
                            interfacciaInserimentoAggiungi.clear();
                            dialog.add(interfacciaInserimentoAggiungi);
                            dialog.setModal(true);
                            dialog.setSize(700, 500);
                            dialog.setLocationRelativeTo(null);
                            dialog.setResizable(false);
                            dialog.setVisible(true);
                        }
                    } else if (premuto.equals(buttonElimina)) {        //pulsante Elimina
                        int rigaSelezionata = -1;
                        rigaSelezionata = interfacciaTabella.getTable().getSelectedRow();
                        if (rigaSelezionata != -1) {
                            int sceltaUtente = MyOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare questo Dottore?\n" +
                                    " Le Prenotazioni e Prescrizioni a suo carico verranno eliminate!", "Conferma eliminazione", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
                            if (sceltaUtente == JOptionPane.YES_OPTION) {
                                databasePrenotazioni.situazioneDottoreEliminato(getController().getDatabase().getDottori().get(rigaSelezionata));
                                databasePrescrizioni.situazioneDottoreEliminato(getController().getDatabase().getDottori().get(rigaSelezionata));
                                controller.getDatabase().rimuoviDottore(rigaSelezionata);
                                interfacciaTabella.aggiorna();
                                JOptionPane.showMessageDialog(null, "Dottore eliminato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } else if (premuto.equals(buttonModifica)) {       //Pulsante Modifica
                        JTable table = interfacciaTabella.getTable();
                        int rigaSelezionata = -1;
                        rigaSelezionata = table.getSelectedRow();
                        if (rigaSelezionata != -1) {
                            if (!dialog2.isVisible()) {
                                if (interfacciaInserimento.getButtonSalva().getActionListeners().length == 0) {
                                    interfacciaInserimento.getButtonSalva().addActionListener(new ActionListener() {

                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            dialog2.setVisible(false);
                                            processaEventoModifica();
                                        }
                                    });
                                }
                                Dottore dottore = controller.getDatabase().getDottori().get(rigaSelezionata);

                                String nome = dottore.getNome();
                                String cognome = dottore.getCognome();
                                String oraInizio = dottore.getOrarioLavorativoInizio();
                                String oraFine = dottore.getOrarioLavorativoFine();
                                Status status = dottore.getStato();
                                TipoOperatori tipoOperatore = dottore.getTipoOperatore();

                                interfacciaInserimento.setFieldNome(nome);
                                interfacciaInserimento.setFieldCognome(cognome);
                                interfacciaInserimento.setBoxStatus(status);
                                interfacciaInserimento.setBoxTipoOperatore(tipoOperatore);
                                interfacciaInserimento.setTimeSpinner2(oraFine);
                                interfacciaInserimento.setTimeSpinner(oraInizio);
                                interfacciaInserimento.setTitle("Modifica Dottore");

                                dialog2.add(interfacciaInserimento);
                                dialog2.setModal(true);
                                dialog2.setSize(700, 500);
                                dialog2.setLocationRelativeTo(null);
                                dialog2.setResizable(false);
                                dialog2.setVisible(true);
                            }
                        }
                    }
                }
                if(checkDottoriAttivi.isSelected()){
                    interfacciaTabella.setDati(controller.getDatabase().visualizzaDottoriAttivi());
                    interfacciaTabella.aggiorna();
                }else{
                    interfacciaTabella.setDati(controller.getDatabase().getDottori());
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


        //Viene passato la lista di prenotazioni nella classe interfaccia Tabella
        interfacciaTabella.setDati(controller.getDottori());

        homePanel.add(interfacciaPannelloPulsanti, BorderLayout.PAGE_START);
        homePanel.add(interfacciaTabella, BorderLayout.CENTER);


        add(homePanel);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,750);
        setMinimumSize(new Dimension(1000,600));
        //setTitle("Gestisci Prenotazioni");
        //setLocationRelativeTo(null);
        setVisible(true);


    }

    private void processaEventoAggiungi() {
        Date time = (Date) interfacciaInserimentoAggiungi.getTimeSpinner().getValue();
        Date time2 = (Date) interfacciaInserimentoAggiungi.getTimeSpinner2().getValue();
        LocalTime oraInizio = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault()).toLocalTime();
        LocalTime oraFine = LocalDateTime.ofInstant(time2.toInstant(), ZoneId.systemDefault()).toLocalTime();
        String nome = interfacciaInserimentoAggiungi.getFieldNome().getText();
        String cognome = interfacciaInserimentoAggiungi.getFieldCognome().getText();
        int status = interfacciaInserimentoAggiungi.getBoxStatus().getSelectedIndex();
        int tipoOperatore = interfacciaInserimentoAggiungi.getBoxTipoOperatore().getSelectedIndex();

        controller.addDottore(nome, cognome, tipoOperatore, oraInizio, oraFine, status);
        interfacciaTabella.aggiorna();
        JOptionPane.showMessageDialog(null, "Dottore aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);

    }

    private void processaEventoModifica() {
        JTable table = interfacciaTabella.getTable();
        int rigaSelezionata= table.getSelectedRow();
        table.clearSelection();

        String nome = interfacciaInserimento.getFieldNome().getText();
        String cognome = interfacciaInserimento.getFieldCognome().getText();

        Date time = (Date) interfacciaInserimento.getTimeSpinner().getValue();
        Date time2 = (Date) interfacciaInserimento.getTimeSpinner2().getValue();
        LocalTime localOra = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault()).toLocalTime();
        LocalTime localOra2 = LocalDateTime.ofInstant(time2.toInstant(), ZoneId.systemDefault()).toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
        String oraInizio = localOra.format(formatter);
        String oraFine = localOra2.format(formatter2);

        TipoOperatori tipoOperatore = (TipoOperatori) interfacciaInserimento.getBoxTipoOperatore().getSelectedItem();
        Status stato = (Status) interfacciaInserimento.getBoxStatus().getSelectedItem();


        Dottore vecchioDottore = getController().getDottori().get(rigaSelezionata);
        Dottore nuovoDottore = new Dottore(nome, cognome, tipoOperatore, stato, oraInizio, oraFine);

        databasePrenotazioni.AggiornaDottorePrenotazione(vecchioDottore, nuovoDottore);
        databasePrescrizioni.AggiornaDottorePrenotazione(vecchioDottore, nuovoDottore);

        controller.getDatabase().sostituisciDottore(rigaSelezionata, nuovoDottore);

        interfacciaTabella.aggiorna();
        JOptionPane.showMessageDialog(null, "Dottore modificato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);

    }

    public JPanel getPanel(){
        return this.homePanel;
    }
    public Controller getController(){
        return this.controller;
    }

    public InterfacciaTab getTab(){
        return interfacciaPannelloPulsanti.getInterfacciaTab();
    }
    public void setDatabasePrenotazioni(DatabasePrenotazione databasePrenotazione){
        this.databasePrenotazioni = databasePrenotazione;
    }
    public void setDatabasePrescrizioni(DatabasePrescrizioni databasePrescrizioni){
        this.databasePrescrizioni= databasePrescrizioni;
    }

}
