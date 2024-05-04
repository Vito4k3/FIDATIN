package GestioneDottori.controller;

import GestioneDottori.model.DatabaseDottori;
import GestioneDottori.model.Dottore;
import GestioneDottori.model.Status;
import GestioneDottori.model.TipoOperatori;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Controller {
    private Dottore dottore;
    private DatabaseDottori databaseDottori = new DatabaseDottori();
    public void addDottore(String nome, String cognome, int tipo, LocalTime inizioOrarioLavorativo, LocalTime fineOrarioLavorativo, int statoOperativo){

        //conversione tipo reparto
        TipoOperatori tipoOperatore = null;
        switch(tipo){
            case 0:
                tipoOperatore= tipoOperatore.CHIRURGO;
                break;
            case 1:
                tipoOperatore= tipoOperatore.DERMATOLOGO;
                break;
        }

        Status stato = null;
        switch(statoOperativo){
            case 0:
                stato= Status.ATTIVO;
                break;
            case 1:
                stato= Status.NON_ATTIVO;
                break;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String oraInizio = inizioOrarioLavorativo.format(formatter);
        String oraFine = fineOrarioLavorativo.format(formatter);

        //creazione prenotazione
        this.dottore = new Dottore(nome, cognome, tipoOperatore, stato, oraInizio, oraFine);

        //aggiunta prenotazione dentro il database
        databaseDottori.aggiungiDottore(this.dottore);
    }
        //metodo per restituire le dottori
        public List<Dottore> getDottori() {
            return databaseDottori.getDottori();
        }

        public void salvaSuFile() throws IOException {
            databaseDottori.salvaSuFile();
        }
        public void caricaDaFile() throws IOException {
            databaseDottori.caricaDaFile();
        }
        //public void setContatore(int contatore){
           // dottore.setContatore(contatore);
        //}

        public DatabaseDottori getDatabase(){
            return this.databaseDottori;
        }
}
