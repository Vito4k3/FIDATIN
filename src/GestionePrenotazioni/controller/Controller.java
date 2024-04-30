package GestionePrenotazioni.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import GestioneDottori.model.Dottore;
import GestionePazienti.model.Paziente;
import GestionePrenotazioni.model.Database;
import GestionePrenotazioni.model.Prenotazione;
import GestionePrenotazioni.model.Reparto;
import GestionePrenotazioni.model.TipoPrenotazione;

import java.util.List;
import java.util.Locale;

public class Controller {
    private Prenotazione prenotazione;
    private Database database = new Database();
    public void addPrenotazione(Paziente paziente, Dottore dottore, Date dataDate, LocalTime ora, int repartoInt, int tipoPrenotazioneInt){

        //conversione tipo reparto
        Reparto reparto = null;
        switch(repartoInt){
            case 0:
                reparto= reparto.DERMATOLOGIA;
                break;
            case 1:
                reparto= reparto.CHIRURGIA;
                break;
            case 2:
                reparto= reparto.VACCINI;
                break;
        }

        //conversione tipo prenotazione
        TipoPrenotazione tipoPrenotazione = null;
        switch(tipoPrenotazioneInt){
            case 0:
                tipoPrenotazione= tipoPrenotazione.VISITA;
                break;
            case 1:
                tipoPrenotazione= tipoPrenotazione.INTERVENTO;
                break;
            case 2:
                tipoPrenotazione= tipoPrenotazione.VACCINO;
                break;
        }

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        String data= df.format(dataDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String oraFormattata = ora.format(formatter);

        //creazione prenotazione
        prenotazione= new Prenotazione(paziente, dottore, data, oraFormattata, reparto, tipoPrenotazione);

        //aggiunta prenotazione dentro il database
        database.aggiungiPrenotazione(prenotazione);
    }
        //metodo per restituire le prenotazioni
        public List<Prenotazione> getPrenotazioni() {
            return database.getPrenotazioni();
        }

        public void salvaSuFile(File file) throws IOException {
            database.salvaSuFile(file);
        }
        public void caricaDaFile(File file) throws IOException {
            database.caricaDaFile(file);
        }
        public void setContatore(int contatore){
            prenotazione.setContatore(contatore);
        }

        public Database getDatabase(){
            return this.database;
        }
}
