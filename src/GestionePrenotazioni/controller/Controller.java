package GestionePrenotazioni.controller;

import java.text.DateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import GestioneDottori.model.Dottore;
import GestionePazienti.model.Paziente;
import GestionePrenotazioni.model.DatabasePrenotazione;
import GestionePrenotazioni.model.Prenotazione;
import GestionePrenotazioni.model.Reparto;
import GestionePrenotazioni.model.TipoPrenotazione;

import java.util.List;
import java.util.Locale;

public class Controller {
    private Prenotazione prenotazione;
    private DatabasePrenotazione databasePrenotazione = new DatabasePrenotazione();
    public void addPrenotazione(Paziente paziente, Dottore dottore, Date dataDate, LocalTime ora, int repartoInt, int tipoPrenotazioneInt){

        //conversione tipo reparto
        Reparto reparto = null;
        switch(repartoInt){
            case 0:
                reparto= Reparto.DERMATOLOGIA;
                break;
            case 1:
                reparto= Reparto.CHIRURGIA;
                break;
            case 2:
                reparto= Reparto.VACCINI;
                break;
        }

        //conversione tipo prenotazione
        TipoPrenotazione tipoPrenotazione = null;
        switch(tipoPrenotazioneInt){
            case 0:
                tipoPrenotazione= TipoPrenotazione.VISITA;
                break;
            case 1:
                tipoPrenotazione= TipoPrenotazione.INTERVENTO;
                break;
            case 2:
                tipoPrenotazione= TipoPrenotazione.VACCINO;
                break;
        }

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        String data= df.format(dataDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String oraFormattata = ora.format(formatter);

        //creazione prenotazione
        prenotazione= new Prenotazione(paziente, dottore, data, oraFormattata, reparto, tipoPrenotazione);

        //aggiunta prenotazione dentro il database
        databasePrenotazione.aggiungiPrenotazione(prenotazione);
    }
        //metodo per restituire le prenotazioni
        public List<Prenotazione> getPrenotazioni() {
            return databasePrenotazione.getPrenotazioni();
        }

        public DatabasePrenotazione getDatabase(){
            return this.databasePrenotazione;
        }
}
